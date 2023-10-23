package com.majorbriggs.wearsample.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound

@Composable
fun ScaffoldWithScrollIndicator() {

    val listState = rememberScalingLazyListState()
    val vignetteState = remember { mutableStateOf(VignettePosition.TopAndBottom) }
    val showVignette = remember { mutableStateOf(true) }

    Scaffold(
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState,
                modifier = Modifier
            )
        },
        vignette = {
            if (showVignette.value) {
                Vignette(vignettePosition = vignetteState.value)
            }
        },
        timeText = {
            TimeText()
        }
    ) {
        ScalingLazyColumn(
            contentPadding = PaddingValues(top = 40.dp),
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Chip(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showVignette.value = false },
                    label = { Text("No Vignette") },
                    colors = ChipDefaults.secondaryChipColors()
                )
            }
            item {
                Chip(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        showVignette.value = true
                        vignetteState.value = VignettePosition.Top
                    },
                    label = { Text("Top Vignette only") },
                    colors = ChipDefaults.secondaryChipColors()
                )
            }
            item {
                Chip(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        showVignette.value = true
                        vignetteState.value = VignettePosition.Bottom
                    },
                    label = { Text("Bottom Vignette only") },
                    colors = ChipDefaults.secondaryChipColors()
                )
            }
            item {
                Chip(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        showVignette.value = true
                        vignetteState.value = VignettePosition.TopAndBottom
                    },
                    label = { Text("Top and Bottom Vignette") },
                    colors = ChipDefaults.secondaryChipColors()
                )
            }
            items(20) {
                Chip(
                    onClick = { },
                    label = { Text("List item $it") },
                    colors = ChipDefaults.secondaryChipColors()
                )
            }
        }
    }
}

@WearPreviewLargeRound
@Composable
fun ScaffoldWithScrollIndicatorVignetteOn() {
    ScaffoldWithScrollIndicator()
}
