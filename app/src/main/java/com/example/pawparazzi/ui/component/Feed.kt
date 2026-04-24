package com.example.pawparazzi.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pawparazzi.data.FeedCardItem
import com.example.pawparazzi.data.mockFeed
import com.example.pawparazzi.ui.calculateNumberOfColumns

@Composable
fun FeedCard(
    feedCardItem: FeedCardItem,
    modifier: Modifier = Modifier,
    onClick: (FeedCardItem) -> Unit = {}
) {
    Box(
        modifier = modifier
            .aspectRatio(186f / 326f)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .clickable { onClick(feedCardItem) }
    ) {
        if (feedCardItem.image != 0) {
            Image(
                painter = painterResource(id = feedCardItem.image),
                contentDescription = feedCardItem.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Tag at the top
        Box(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.TopStart)
        ) {
            AssistChip(
                onClick = { /* Handle click */ },
                label = {
                    Text(
                        text = feedCardItem.label,
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                shape = RoundedCornerShape(4.dp),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f),
                    labelColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                border = null
            )
        }

        // Caption at the bottom with white gradient
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.7f),
                            MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.8f)
                        )
                    )
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = feedCardItem.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = feedCardItem.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun Feed(feed: List<FeedCardItem>, isDetailOpen: Boolean, modifier: Modifier = Modifier, onClick: (FeedCardItem) -> Unit = {}) {
    val numberOfColumns = calculateNumberOfColumns(isDetailOpen)

    LazyVerticalGrid(
        columns = GridCells.Fixed(numberOfColumns),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(feed) {
            FeedCard(feedCardItem = it, onClick = onClick, modifier = Modifier.padding(4.dp))
        }
    }
}


@Preview(device = PIXEL_TABLET)
@Preview(device = PIXEL_9_PRO)
@Composable
fun FeedNoDetailPreview(
    modifier: Modifier = Modifier
) {
    Feed(
        feed = mockFeed,
        isDetailOpen = false,
        modifier = modifier
    )
}

@Preview(device = PIXEL_TABLET)
@Preview(device = PIXEL_9_PRO)
@Composable
fun FeedWithDetailPreview(
    modifier: Modifier = Modifier
) {
    Feed(
        feed = mockFeed,
        isDetailOpen = true,
        modifier = modifier
    )
}


