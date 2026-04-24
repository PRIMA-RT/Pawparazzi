package com.example.pawparazzi.exercises

import androidx.compose.foundation.border
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.pawparazzi.ui.component.TagButton
import com.example.pawparazzi.ui.theme.FredokaFontFamily
import com.example.pawparazzi.ui.theme.LocalCustomColors

@OptIn(ExperimentalMediaQueryApi::class)
@Composable
fun FocusSample() {
    var lastSelected by remember { mutableStateOf(Tags.Home) }
    val isKeyboardAttached = false // TODO Insert MediaQuery here

    Text(
        text = "Focus sample", style = MaterialTheme.typography.bodyLarge,
        fontFamily = FredokaFontFamily,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(vertical = 24.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .focusGroup()
    ) {
        Tags.entries.forEach { tag ->
            var isFocused by remember { mutableStateOf(false) }
            val borderColor = if (isFocused && isKeyboardAttached) {
                LocalCustomColors.current.secondary
            } else {
                Color.Transparent
            }
            TagButton(
                text = tag.displayLabel, isActive = tag == lastSelected, onClick = {
                    if (tag != lastSelected) {
                        lastSelected = tag
                    }
                }, modifier = Modifier
                    .onFocusChanged {
                        isFocused = it.isFocused
                    }
                    .border(width = 4.dp, color = borderColor, shape = RoundedCornerShape(24.dp))
                    .focusable())
        }
    }
}