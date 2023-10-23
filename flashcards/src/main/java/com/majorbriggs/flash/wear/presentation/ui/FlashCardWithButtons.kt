package com.majorbriggs.flash.wear.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import androidx.wear.compose.ui.tooling.preview.WearPreviewSmallRound
import com.majorbriggs.flash.wear.presentation.model.FlashViewState
import com.majorbriggs.flash.wear.presentation.theme.FlashTheme

@Composable
fun FlashCardWithButtons(
    modifier: Modifier = Modifier,
    viewState: FlashViewState,
    onPositiveClicked: () -> Unit,
    onFavoriteClicked: () -> Unit,
    onCardFlipped: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FlipCard(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(horizontal = 32.dp),
            frontText = viewState.wordToLearn,
            backText = viewState.translation,
            isFlipped = viewState.isFlipped,
            flipCard = onCardFlipped,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.padding(bottom = 16.dp)) {
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp),
                onClick = onFavoriteClicked,
                colors = ButtonDefaults.secondaryButtonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Favorite"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp),
                onClick = onPositiveClicked,
                colors = ButtonDefaults.secondaryButtonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                    contentDescription = "Next"
                )
            }
        }

    }
}

@WearPreviewSmallRound
@Composable
fun FlipCardWithButtonsPreview() {
    FlashTheme {
        FlashCardWithButtons(
            modifier = Modifier,
            viewState = FlashViewState(
                wordToLearn = "Hola",
                translation = "Hello",
                flashCardsDone = 0,
            ),
            onPositiveClicked = {},
            onFavoriteClicked = {},
            onCardFlipped = {})
    }
}

@WearPreviewLargeRound
@Composable
fun FlipCardWithButtonsPreviewLarge() {
    FlashTheme {
        FlashCardWithButtons(
            modifier = Modifier,
            viewState = FlashViewState(
                wordToLearn = "Hola",
                translation = "Hello",
                flashCardsDone = 0,
            ),
            onPositiveClicked = {},
            onFavoriteClicked = {},
            onCardFlipped = {})
    }
}
