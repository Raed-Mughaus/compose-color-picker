package com.raedapps.alwan.ktx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.AwaitPointerEventScope

internal val AwaitPointerEventScope.currentPosition: Offset
    get() = currentEvent.changes.first().position

internal val AwaitPointerEventScope.currentPointersCount: Int
    get() = currentEvent.changes.size

internal fun AwaitPointerEventScope.consumeCurrent() {
    currentEvent.changes.first().consume()
}
