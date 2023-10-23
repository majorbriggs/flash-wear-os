package com.majorbriggs.flash.wear.presentation

import androidx.lifecycle.ViewModel
import com.majorbriggs.flash.wear.data.static.WordBank
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data class WordListState(
    val items: List<String>,
)

@HiltViewModel
class WordListViewModel @Inject constructor(
) : ViewModel() {

    private val listOfWords = WordBank.allWords

    private val _wordListState = MutableStateFlow(
        WordListState(
            items = listOfWords.take(10).map { it.wordToLearn }
        )
    )

    val wordListState: Flow<WordListState> = _wordListState
}
