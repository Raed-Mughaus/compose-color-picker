package com.raedapps.alwan.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.input.pointer.pointerInput
import com.raedapps.alwan.model.HSVColorPickerUIData
import com.raedapps.alwan.model.HSVColor
import com.raedapps.alwan.event.handler.detectHueChangeEvent
import com.raedapps.alwan.event.handler.detectSaturationValueChange
import com.raedapps.alwan.renderer.drawHueCircle
import com.raedapps.alwan.renderer.drawHueIndicator
import com.raedapps.alwan.renderer.drawSaturationValueRect
import com.raedapps.alwan.renderer.drawSaturationValueIndicator

@Composable
internal fun HSVColorPicker(
    modifier: Modifier,
    uiData: HSVColorPickerUIData,
    selectedColor: HSVColor,
    onChangeStart: () -> Unit,
    onColorChanged: (HSVColor) -> Unit,
    onChangeEnd: () -> Unit,
) {
    var activeHueIndicator by remember { mutableStateOf(false) }
    var activeSVIndicator by remember { mutableStateOf(false) }
    var key by remember {
        mutableStateOf(0)
    }
    Canvas(
        modifier = modifier
            .pointerInput(uiData, key) {
                detectHueChangeEvent(
                    uiData.hueOuterCircle,
                    uiData.hueInnerCircle,
                    onChangeStart = {
                        activeHueIndicator = true
                        onChangeStart()
                    },
                    onHueChanged = { h ->
                        onColorChanged(
                            selectedColor.copy(h = 360 - h),
                        )
                    },
                    onChangeEnd = {
                        activeHueIndicator = false
                        onChangeEnd()
                        key++
                    }
                )
            }
            .pointerInput(uiData, key) {
                detectSaturationValueChange(
                    uiData.saturationValueRect,
                    onChangeStart = {
                        activeSVIndicator = true
                        onChangeStart()
                    },
                    onSaturationValueChanged = { s, v ->
                        onColorChanged(
                            selectedColor.copy(s = s, v = v),
                        )
                    },
                    onChangeEnd = {
                        activeSVIndicator = false
                        onChangeEnd()
                        key++
                    },
                )
            },
        onDraw = {
            clipRect {
                drawHueCircle(uiData)
                drawHueIndicator(uiData, selectedColor, activeHueIndicator)
                drawSaturationValueRect(uiData, selectedColor)
                drawSaturationValueIndicator(uiData, selectedColor, activeSVIndicator)
            }
        },
    )
}
