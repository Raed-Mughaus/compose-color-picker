package com.raedapps.alwan

import androidx.compose.ui.geometry.Offset
import kotlin.math.*

internal fun toRadians(degrees: Float) = PI.toFloat() * degrees / 180

internal fun toDegrees(radians: Float) = 180 * radians / PI.toFloat()

internal fun angle(point: Offset): Float? {
    return if (point.x != 0f) {
        val m = point.y / point.x
        val angle = toDegrees(atan(m))
        when {
            point.x > 0 && point.y >= 0 -> angle
            point.x < 0 && point.y >= 0 -> 180 + angle
            point.x < 0 && point.y < 0 -> 180 + angle
            point.x > 0 && point.y < 0 -> 360 + angle
            else -> throw RuntimeException()
        }
    } else if (point.y > 0) {
        90f
    } else if (point.y < 0) {
        270f
    } else {
        null
    }
}

internal fun lerp(a: Float, b: Float, t: Float) = a + t * (b - a)
