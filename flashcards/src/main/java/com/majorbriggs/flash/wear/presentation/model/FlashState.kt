package com.majorbriggs.flash.wear.presentation.model

sealed class FlashState {
    data class FlashCard(
        val wordToLearn: String,
        val translation: String,
    ) : FlashState()
}
