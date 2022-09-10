package com.raedapps.alwan.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.raedapps.alwan.AlwanState
import com.raedapps.alwan.model.HSVColorPickerUIData
import com.raedapps.alwan.rememberAlwanState
import kotlin.math.min

@Composable
fun Alwan(
    onColorChanged: (color: Color) -> Unit,
    modifier: Modifier = Modifier,
    state: AlwanState = rememberAlwanState(Color.Red),
    onChangeStart: () -> Unit = { },
    onChangeEnd: () -> Unit = { },
    showAlphaSlider: Boolean = false,
) {
    val suggestedSize = 300.dp
    Column(
        modifier = modifier
            .width(suggestedSize)
            .wrapContentHeight(),
    ) {
        val currentDensity = LocalDensity.current
        var sizePx by remember {
            with(currentDensity) {
                mutableStateOf(suggestedSize.roundToPx())
            }
        }
        val sizeDp = remember(sizePx) {
            with(currentDensity) {
                sizePx.toDp()
            }
        }
        val uiData = remember(sizePx) {
            HSVColorPickerUIData(sizePx)
        }
        HSVColorPicker(
            modifier = Modifier
                .aspectRatio(1f)
                .onSizeChanged {
                    sizePx = min(it.width, it.height)
                },
            uiData = uiData,
            selectedColor = state.hsvColor,
            onChangeStart = onChangeStart,
            onColorChanged = {
                state.hsvColor = it
                onColorChanged(state.color)
            },
            onChangeEnd = onChangeEnd,
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (showAlphaSlider) {
            AlphaSlider(
                Modifier
                    .width(sizeDp)
                    .height(36.dp),
                state.color,
                onAlphaChanged = {
                    state.alpha = it
                    onColorChanged(state.color)
                }
            )
        }
    }
}
