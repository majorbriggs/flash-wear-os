package com.majorbriggs.flash.wear.presentation.model

data class FlashViewState(
    val wordToLearn: String = "",
    val translation: String = "",
    val isFlipped: Boolean = false,
    val flashCardsDone: Int = 0,
)
