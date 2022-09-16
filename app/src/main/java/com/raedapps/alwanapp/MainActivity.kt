package com.raedapps.alwanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.raedapps.alwan.rememberAlwanState
import com.raedapps.alwan.ui.Alwan
import com.raedapps.alwan.ui.AlwanDialog
import com.raedapps.alwanapp.ui.theme.AlwanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlwanTheme {
                Scaffold { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        var color by remember {
                            mutableStateOf(Color(0xFF2187BB))
                        }
                        /*Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(24.dp)
                                .clip(
                                    RoundedCornerShape(16.dp),
                                )
                        ) {
                            TransparencyGrid(
                                Modifier.fillMaxSize(),
                            )
                            Card(
                                modifier = Modifier.fillMaxSize(),
                                backgroundColor = color,
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                ) {
                                    val shape = RoundedCornerShape(16.dp)
                                    Text(
                                        String.format("#%08X", color.toArgb()),
                                        modifier = Modifier
                                            .clip(shape)
                                            .background(Color.Black, shape)
                                            .border(3.dp, Color.White, shape = shape)
                                            .padding(6.dp),
                                        style = MaterialTheme.typography.h4,
                                        fontFamily = FontFamily.Monospace,
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Alwan(
                            onColorChanged = {
                                color = it
                            },
                            showAlphaSlider = true,
                        )*/
                        Spacer(modifier = Modifier.height(36.dp))
                        DialogButton(
                            initialColor = color,
                            onColorChanged = {
                                color = it
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DialogButton(
    initialColor: Color,
    onColorChanged: (Color) -> Unit,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    Button(
        onClick = {
            showDialog = true
        },
    ) {
        Text(text = "Show dialog")
    }
    val state = rememberAlwanState(initialColor = initialColor)
    if (showDialog) {
        AlwanDialog(
            onColorChanged = {},
            state = state,
            onDismissRequest = {
                showDialog = false
            },
            showAlphaSlider = true,
            positiveButtonText = "OK",
            onPositiveButtonClick = {
                showDialog = false
                onColorChanged(state.color)
            },
            negativeButtonText = "Cancel",
            onNegativeButtonClick = {
                showDialog = false
            },
        )
    }
}

@Composable
private fun TransparencyGrid(modifier: Modifier) {
    val backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    val squaresColor = MaterialTheme.colors.onSurface.copy(alpha = 1f)
    Canvas(
        modifier = modifier,
        onDraw = {
            drawTransparencyGrid(
                backgroundColor,
                squaresColor
            )
        },
    )
}

private fun DrawScope.drawTransparencyGrid(
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
