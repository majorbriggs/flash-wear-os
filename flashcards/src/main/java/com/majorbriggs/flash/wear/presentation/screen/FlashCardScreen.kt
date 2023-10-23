package com.majorbriggs.flash.wear.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.majorbriggs.flash.wear.presentation.FlashCardViewModel
import com.majorbriggs.flash.wear.presentation.model.FlashViewState
import com.majorbriggs.flash.wear.presentation.ui.FlashCardWithButtons

@Composable
fun FlashCardScreen() {
    val viewModel = hiltViewModel<FlashCardViewModel>()
    val viewState by viewModel.flashViewState.collectAsState(
        FlashViewState(
            "",
            "",
        )
    )
    FlashCardWithButtons(
        viewState = viewState,
        onPositiveClicked = { viewModel.nextWord() },
        onFavoriteClicked = { viewModel.favoriteWord() },
        onCardFlipped = { viewModel.flipCard() },
    )
}
