package com.majorbriggs.flash.wear.tile.glance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.glance.GlanceComposable
import androidx.glance.wear.tiles.GlanceTileService
import com.majorbriggs.flash.wear.data.FlashCardRepository
import com.majorbriggs.flash.wear.presentation.theme.FlashTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

abstract class ProxyGlanceTileService : GlanceTileService()

@AndroidEntryPoint
class LearningProgressTileService : ProxyGlanceTileService() {

    @Inject
    lateinit var flashCardRepository: FlashCardRepository

    @Composable
    @GlanceComposable
    override fun Content() {
        val state = remember {
            flashCardRepository.getFlashCardsViewed()
        }
        FlashTheme {
            GlanceProgressIndicator(
                value = state,
                target = 30,
            )
        }
    }
}
