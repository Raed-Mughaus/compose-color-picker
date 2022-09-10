package com.raedapps.alwan.model

import androidx.compose.ui.geometry.Offset
import com.raedapps.alwan.ktx.distanceTo

data class Circle(
    val center: Offset,
    val radius: Float,
) {

    fun contains(point: Offset) = point.distanceTo(center) <= radius

}
