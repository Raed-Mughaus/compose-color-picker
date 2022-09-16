package com.raedapps.alwan.renderer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp
import com.raedapps.alwan.STEP_HUE_LIST
import com.raedapps.alwan.ktx.addCircle
import com.raedapps.alwan.model.HSVColorPickerUIData
import com.raedapps.alwan.model.RSVColor
import com.raedapps.alwan.toRadians
import kotlin.math.cos
import kotlin.math.sin

private val STEP_COLOR_LIST = STEP_HUE_LIST
    .map { (step, hue) ->
        Pair(
            step,
            Color.hsv(hue, 1f, 1f),
        )
    }
    .reversed()
    .toTypedArray()

internal fun DrawScope.drawHueCircle(
    uiData: HSVColorPickerUIData,
) {
    val path = Path()
    path.addCircle(uiData.hueInnerCircle)
    clipPath(path, ClipOp.Difference) {
        val outerCircle = uiData.hueOuterCircle
        drawCircle(
            Brush.sweepGradient(*STEP_COLOR_LIST),
            outerCircle.radius,
            outerCircle.center,
        )
    }
}

internal fun DrawScope.drawHueIndicator(
    uiData: HSVColorPickerUIData,
    selectedColor: RSVColor,
    pressed: Boolean,
) {
    val outerCircle = uiData.hueOuterCircle
    val innerCircle = uiData.hueInnerCircle
    val radius = (outerCircle.radius + innerCircle.radius) / 2f
    val angleRadians = toRadians(selectedColor.r)
    drawSelectionIndicator(
        center = Offset(
            center.x + radius * cos(angleRadians),
            center.y - radius * sin(angleRadians),
        ),
        radius = (outerCircle.radius - innerCircle.radius - 8.dp.toPx()) / 2f,
        color = selectedColor.copy(s = 1f, v = 1f).toColor(),
        active = pressed,
    )
}
