package com.example.pawparazzi.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.MutableStyleState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pawparazzi.ui.theme.LocalAppStyles


@ExperimentalFoundationStyleApi
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: Style = Style,
    selected: Boolean = true,
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    content: @Composable RowScope.() -> Unit
) {
    val styleState = remember(interactionSource) {
        MutableStyleState(interactionSource)
    }
    styleState.isSelected = selected
    Row(
        modifier = modifier
            .hoverable( interactionSource = interactionSource)
            .focusable(true, interactionSource = interactionSource)
            .clickable(
                enabled = true,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null,
            )
            .styleable(styleState, LocalAppStyles.current.baseButtonStyle, style),
        content = content,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun TagButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = onClick,
        selected = isActive,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun TagButtonPreview() {
    Column {
        TagButton(text = "Active", onClick = {}, isActive = true)
        TagButton(text = "Inactive", onClick = {}, isActive = false)
    }
}


