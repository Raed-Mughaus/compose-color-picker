package com.raedapps.alwan.ktx

import android.graphics.Color.colorToHSV
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.raedapps.alwan.hueToRotation
import com.raedapps.alwan.model.RSVColor

internal fun Color.toHSV(): RSVColor {
    val hsv = FloatArray(3)
    colorToHSV(toArgb(), hsv)
    return RSVColor(
        hueToRotation(
            hsv[0],
        ),
        hsv[1],
        hsv[2],
    )
}
