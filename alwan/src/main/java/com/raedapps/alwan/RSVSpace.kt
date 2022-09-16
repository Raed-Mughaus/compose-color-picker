package com.raedapps.alwan

/**
 * RSV is a modified version of HSV.
 */

private val ANGLES = listOf(0, 120, 160, 210, 260, 310, 360)

internal val STEP_ROTATION_LIST = ANGLES
    .mapIndexed { i, angle ->
        Pair(
            1 - i * 60 / 360f,
            angle.toFloat(),
        )
    }

internal val STEP_HUE_LIST = ANGLES
    .mapIndexed { i, angle ->
        Pair(
            1 - angle / 360f,
            i * 60f,
        )
    }

internal fun hueToRotation(h: Float): Float {
    val step = 1 - h / 360
    val (previousStep, previousHue) = STEP_ROTATION_LIST
        .filter { it.first <= step }
        .maxBy { it.first }
    val (nextStep, nextHue) = STEP_ROTATION_LIST
        .filter { it.first >= step }
        .minBy { it.first }
    return if (previousStep == nextStep) {
        previousHue
    } else {
        lerp(
            previousHue,
            nextHue,
            (step - previousStep) / (nextStep - previousStep),
        )
    }
}

internal fun rotationToHue(r: Float): Float {
    val step = 1 - r / 360
    val (previousStep, previousHue) = STEP_HUE_LIST
        .filter { it.first <= step }
        .maxBy { it.first }
    val (nextStep, nextHue) = STEP_HUE_LIST
        .filter { it.first >= step }
        .minBy { it.first }
    return if (previousStep == nextStep) {
        previousHue
    } else {
        lerp(
            previousHue,
            nextHue,
            (step - previousStep) / (nextStep - previousStep),
        )
    }
}
