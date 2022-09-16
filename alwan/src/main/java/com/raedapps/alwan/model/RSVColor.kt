package com.raedapps.alwan.model

import androidx.compose.ui.graphics.Color
import com.raedapps.alwan.rotationToHue
import kotlin.math.pow

private val MAX_PACKED_VALUE = 2f.pow(16).toInt() - 1

/**
 * RSV stands for Rotation Saturation Value
 */
@JvmInline
internal value class RSVColor private constructor(
    private val value: ULong,
) {

    constructor(
        h: Float,
        s: Float,
        v: Float,
    ) : this(pack(h, s, v))

    val r get() = 360 * ((value shr 32) and 0XFFFFu).toFloat() / MAX_PACKED_VALUE

    val s get() = ((value shr 16) and 0XFFFFu).toFloat() / MAX_PACKED_VALUE

    val v get() = (value and 0XFFFFu).toFloat() / MAX_PACKED_VALUE

    fun toColor(): Color {
        return Color.hsv(
            rotationToHue(r),
            s,
            v,
        )
    }

    fun copy(
        h: Float = this.r,
        s: Float = this.s,
        v: Float = this.v,
    ): RSVColor {
        return RSVColor(h, s, v)
    }

    override fun toString() = "HSV(h = $r, s = ${(100_00 * s).toInt() / 100}, v = ${(100_00 * v).toInt() / 100})"

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
