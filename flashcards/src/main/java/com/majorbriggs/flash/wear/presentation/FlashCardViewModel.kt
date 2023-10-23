package com.majorbriggs.flash.wear.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.wear.tiles.TileUpdateRequester
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceUpdateRequester
import com.majorbriggs.flash.wear.data.FlashCardRepository
import com.majorbriggs.flash.wear.domain.FlashCard
import com.majorbriggs.flash.wear.presentation.model.FlashViewState
import com.majorbriggs.flash.wear.tile.tilesapi.WordOfTheDayTileService
import com.majorbriggs.flash.wear.tile.glance.LearningProgressTileService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val flashCardRepository: FlashCardRepository,
    private val tileUpdateRequester: TileUpdateRequester,
    private val complicationUpdateRequester: ComplicationDataSourceUpdateRequester,
) : ViewModel() {

    private val flashCardId: Int = savedStateHandle.get<String>("id")?.toInt() ?: 0

    private lateinit var allFlashCards: List<FlashCard>
    private val _flashState = MutableStateFlow(
        FlashViewState(
            wordToLearn = "",
            translation = "",
        )
    )

    init {
        tileUpdateRequester.requestUpdate(LearningProgressTileService::class.java)
        tileUpdateRequester.requestUpdate(WordOfTheDayTileService::class.java)
        complicationUpdateRequester.requestUpdateAll()

        viewModelScope.launch {
            allFlashCards = flashCardRepository.getAllFlashCards()
            _flashState.value = allFlashCards[flashCardId].let {
                FlashViewState(
                    wordToLearn = it.wordToLearn,
                    translation = it.translation
                )
            }
        }
    }

    val flashViewState: Flow<FlashViewState> = _flashState

    fun nextWord() {
        val nextWord = allFlashCards.random()
        _flashState.value = FlashViewState(
            nextWord.wordToLearn,
            nextWord.translation
        )
        viewModelScope.launch {
            increaseFlashcardsDailyCounter()
        }
    }

    fun favoriteWord() {
        viewModelScope.launch {
            saveWordOfTheDay()
            tileUpdateRequester.requestUpdate(WordOfTheDayTileService::class.java)
            complicationUpdateRequester.requestUpdateAll()
        }
    }

    private suspend fun increaseFlashcardsDailyCounter() {
        flashCardRepository.increaseDailyCounter()
        complicationUpdateRequester.requestUpdateAll()
        tileUpdateRequester.requestUpdate(LearningProgressTileService::class.java) // THIS SH
    }

    private suspend fun saveWordOfTheDay() {
        // Store word of the day
        with(_flashState.value) {
            flashCardRepository.saveWordOfTheDay(wordToLearn, translation)
        }
    }

    fun flipCard() {
        _flashState.update { it.copy(isFlipped = !it.isFlipped) }
    }
}
