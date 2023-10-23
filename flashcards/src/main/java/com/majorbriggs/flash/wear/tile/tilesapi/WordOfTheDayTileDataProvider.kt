package com.majorbriggs.flash.wear.tile.tilesapi

import com.majorbriggs.flash.wear.data.FlashCardRepository
import javax.inject.Inject

class WordOfTheDayTileDataProvider @Inject constructor(
    private val repository: FlashCardRepository,
) {

    suspend fun getWordOfTheDay(): WordOfTheDayTileData {
        val wordOfTheDayFlashCard = repository.getWordOfTheDay()
        return WordOfTheDayTileData(wordOfTheDayFlashCard.wordToLearn, wordOfTheDayFlashCard.translation)
    }
}
