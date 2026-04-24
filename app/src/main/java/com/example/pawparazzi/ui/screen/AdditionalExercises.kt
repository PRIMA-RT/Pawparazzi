package com.example.pawparazzi.ui.screen

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND
import com.example.pawparazzi.FlexboxPlayground
import com.example.pawparazzi.GridPlayground
import com.example.pawparazzi.MQPlayground
import com.example.pawparazzi.ui.theme.LocalCustomColors

@OptIn(ExperimentalMediaQueryApi::class, ExperimentalGridApi::class)
@Composable
fun AdditionalExercises(
    modifier: Modifier = Modifier, onClick: (NavKey) -> Unit
) {

    val shouldShowMoreColumns by derivedMediaQuery { windowWidth >= WIDTH_DP_EXPANDED_LOWER_BOUND.dp }
    Grid(config = {
        if (shouldShowMoreColumns) {
            column(.3.fr)
            column(.3.fr)
            column(.3.fr)
            row(1.fr)
        } else {
            column(.5.fr)
            column(.5.fr)
            row(.7.fr)
            row(.3.fr)
            rowGap(16.dp)
        }

        columnGap(8.dp)
    }, modifier = modifier) {

        Card(label = "FlexBox Playground", FlexboxPlayground, onClick)
        Card(label = "Grid Playground", GridPlayground, onClick)

        val mqModifier = if(shouldShowMoreColumns) {
            Modifier
        } else {
            Modifier.gridItem(columnSpan = 2)
        }

        Card(label = "MediaQuery Playground", MQPlayground, onClick, modifier = mqModifier)
    }
}

@Composable
private fun Card(label: String, key: NavKey, onClick: (NavKey) -> Unit, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .border(
                width = 3.dp,
                color = LocalCustomColors.current.onBackground,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .background(
                color = LocalCustomColors.current.background,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .clickable(onClick = { onClick(key) }),
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = LocalCustomColors.current.onBackground)
    }
}