package com.majorbriggs.flash.wear.presentation.screen

import android.util.Log
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.scrollAway
import com.majorbriggs.flash.wear.presentation.WordListState
import com.majorbriggs.flash.wear.presentation.WordListViewModel
import com.majorbriggs.flash.wear.presentation.ui.WordList
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalWearFoundationApi::class)
fun WordListScreen(
    modifier: Modifier = Modifier,
    onWordSelected: (Int) -> Unit,
    viewModel: WordListViewModel = hiltViewModel(),
) {
    val listState = rememberScalingLazyListState()
    val focusRequester = rememberActiveFocusRequester()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        timeText = { TimeText(modifier = Modifier.scrollAway(listState)) },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(
                scalingLazyListState = listState
            )
        }

    ) {
        val viewState by viewModel.wordListState.collectAsState(
            WordListState(emptyList())
        )

        WordList(
            modifier = modifier
                .fillMaxWidth()
                .onRotaryScrollEvent {
                    coroutineScope.launch {
                        listState.scrollBy(it.verticalScrollPixels)
                        // The following line can be omitted if using Wear Compose
                        // 1.3.0-alpha07 or later
                        listState.animateScrollBy(0f)
                    }
                    true
                }
                .focusRequester(focusRequester)
                .focusable(),
            items = viewState.items,
            listState = listState,
            onItemSelected = {
                Log.d("PIOTR", "item $it clicked")
                onWordSelected(it)
            })
    }
}
