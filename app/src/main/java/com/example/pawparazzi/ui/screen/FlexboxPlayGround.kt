@file:OptIn(ExperimentalFlexBoxApi::class, ExperimentalGridApi::class)

package com.example.pawparazzi.ui.screen

import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.pawparazzi.exercises.FilterList

@OptIn(ExperimentalFlexBoxApi::class, ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun FlexboxPlayGround() {
    FilterList()
}
