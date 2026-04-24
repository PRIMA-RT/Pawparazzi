@file:OptIn(ExperimentalMediaQueryApi::class)

package com.example.pawparazzi.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.example.pawparazzi.R
import com.example.pawparazzi.ui.theme.FredokaFontFamily

@Composable
private fun MenuIcon() {
    Image(
        imageVector = Icons.Filled.Menu,
        contentDescription = "Menu",
        modifier = Modifier
            .size(48.dp)
            .padding(12.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
    )
}

@Composable
private fun ProfileIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_pawparazzi_logo),
        contentDescription = "App Icon",
        modifier = Modifier
            .padding(12.dp)
            .size(48.dp)
    )
}

@Preview(device = PIXEL_TABLET, uiMode = UI_MODE_NIGHT_YES)
@Preview(device = PIXEL_9_PRO, uiMode = UI_MODE_NIGHT_YES)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val isAtLeastMedium by derivedMediaQuery { windowWidth >= WIDTH_DP_MEDIUM_LOWER_BOUND.dp }

    if (isAtLeastMedium) {
        MenuIcon()
    } else {
        TopAppBar(
            actions = { ProfileIcon() },
            title = {
                Text(
                    text = "Pawparazzi",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FredokaFontFamily,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        )
    }
}