package com.raedapps.alwan.renderer

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.raedapps.alwan.model.HSVColorPickerUIData
import com.raedapps.alwan.model.RSVColor

internal fun DrawScope.drawSaturationValueRect(
    uiData: HSVColorPickerUIData,
    selectedColor: RSVColor,
) {
    val rect = uiData.saturationValueRect
    val cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
    drawRoundRect(
        Brush.horizontalGradient(
            listOf(
                Color.White,
                selectedColor
                    .copy(s = 1f, v = 1f)
                    .toColor(),
            ),
            startX = rect.left,
            endX = rect.right,
        ),
        rect.topLeft,
        rect.size,
        cornerRadius = cornerRadius,
    )
    drawRoundRect(
        Brush.verticalGradient(
            listOf(Color.Black, Color(0)),
            startY = rect.bottom,
            endY = rect.top,
        ),
        rect.topLeft,
        rect.size,
        cornerRadius = cornerRadius,
    )
    drawRoundRect(
        Color.White,
        rect.topLeft,
        rect.size,
        style = Stroke(
            width = 1.dp.toPx(),
        ),
        cornerRadius = cornerRadius,
    )
}

internal fun DrawScope.drawSaturationValueIndicator(
    uiData: HSVColorPickerUIData,
    selectedColor: RSVColor,
    active: Boolean,
) {
    val rect = uiData.saturationValueRect
    drawSelectionIndicator(
        Offset(
            rect.left + selectedColor.s * rect.width,
            rect.bottom - selectedColor.v * rect.height,
        ),
        12.dp.toPx(),
        selectedColor.toColor(),
        active,
        boundary = uiData.saturationValueRect,
    )
}
