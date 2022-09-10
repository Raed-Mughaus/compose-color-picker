package com.raedapps.alwan

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.raedapps.alwan.ktx.toHSV

data class AlwanState(
    val initialColor: Color,
) {

    internal var hsvColor by mutableStateOf(initialColor.toHSV())

    internal var alpha by mutableStateOf(initialColor.alpha)

    var color: Color
        get() = hsvColor.toColor().copy(alpha = alpha)
        set(value) {
            hsvColor = value.toHSV()
            alpha = value.alpha
        }

}

@Composable
fun rememberAlwanState(
    initialColor: Color,
): AlwanState {
    return remember {
        AlwanState(initialColor)
    }
}