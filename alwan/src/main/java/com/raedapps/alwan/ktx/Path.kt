package com.raedapps.alwan.ktx

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import com.raedapps.alwan.model.Circle

internal fun Path.addCircle(circle: Circle) {
    val rect = Rect(circle.center, circle.radius)
    addOval(rect)
}