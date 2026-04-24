package com.example.pawparazzi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND

@OptIn(ExperimentalMediaQueryApi::class)
@Composable
fun calculateNumberOfColumns(isDetailOpen: Boolean) : Int {
    val isAtLeastLarge by derivedMediaQuery { windowWidth >= 1200.dp }
    val isAtLeastMedium by derivedMediaQuery { windowWidth >= WIDTH_DP_EXPANDED_LOWER_BOUND.dp }

    return when {
        isAtLeastLarge && !isDetailOpen -> 5
        isAtLeastLarge || (isAtLeastMedium && !isDetailOpen) -> 3
        else -> 2
    }
}
