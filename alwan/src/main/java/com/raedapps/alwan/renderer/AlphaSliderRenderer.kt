package com.raedapps.alwan.renderer

import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp

internal fun DrawScope.drawAlphaSlider(
    selectedColor: Color,
    backgroundColor: Color,
    squaresColor: Color,
) {
    val rect = Rect(Offset.Zero, size)
    val roundRect = RoundRect(
        rect,
        CornerRadius(6.dp.toPx(), 6.dp.toPx())
    )
    val path = Path()
    path.addRoundRect(roundRect)
    clipPath(path) {
        drawTransparencyGrid(
            backgroundColor,
            squaresColor,
        )
        drawRoundRect(
            Brush.horizontalGradient(
                listOf(
                    selectedColor.copy(alpha = 0f),
                    selectedColor.copy(alpha = 1f),
                ),
            ),
            rect.topLeft,
            rect.size,
        )
    }
}

internal fun DrawScope.drawAlphaIndicator(
    color: Color,
    active: Boolean,
) {
    drawSelectionIndicator(
        center = Offset(
            color.alpha * size.width,
            center.y,
        ),
        radius = 12.dp.toPx(),
        color = color,
        active,
        boundary = Rect(Offset.Zero, size)
    )
}
