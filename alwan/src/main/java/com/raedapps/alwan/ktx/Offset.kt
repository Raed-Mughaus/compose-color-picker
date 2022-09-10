package com.raedapps.alwan.ktx

import androidx.compose.ui.geometry.Offset
import kotlin.math.sqrt

internal fun Offset.distanceTo(offset: Offset): Float {
    val dx = offset.x - x
    val dy = offset.y - y
    return sqrt(dx * dx + dy * dy)
}
