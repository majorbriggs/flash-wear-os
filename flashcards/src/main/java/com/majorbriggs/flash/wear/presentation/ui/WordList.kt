package com.majorbriggs.flash.wear.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyColumnDefaults
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.itemsIndexed
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound

@Composable
fun WordList(
    modifier: Modifier = Modifier,
    items: List<String>,
    onItemSelected: (Int) -> Unit,
    listState: ScalingLazyListState,
) {
    ScalingLazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        flingBehavior = ScalingLazyColumnDefaults.snapFlingBehavior(state = listState),
        scalingParams = ScalingLazyColumnDefaults.scalingParams(
            edgeAlpha = 0.4f,
            minTransitionArea = 0.6f,
            maxTransitionArea = 0.9f,
        ),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        content = {
            itemsIndexed(items) { index, item ->
                WordListItem(
                    onItemClick = { onItemSelected(index) },
                    word = item
                )
            }
        }
    )
}

@Composable
fun WordListItem(modifier: Modifier = Modifier, word: String, onItemClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onItemClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                style = MaterialTheme.typography.title2,
                textAlign = TextAlign.Center,
                text = word,
            )
        }
    }
}

@Preview
@Composable
fun WordListItemPreview() {
    WordListItem(
        word = "Test"
    )
}

@WearPreviewLargeRound
@Composable
fun WordListPreview() {
    WordList(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        items = listOf("1", "2", "3", "4"), listState = ScalingLazyListState(), onItemSelected = {})
}
