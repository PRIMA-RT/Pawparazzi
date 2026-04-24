package com.example.pawparazzi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.example.pawparazzi.MainViewModel
import com.example.pawparazzi.data.FeedCardItem
import com.example.pawparazzi.data.mockFeed
import com.example.pawparazzi.ui.component.Feed
import com.example.pawparazzi.ui.component.FilterList
import com.example.pawparazzi.ui.theme.BackgroundColor


@OptIn(ExperimentalMediaQueryApi::class)
@Composable
fun ReelsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    onClick: (FeedCardItem) -> Unit
) {
    val isAtLeastMedium by derivedMediaQuery { windowWidth >= WIDTH_DP_MEDIUM_LOWER_BOUND.dp }

    val appliedModifier = if (isAtLeastMedium) {
        modifier.padding(innerPadding)
    } else {
        modifier
    }

    Column(
        appliedModifier
            .fillMaxSize()
            .background(
                BackgroundColor
            )
    ) {
        FilterList { }
        Feed(
            mockFeed,
            isDetailOpen = viewModel.isDetailOpen,
            modifier = Modifier
                .padding(vertical = 16.dp),
            onClick = {
                onClick(it)
                viewModel.isDetailOpen = true
            }
        )
    }
}