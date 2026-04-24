@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.example.pawparazzi.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.StyleScope
import androidx.compose.foundation.style.pressed
import androidx.compose.foundation.style.selected
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.LocalUiMediaScope
import androidx.compose.ui.UiMediaScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMediaQueryApi::class)
fun StyleScope.mediaQueryBasedSize() {
    val isPointerPrecise = LocalUiMediaScope.currentValue.pointerPrecision == UiMediaScope.PointerPrecision.Fine

    if (isPointerPrecise) {
        minHeight(24.dp)
        minWidth(24.dp)
    } else {
        minHeight(48.dp)
        minWidth(48.dp)
    }
}

fun createButtonStyle(
    containerColor: Color,
    contentColor: Color,
    selectedContainerColor: Color,
    selectedContentColor: Color
) = Style {
    fontSize(14.sp)
    externalPadding(4.dp)
    contentPadding(horizontal = 24.dp, vertical = 10.dp)
    shape(RoundedCornerShape(24.dp))
    pressed {
        background(Color.DarkGray)
        alpha(0.8f)
    }
    //default state
    background(containerColor)
    contentColor(contentColor)
    selected {
        background(selectedContainerColor)
        contentColor(selectedContentColor)
    }
}

// Keep a default for compatibility, but it will be overridden in FurBookTheme
val buttonStyle = createButtonStyle(
    containerColor = SecondaryContainerDark,
    contentColor = OnSecondaryContainerDark,
    selectedContainerColor = PrimaryDark,
    selectedContentColor = OnPrimaryDark
)
