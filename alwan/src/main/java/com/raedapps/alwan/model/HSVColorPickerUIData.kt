package com.raedapps.alwan.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos

private const val HSV_WEIGH = 0.7f

internal class HSVColorPickerUIData(
    hsvSize: Int,
) {

    val hueOuterCircle = run {
        val radius = hsvSize / 2f
        Circle(
            Offset(radius, radius),
            radius,
        )
    }

    val hueInnerCircle = Circle(
        hueOuterCircle.center,
        HSV_WEIGH * hueOuterCircle.radius
    )

    val saturationValueRect = run {
        rectInside(
            Circle(
                hueInnerCircle.center,
                0.95f * hueInnerCircle.radius,
            ),
        )
    }

}

private fun rectInside(circle: Circle): Rect {
    val size = abs(circle.radius * cos(0.25f * PI)).toFloat()
    val topLeft = circle.center - Offset(size, size)
    return Rect(topLeft, Size(2 * size, 2 * size))
}
