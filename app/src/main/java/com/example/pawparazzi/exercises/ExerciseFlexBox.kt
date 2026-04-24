package com.example.pawparazzi.exercises

import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.FlexBox
import androidx.compose.foundation.layout.FlexWrap
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pawparazzi.data.Tags
import com.example.pawparazzi.ui.component.FilterListContent

@Preview
@Composable
@OptIn(ExperimentalFlexBoxApi::class)
fun FilterList(modifier: Modifier = Modifier, onItemSelected: (Tags) -> Unit = {}) {

    var lastSelected by remember { mutableStateOf(Tags.Home)}

    FlexBox(modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp), config = {
        wrap(FlexWrap.Wrap)
    }) {
        FilterListContent(lastSelected, onItemSelected)
    }
}