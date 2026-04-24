@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pawparazzi.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiMediaScope
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.mediaQuery
import androidx.compose.ui.unit.dp
import com.example.pawparazzi.data.Tags
import com.example.pawparazzi.exercises.FocusSample
import com.example.pawparazzi.ui.component.TagButton
import com.example.pawparazzi.ui.theme.FredokaFontFamily
import com.example.pawparazzi.ui.theme.LocalCustomColors

@Composable
fun MediaQueryPlayGround() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "MediaQuery Playground",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FredokaFontFamily,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        )

        FocusSample()
    }
}