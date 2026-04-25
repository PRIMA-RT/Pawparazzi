@file:OptIn(ExperimentalGridApi::class)

package com.example.pawparazzi.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiMediaScope
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.mediaQuery
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9
import androidx.compose.ui.tooling.preview.Devices.PIXEL_FOLD
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pawparazzi.ui.theme.LocalCustomColors
import com.example.pawparazzi.ui.theme.pastelColors

@OptIn(ExperimentalMediaQueryApi::class)
@Preview(device = PIXEL_FOLD, name = "Pixel Fold")
@Preview(device = PIXEL_9, name = "Pixel phone")
@Composable
fun AdaptiveGridConfiguration() {
    val lessThan600 = derivedMediaQuery {   windowWidth < 600.dp }
    val lessThan800 = derivedMediaQuery {   windowWidth < 800.dp }

    Grid(
        config = {
            val maxWidthDp = constraints.maxWidth.toDp()
            var (cols, rows) = 0 to 0

            // width is less than 600, 2 to 4
            if(lessThan600.value){
                cols = 2
                rows = 4
            }else if(lessThan800.value){
                cols = 3
                rows = 3
            }else{
                cols = 4
                rows = 2
            }

            println("Max width: $maxWidthDp, cols: $cols, rows: $rows")

            val gapSizeDp = 8.dp
            val availableWidthForColumns = maxWidthDp.value - (cols - 1) * gapSizeDp.value
            val cellSize = availableWidthForColumns / cols

            repeat(cols) { column(cellSize.dp) }
            repeat(rows) { row(cellSize.dp) }
            gap(gapSizeDp)
        },
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        pastelColors.forEachIndexed { index, color ->

            val modifier = if (index == 0) {
                Modifier.gridItem(rowSpan = 2, columnSpan = 2)
            } else {
                Modifier
            }

            ColoredBox(color = color, modifier = modifier,)
        }
    }
}

@Composable
private fun ColoredBox(modifier: Modifier = Modifier, color: Color) {
    Box(
        modifier
            .fillMaxSize()
            .background(color = color, shape = RoundedCornerShape(size = 16.dp))
            .border(
                width = 2.dp,
                color = LocalCustomColors.current.onBackground,
                shape = RoundedCornerShape(size = 16.dp)
            )
    )
}
