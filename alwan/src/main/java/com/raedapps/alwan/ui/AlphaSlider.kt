package com.raedapps.alwan.ui

import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.raedapps.alwan.event.handler.detectAlphaChangeEvent
import com.raedapps.alwan.model.HSVColorPickerUIData
import com.raedapps.alwan.renderer.drawAlphaIndicator
import com.raedapps.alwan.renderer.drawAlphaSlider

@Composable
internal fun AlphaSlider(
    modifier: Modifier,
    selectedColor: Color,
    onAlphaChanged: (Float) -> Unit,
) {
    val backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    val squaresColor = MaterialTheme.colors.onSurface.copy(alpha = 1f)
    var activeSelectionIndicator by remember {
        mutableStateOf(false)
    }
    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectAlphaChangeEvent(
                    onChangeStart = {
                        activeSelectionIndicator = true
                    },
                    onAlphaChanged = onAlphaChanged,
                    onChangeEnd = {
                        activeSelectionIndicator = false
                    },
                )
            },
        onDraw = {
            clipRect {
                drawAlphaSlider(
                    selectedColor,
                    backgroundColor = backgroundColor,
                    squaresColor = squaresColor,
                )
                drawAlphaIndicator(
                    selectedColor,
                    activeSelectionIndicator,
                )
            }
        },
    )
}