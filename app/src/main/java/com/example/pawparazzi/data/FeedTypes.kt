package com.example.pawparazzi.data

import androidx.compose.ui.graphics.vector.ImageVector

data class FeedCardItem(
    val title: String,
    val image: Int,
    val subtitle: String,
    val label: String,
    val id: Int
)

data class SideItem(
    val icon: ImageVector,
    val label: String
)

data class PostDetail(
    val username: String,
    val initial: String = username.first().uppercase(),
    val feedCardItem: FeedCardItem
)
