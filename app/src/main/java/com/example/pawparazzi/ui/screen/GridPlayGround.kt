@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalGridApi::class)

package com.example.pawparazzi.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.pawparazzi.exercises.AdaptiveGridConfiguration
import com.example.pawparazzi.ui.theme.FredokaFontFamily


@Composable
fun GridPlayGround() {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Grid Playground",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FredokaFontFamily,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        )
        AdaptiveGridConfiguration()
    }
}