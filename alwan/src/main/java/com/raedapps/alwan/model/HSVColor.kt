package com.raedapps.alwan.model

import androidx.compose.ui.graphics.Color
import com.raedapps.alwan.lerp
import kotlin.math.pow

private val MAX_PACKED_VALUE = 2f.pow(16).toInt() - 1

@JvmInline
internal value class HSVColor private constructor(
    private val value: ULong,
) {

    constructor(
        h: Float,
        s: Float,
        v: Float,
    ) : this(pack(h, s, v))

    val h get() = 360 * ((value shr 32) and 0XFFFFu).toFloat() / MAX_PACKED_VALUE

    val s get() = ((value shr 16) and 0XFFFFu).toFloat() / MAX_PACKED_VALUE

    val v get() = (value and 0XFFFFu).toFloat() / MAX_PACKED_VALUE

    fun toColor(): Color {
        val step = 1 - h / 360
        val (previousStep, previousHue) = STEP_HUE_LIST
            .filter { it.first <= step }
            .maxBy { it.first }
        val (nextStep, nextHue) = STEP_HUE_LIST
            .filter { it.first >= step }
            .minBy { it.first }
        return Color.hsv(
            if (previousStep == nextStep) {
                previousHue
            } else {
                lerp(
                    previousHue,
                    nextHue,
                    (step - previousStep) / (nextStep - previousStep),
                )
            },
            s,
            v,
        )
    }

    fun copy(
        h: Float = this.h,
        s: Float = this.s,
        v: Float = this.v,
    ): HSVColor {
        return HSVColor(h, s, v)
    }

    override fun toString() = "HSV(h = $h, s = ${(100_00 * s).toInt() / 100}, v = ${(100_00 * v).toInt() / 100})"

}

private fun pack(
    h: Float,
    s: Float,
    v: Float,
): ULong {
    require(h in 0f..360f && s in 0f..1f && v in 0f..1f)
    val uH = ((h / 360) * MAX_PACKED_VALUE).toULong()
    val uS = (s * MAX_PACKED_VALUE).toULong()
    val uB = (v * MAX_PACKED_VALUE).toULong()
    return (uH shl 32) or (uS shl 16) or uB
}
