package com.raedapps.alwan.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.window.Dialog
import com.raedapps.alwan.AlwanState
import com.raedapps.alwan.rememberAlwanState
import com.raedapps.alwan.renderer.drawTransparencyGrid

@Composable
fun AlwanDialog(
    onDismissRequest: () -> Unit,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    state: AlwanState = rememberAlwanState(Color.Red),
    onChangeStart: () -> Unit = { },
    onChangeEnd: () -> Unit = { },
    positiveButtonText: String? = null,
    onPositiveButtonClick: () -> Unit = {},
    negativeButtonText: String? = null,
    onNegativeButtonClick: () -> Unit = {},
    showAlphaSlider: Boolean = false,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            modifier = modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                Modifier.padding(24.dp, 24.dp, 24.dp, 12.dp)
            ) {
                Box {
                    Alwan(
                        onColorChanged = onColorChanged,
                        modifier = modifier,
                        state = state,
                        onChangeStart = onChangeStart,
                        onChangeEnd = onChangeEnd,
                        showAlphaSlider = showAlphaSlider,
                    )
                    SelectedColor(color = state.color)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.align(Alignment.End),
                ) {
                    if (negativeButtonText != null) {
                        TextButton(
                            onClick = onNegativeButtonClick,
                        ) {
                            Text(text = negativeButtonText)
                        }
                    }
                    if (positiveButtonText != null) {
                        TextButton(
                            onClick = onPositiveButtonClick,
                        ) {
                            Text(text = positiveButtonText)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BoxScope.SelectedColor(
    color: Color,
) {
    Box(
        Modifier
            .size(36.dp)
            .align(Alignment.TopEnd)
            .clip(CircleShape),
    ) {
        val backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        val squaresColor = MaterialTheme.colors.onSurface.copy(alpha = 1f)
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawTransparencyGrid(
                    backgroundColor,
                    squaresColor,
                )
            },
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = color,
            border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
            elevation = 4.dp,
            shape = CircleShape,
        ) { }
    }
}