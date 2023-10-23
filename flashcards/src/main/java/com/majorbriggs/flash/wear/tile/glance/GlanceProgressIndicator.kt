package com.majorbriggs.flash.wear.tile.glance

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.glance.wear.tiles.curved.AnchorType
import androidx.glance.wear.tiles.curved.CurvedRow
import androidx.glance.wear.tiles.curved.GlanceCurvedModifier
import androidx.glance.wear.tiles.curved.sweepAngleDegrees
import androidx.glance.wear.tiles.curved.thickness
import com.majorbriggs.flash.wear.presentation.theme.WearAppColorPalette

@Composable
fun GlanceProgressIndicator(
    value: Int,
    target: Int,
) {
    Column(
        modifier = GlanceModifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.Top
    ) {
        GlanceProgressCircle(modifier = GlanceModifier.size(200.dp), value, target)
    }
}

@Composable
private fun GlanceProgressCircle(modifier: GlanceModifier = GlanceModifier, value: Int, target: Int) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$value/$target",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = GlanceModifier.padding(horizontal = 16.dp),
                text = "Words reviewed today",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = ColorProvider(color = Color(0xFF949aa1))
                )
            )
        }

        val goalProgress = (value.toFloat() / target.toFloat()) * 360f

        // Background of Circular progress indicator
        CurvedRow {
            curvedLine(
                color = ColorProvider(WearAppColorPalette.primary),
                curvedModifier =
                GlanceCurvedModifier
                    .sweepAngleDegrees(360f)
                    .thickness(5.dp)
            )
        }

        // This shows the color of actual progress in the indicator
        CurvedRow(
            anchorDegrees = 270f,
            anchorType = AnchorType.Start
        ) {
            curvedLine(
                color = ColorProvider(WearAppColorPalette.secondary),
                curvedModifier =
                GlanceCurvedModifier
                    .sweepAngleDegrees(goalProgress)
                    .thickness(5.dp)
            )
        }
    }
}
