package com.example.spreadsheetjetpackcompose.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        x2 = abs(x = from.x + to.x) / 2f,
        y2 = abs(x = from.y + to.y) / 2f
    )
}
