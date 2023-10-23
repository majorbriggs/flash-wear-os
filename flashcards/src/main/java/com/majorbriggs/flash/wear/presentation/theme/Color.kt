package com.majorbriggs.flash.wear.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors


val DarkGreen = Color(0xFF4b5643)
val VeryLightGreen = Color(0xFFd9e7ca)
val Red400 = Color(0xFFCF6679)

val WearAppColorPalette: Colors = Colors(
    primary = DarkGreen,
    primaryVariant = DarkGreen,
    secondary = VeryLightGreen,
    secondaryVariant = VeryLightGreen,
    error = Red400,
    onPrimary = VeryLightGreen,
    onSecondary = DarkGreen,
    onError = Color.Black
)
