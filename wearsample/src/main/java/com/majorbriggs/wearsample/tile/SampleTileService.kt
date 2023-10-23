package com.majorbriggs.wearsample.tile

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.protolayout.TimelineBuilders
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.tools.LayoutRootPreview
import com.google.android.horologist.compose.tools.buildDeviceParameters
import com.google.android.horologist.tiles.SuspendingTileService
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.Typography

private const val RESOURCES_VERSION = "0"

/**
 * Skeleton for a tile with no images. Consider also using SingleTileLayoutRenderer to render the content
 */
@OptIn(ExperimentalHorologistApi::class)
class SampleTileService : SuspendingTileService() {

    override suspend fun resourcesRequest(
        requestParams: RequestBuilders.ResourcesRequest,
    ): ResourceBuilders.Resources {
        return ResourceBuilders.Resources.Builder().setVersion(RESOURCES_VERSION).build()
    }

    override suspend fun tileRequest(
        requestParams: RequestBuilders.TileRequest,
    ): TileBuilders.Tile {
        val singleTileTimeline = TimelineBuilders.Timeline.Builder()
            .addTimelineEntry(
                TimelineBuilders.TimelineEntry.Builder().setLayout(
                    LayoutElementBuilders.Layout.Builder().setRoot(tileLayout(this)).build()
                ).build()
            ).build()

        return TileBuilders.Tile.Builder()
            .setResourcesVersion(RESOURCES_VERSION)
            .setTileTimeline(singleTileTimeline).build()
    }
}

private fun tileLayout(context: Context): LayoutElementBuilders.LayoutElement {
    return PrimaryLayout.Builder(
        buildDeviceParameters(context.resources))
        .setContent(
            Text.Builder(context, "Hello World!")
                .setTypography(Typography.TYPOGRAPHY_TITLE2)
                .build()
        ).build()
}

@WearPreviewLargeRound
@Composable
fun TilePreview() {
    LayoutRootPreview(root = tileLayout(LocalContext.current))
}
