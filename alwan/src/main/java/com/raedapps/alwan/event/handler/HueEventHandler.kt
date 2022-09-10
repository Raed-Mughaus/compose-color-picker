package com.raedapps.alwan.event.handler

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerInputScope
import com.raedapps.alwan.angle
import com.raedapps.alwan.ktx.consumeCurrent
import com.raedapps.alwan.ktx.currentPosition
import com.raedapps.alwan.ktx.currentPointersCount
import com.raedapps.alwan.model.Circle

internal suspend fun PointerInputScope.detectHueChangeEvent(
    outerCircle: Circle,
    innerCircle: Circle,
    onChangeStart: () -> Unit,
    onHueChanged: (Float) -> Unit,
    onChangeEnd: () -> Unit,
) = awaitPointerEventScope {
    while (true) {
        awaitFirstDown()
        if (currentPointersCount != 1) {
            continue
        }
        if (!outerCircle.contains(currentPosition) || innerCircle.contains(currentPosition)) {
            continue
        }
        onChangeStart()
        while (true) {
            val hue = angle(currentPosition - outerCircle.center)
            consumeCurrent()
            if (hue != null) {
                onHueChanged(hue)
            }
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
