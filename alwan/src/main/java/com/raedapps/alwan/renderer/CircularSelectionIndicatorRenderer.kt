package com.raedapps.alwan.renderer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

internal fun DrawScope.drawSelectionIndicator(
    center: Offset,
    radius: Float,
    color: Color,
    active: Boolean,
    boundary: Rect? = null,
) {
    val constrainedCenter = if (boundary != null &&
        boundary.width > 2 * radius &&
        boundary.height > 2 * radius) {
        Offset(
            center.x.coerceIn(boundary.left + radius, boundary.right - radius),
            center.y.coerceIn(boundary.top + radius, boundary.bottom - radius),
        )
    } else {
        center
    }
    val radiusScale = if (active) {
        1.4f
    } else {
        1f
    }
    val scaledRadius = radiusScale * radius
    drawCircle(color, scaledRadius, constrainedCenter)
    drawCircle(Color.Black, scaledRadius, constrainedCenter, style = Stroke(width = 2.dp.toPx()))
    drawCircle(Color.White, scaledRadius, constrainedCenter, style = Stroke(width = 1.dp.toPx()))
}
