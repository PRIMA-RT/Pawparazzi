@file:OptIn(ExperimentalMediaQueryApi::class)

package com.example.pawparazzi.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.example.pawparazzi.data.MockReelRepository
import com.example.pawparazzi.data.PostDetail
import com.example.pawparazzi.data.SideItem
import com.example.pawparazzi.data.mockReelDetailSideItems

@OptIn(ExperimentalGridApi::class, ExperimentalFlexBoxApi::class)
@Composable
fun ReelDetailScreen(id: Int, onBack: () -> Unit, modifier: Modifier = Modifier) {

    val isAtLeastMedium by derivedMediaQuery { windowWidth >= WIDTH_DP_MEDIUM_LOWER_BOUND.dp }
    val mockDetail = MockReelRepository.fetchPostById(id)
    var liked by remember { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        if (mockDetail.feedCardItem.image != 0) {
            Image(
                painter = painterResource(id = mockDetail.feedCardItem.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        IconButton(
            onClick = onBack,
            modifier = Modifier
                .safeDrawingPadding()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.tertiaryContainer, shape = CircleShape)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = if(isAtLeastMedium) Icons.Filled.Close else Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Box(
            modifier = Modifier
                .safeDrawingPadding()
                .padding(16.dp)
                .background(
                    if (liked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                )
                .clickable { liked = !liked }
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = if (liked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Like",
                tint = if(liked) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(24.dp)
            )
        }

        Grid(
            config = {
                column(.95.fr)
                column(.05.fr)
                row(1.fr)
                columnGap(16.dp)
            }, modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
        ) {
            ProfileComposable(mockDetail, modifier = modifier.gridItem(column = 1))
            SideItemBar(mockReelDetailSideItems, modifier.gridItem(column = 2))
        }
    }
}

@OptIn(ExperimentalFlexBoxApi::class, ExperimentalGridApi::class)
@Composable
private fun ProfileComposable(post: PostDetail, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        Grid(
            config = {
                column(32.dp)
                column(1.fr)
                row(32.dp)
                row(48.dp)
                columnGap(16.dp)
                rowGap(16.dp)
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Blue, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = post.initial,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                Text(
                    text = post.username,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(post.feedCardItem.title)
                    }
                    appendLine()
                    append(post.feedCardItem.subtitle)
                },
                color = Color.White,
                modifier = Modifier.gridItem(columnSpan = 2),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun SideItemBar(items: List<SideItem>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .background(Color(0x661A1111), shape = RoundedCornerShape(16.dp))
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEach {
                SideItemComposable(icon = it.icon, label = it.label)
            }
        }
    }
}

@Composable
private fun SideItemComposable(icon: ImageVector, label: String) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = icon, contentDescription = label, modifier = Modifier.size(16.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = Modifier.size(width = 16.dp, height = 4.dp))
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview
@Composable
fun ReelDetailPreview() {
    ReelDetailScreen(id = 0, onBack = {})
}