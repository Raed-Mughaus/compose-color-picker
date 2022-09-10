package com.raedapps.alwan.event.handler

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerInputScope
import com.raedapps.alwan.ktx.consumeCurrent
import com.raedapps.alwan.ktx.currentPosition
import com.raedapps.alwan.ktx.currentPointersCount

internal suspend fun PointerInputScope.detectAlphaChangeEvent(
    onChangeStart: () -> Unit,
    onAlphaChanged: (Float) -> Unit,
    onChangeEnd: () -> Unit,
) = awaitPointerEventScope {
    while (true) {
        awaitFirstDown()
        if (currentPointersCount != 1) {
            continue
        }
        onChangeStart()
        while (true) {
            val alpha = (currentPosition.x / size.width).coerceIn(0f..1f)
            consumeCurrent()
            onAlphaChanged(alpha)
            awaitPointerEvent()
            if (currentEvent.changes.size != 1) {
                continue
            }
            if (currentEvent.type != PointerEventType.Move) {
                break
            }
        }
        onChangeEnd()
    }
}
