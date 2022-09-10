package com.raedapps.alwan.renderer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

internal fun DrawScope.drawTransparencyGrid(
    backgroundColor: Color,
    squaresColor: Color,
) {
    drawRect(backgroundColor)
    val squareSize = Size(
        10.dp.toPx(),
        10.dp.toPx(),
    )
    var i = 0f
    while (i < size.width) {
        var j = 0f
        while (j < size.height) {
            drawRect(squaresColor, Offset(i, j), squareSize)
            val l = i + squareSize.width
            val t = j + squareSize.height
            drawRect(squaresColor, Offset(l, t), squareSize)
            j += 2 * squareSize.height
        }
        i += 2 * squareSize.width
    }
}
