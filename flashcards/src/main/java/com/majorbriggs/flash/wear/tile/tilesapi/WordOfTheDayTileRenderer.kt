package com.majorbriggs.flash.wear.tile.tilesapi

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.wear.compose.ui.tooling.preview.WearPreviewSmallRound
import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.ColorBuilders
import androidx.wear.protolayout.DeviceParametersBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material.ChipColors
import androidx.wear.protolayout.material.CompactChip
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.Typography
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.tools.TileLayoutPreview
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import com.majorbriggs.flash.wear.presentation.theme.WearAppColorPalette
import androidx.wear.protolayout.material.Colors as TileColors

@ExperimentalHorologistApi
class WordOfTheDayTileRenderer(context: Context) :
    SingleTileLayoutRenderer<WordOfTheDayTileData, WordOfTheDayTileData>(context) {

    override fun getResourcesVersionForTileState(state: WordOfTheDayTileData): String {
        return state.word
    }

    override fun createTheme(): TileColors {
        return WearAppColorPalette.toTileColors()
    }

    override fun renderTile(
        state: WordOfTheDayTileData,
        deviceParameters: DeviceParametersBuilders.DeviceParameters,
    ): LayoutElementBuilders.LayoutElement {
        return renderWordOfTheDayTile(state, deviceParameters)
    }

    private fun renderWordOfTheDayTile(
        state: WordOfTheDayTileData,
        deviceParameters: DeviceParametersBuilders.DeviceParameters,
    ): LayoutElementBuilders.LayoutElement = PrimaryLayout.Builder(deviceParameters)
        .setPrimaryLabelTextContent(
            titleText()
        )
        .setContent(
            mainContent(state.word)
        )
        .setSecondaryLabelTextContent(bottomText(state.translation))
        .setPrimaryChipContent(
            CompactChip.Builder(
                context,
                "Learn  more",
                learnMoreClickable(state.word),
                deviceParameters
            )
                .setChipColors(ChipColors.primaryChipColors(theme))
                .build()
        )
        .build()

    private fun bottomText(text: String) =
        Text.Builder(context, text)
            .setTypography(Typography.TYPOGRAPHY_BODY1)
            .setColor(ColorBuilders.argb(theme.onSurface))
            .build()

    private fun titleText() =
        Text.Builder(context, "Word of the day")
            .setTypography(Typography.TYPOGRAPHY_TITLE2)
            .setColor(ColorBuilders.argb(theme.primary))
            .build()

    private fun mainContent(text: String) =
        Text.Builder(context, text)
            .setTypography(Typography.TYPOGRAPHY_DISPLAY3)
            .setColor(ColorBuilders.argb(theme.onSurface))
            .build()

    private fun learnMoreClickable(
        flashCardId: String,
    ): ModifiersBuilders.Clickable =
        ModifiersBuilders.Clickable.Builder()
            .setOnClick(ActionBuilders.LoadAction.Builder().build())
            .setId(flashCardId) // TODO: Deeplink to a specific flashcard
            .build()
}

@OptIn(ExperimentalHorologistApi::class)
@WearPreviewSmallRound
@Composable
fun WordOfTheDayTilePreview() {
    val context = LocalContext.current

    val state = WordOfTheDayTileData("el perro", "dog")
    val renderer = WordOfTheDayTileRenderer(context)

    TileLayoutPreview(
        state = state, resourceState = state, renderer = renderer
    )
}
