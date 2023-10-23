package com.majorbriggs.flash.wear.tile.tilesapi

import androidx.compose.ui.graphics.toArgb
import androidx.wear.protolayout.material.Colors as TileColors
import androidx.wear.compose.material.Colors as ComposeColors

fun ComposeColors.toTileColors(): TileColors =
    androidx.wear.protolayout.material.Colors(
        primary.toArgb(),
        onPrimary.toArgb(),
        surface.toArgb(),
        onSurface.toArgb()
    )
