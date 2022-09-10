package com.raedapps.alwan.event.handler

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerInputScope
import com.raedapps.alwan.ktx.consumeCurrent
import com.raedapps.alwan.ktx.currentPointersCount
import com.raedapps.alwan.ktx.currentPosition

internal suspend fun PointerInputScope.detectSaturationValueChange(
    rect: Rect,
    onChangeStart: () -> Unit,
    onSaturationValueChanged: (s: Float, v: Float) -> Unit,
    onChangeEnd: () -> Unit,
) = awaitPointerEventScope {
    while (true) {
        awaitFirstDown()
        if (currentPointersCount != 1) {
            continue
        }
        if (!rect.contains(currentPosition)) {
            continue
        }
        onChangeStart()
        while (true) {
            val saturation = ((currentPosition.x - rect.left) / rect.width).coerceIn(0f, 1f)
            val value = ((rect.bottom - currentPosition.y) / rect.height).coerceIn(0f, 1f)
            onSaturationValueChanged(saturation, value)
            consumeCurrent()
            awaitPointerEvent()
            if (currentPointersCount != 1) {
                continue
            }
            if (currentEvent.type != PointerEventType.Move) {
                break
            }
        }
        onChangeEnd()
    }
}
