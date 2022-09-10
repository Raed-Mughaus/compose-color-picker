package com.raedapps.alwan.ktx

import android.graphics.Color.colorToHSV
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.raedapps.alwan.model.HSVColor

internal fun Color.toHSV(): HSVColor {
    val hsv = FloatArray(3)
    colorToHSV(toArgb(), hsv)
    return HSVColor(
        hsv[0],
        hsv[1],
        hsv[2],
    )
}
