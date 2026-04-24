@file:OptIn(ExperimentalFlexBoxApi::class)

package com.example.pawparazzi.ui.component

import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.FlexBox
import androidx.compose.foundation.layout.FlexBoxScope
import androidx.compose.foundation.layout.FlexDirection
import androidx.compose.foundation.layout.FlexJustifyContent
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


@Preview
@Composable
fun FilterList(modifier: Modifier = Modifier, onItemSelected: (Tags) -> Unit = {}) {

    var lastSelected by remember { mutableStateOf(Tags.Home)}

    FlexBox(modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp), config = {
        wrap(FlexWrap.Wrap)
        direction(FlexDirection.Row)
        justifyContent(FlexJustifyContent.Start)
        rowGap(12.dp)
    }) {
        Tags.entries.forEach { tag ->
            TagButton(text = tag.displayLabel, isActive = tag == lastSelected, onClick = {
                if(tag != lastSelected) {
                    lastSelected = tag
                    onItemSelected(tag)
                }
            },
                modifier = Modifier.flex {
                    grow(1f)
                }
            )
        }
    }
}

@Composable
fun FlexBoxScope.FilterListContent(
    lastSelected: Tags,
    onItemSelected: (Tags) -> Unit
) {
    var lastSelected1 = lastSelected
    Tags.entries.forEach { tag ->
        TagButton(
            text = tag.displayLabel, isActive = tag == lastSelected1, onClick = {
                if (tag != lastSelected1) {
                    lastSelected1 = tag
                    onItemSelected(tag)
                }
            },
            modifier = Modifier.flex {
                grow(1f)
            }
        )
    }
}