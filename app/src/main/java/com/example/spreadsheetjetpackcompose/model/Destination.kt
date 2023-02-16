package com.example.spreadsheetjetpackcompose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Destination(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)