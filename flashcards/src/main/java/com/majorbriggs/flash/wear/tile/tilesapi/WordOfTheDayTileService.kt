
package com.majorbriggs.flash.wear.tile.tilesapi

import android.content.Intent
import androidx.core.app.TaskStackBuilder
import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.tiles.SuspendingTileService
import com.majorbriggs.flash.wear.presentation.FlashWearActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Don't know why, but Hilt was failing when WordOfTheDayTileService inherited directly from SuspendingTileService
@OptIn(ExperimentalHorologistApi::class)
abstract class ProxyWordOfTheDayTileService : SuspendingTileService()

@OptIn(ExperimentalHorologistApi::class)
@AndroidEntryPoint
class WordOfTheDayTileService : ProxyWordOfTheDayTileService() {

    @Inject
    lateinit var dataProvider: WordOfTheDayTileDataProvider

    private val renderer = WordOfTheDayTileRenderer(this)

    override suspend fun resourcesRequest(requestParams: RequestBuilders.ResourcesRequest): ResourceBuilders.Resources {
        return renderer.produceRequestedResources(tileState(), requestParams)
    }

    private suspend fun tileState(): WordOfTheDayTileData {
        return dataProvider.getWordOfTheDay()
    }

    override suspend fun tileRequest(requestParams: RequestBuilders.TileRequest): TileBuilders.Tile {
        val lastClickableId = requestParams.currentState.lastClickableId
        if (lastClickableId.isNotBlank()) {
            handleClick(lastClickableId)
        }
        return renderer.renderTimeline(tileState(), requestParams)
    }

    private fun handleClick(uri: String) {
        val launchActivityIntent = Intent(this, FlashWearActivity::class.java)

        TaskStackBuilder.create(this)
            .addNextIntentWithParentStack(
                launchActivityIntent
            )
            .startActivities()
    }
}
