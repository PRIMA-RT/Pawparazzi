package com.example.pawparazzi.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.TagFaces
import androidx.compose.material.icons.outlined.ThumbUp
import com.example.pawparazzi.R

enum class Tags(val displayLabel: String) {
    Home("Home"),
    Popular("Popular"),
    ForYou("For You"),
    Cats("Cats"),
    OutAndAbout("Out and About"),
    Chill("chill"),
    SoFluffy("So Fluffy!"),
    DemandingTreats("Demanding treats"),
    Dogs("Dogs")
}

private data class RawFeedItem(
    val imageName: String,
    val subtitle: String,
    val label: String,
    val drawableRes: Int
)

private val rawMockData = listOf(
    RawFeedItem("caren", "A curious cat exploring the garden", "#dog", R.drawable.caren),
    RawFeedItem("cleo", "Sleeping peacefully on the sofa", "#dog", R.drawable.cleo),
    RawFeedItem("diana", "Ready for a walk in the park", "#dog", R.drawable.diana),
    RawFeedItem("donovan", "Watching birds from the window", "#dog", R.drawable.donovan),
    RawFeedItem("francesco", "Enjoying some treats", "#cat", R.drawable.francesco),
    RawFeedItem("francesco2", "Playful moment in the living room", "#cat", R.drawable.francesco2),
    RawFeedItem("jan", "A majestic look from a loyal friend", "#dog", R.drawable.jan),
    RawFeedItem("nevin", "Chasing shadows on the wall", "#cat", R.drawable.nevin),
    RawFeedItem("rebecca", "Sunbathing in the balcony", "#dog", R.drawable.rebecca),
    RawFeedItem("rebecca2", "Intrigued by a new toy", "#dog", R.drawable.rebecca2),
    RawFeedItem("rob", "Guard dog on duty", "#cat", R.drawable.rob),
    RawFeedItem("sabs", "Purring loudly after a nap", "#cat", R.drawable.sabs),
    RawFeedItem("saryong", "Waiting for dinner time", "#dog", R.drawable.saryong),
    RawFeedItem("saryong2", "Happy face after a long run", "#cat", R.drawable.saryong2),
    RawFeedItem("tram-miso", "Tiny kitten with big eyes", "#cat", R.drawable.tram_miso)
)

val mockFeed = rawMockData.mapIndexed { index, item ->
    FeedCardItem(
        title = item.imageName.replaceFirstChar { it.uppercase() },
        subtitle = item.subtitle,
        image = item.drawableRes,
        label = item.label,
        id = index
    )
}

val mockReelDetailSideItems = listOf(
    SideItem(icon = Icons.Outlined.ThumbUp, label = "12K"),
    SideItem(icon = Icons.AutoMirrored.Outlined.Message, label = "8K"),
    SideItem(icon = Icons.Outlined.Share, label = "16K"),
    SideItem(icon = Icons.Outlined.TagFaces, label = "20K")
)

object MockReelRepository {
    fun fetchPostById(id: Int): PostDetail {
        return PostDetail(
            username = "username",
            feedCardItem = mockFeed.getOrElse(id) { mockFeed.first() }
        )
    }
}
