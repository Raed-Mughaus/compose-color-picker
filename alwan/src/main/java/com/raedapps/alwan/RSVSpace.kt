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

internal fun hueToRotation(h: Float) = map(h, STEP_ROTATION_LIST)

internal fun rotationToHue(r: Float) = map(r, STEP_HUE_LIST)

private fun map(x: Float, stepXList: List<Pair<Float, Float>>): Float {
    val step = 1 - x / 360
    val (previousStep, previousHue) = stepXList
        .filter { it.first <= step }
        .maxBy { it.first }
    val (nextStep, nextHue) = stepXList
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
