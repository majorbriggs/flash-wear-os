package com.majorbriggs.flash.wear.tile.glance

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.layout.Column
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.wear.compose.material.MaterialTheme

@Composable
private fun SimpleGlanceTile() {
    Column {
        Text(
            text = "Today",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = ColorProvider(color = MaterialTheme.colors.secondary)
            )
        )
        Text(
            text = "Summary",
            style = TextStyle(
                color = ColorProvider(color = Color.White)
            )
        )
    }
}
