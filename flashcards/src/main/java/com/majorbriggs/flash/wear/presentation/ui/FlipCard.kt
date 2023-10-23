package com.majorbriggs.flash.wear.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound

@Composable
fun FlipCard(
    modifier: Modifier = Modifier,
    frontText: String,
    backText: String,
    isFlipped: Boolean,
    flipCard: () -> Unit,
) {
    val rotationY by animateFloatAsState(if (isFlipped) 180f else 0f, label = "flip")
    val textRotationY = if (rotationY <= 90f) 0f else 180f

    Card(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .graphicsLayer(rotationY = rotationY), // Add the rotation
        shape = RoundedCornerShape(8.dp),
        onClick = { flipCard() }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isFlipped) backText else frontText,
                color = Color.White,
                style = MaterialTheme.typography.title2,
                modifier = Modifier.graphicsLayer(rotationY = textRotationY)
            )
        }
    }
}

@Preview
@Composable
fun FlipCardPreview() {
    FlipCard(
        frontText = "Front",
        backText = "Back",
        isFlipped = false,
        flipCard = {}
    )
}
