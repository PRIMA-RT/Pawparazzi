/*
 * Copyright 2026 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:OptIn(ExperimentalFlexBoxApi::class)

package com.example.pawparazzi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.FlexBox
import androidx.compose.foundation.layout.FlexJustifyContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.example.pawparazzi.ui.scene.ListDetailScene
import com.example.pawparazzi.ui.scene.rememberListDetailSceneStrategy
import com.example.pawparazzi.ui.scene.rememberNavAreaSceneDecorator
import com.example.pawparazzi.ui.screen.AdditionalExercises
import com.example.pawparazzi.ui.screen.FlexboxPlayGround
import com.example.pawparazzi.ui.screen.GridPlayGround
import com.example.pawparazzi.ui.screen.MediaQueryPlayGround
import com.example.pawparazzi.ui.screen.ReelDetailScreen
import com.example.pawparazzi.ui.screen.ReelsScreen
import com.example.pawparazzi.ui.theme.PawparazziTheme
import kotlinx.serialization.Serializable

sealed interface TopLevelRoute : NavKey {
    @get:DrawableRes
    val icon: Int
    val label: String
}

@Serializable
data object Feed : TopLevelRoute {
    override val icon = R.drawable.ic_nav_feed
    override val label = "Feed"
}

@Serializable
data class FeedDetail(val id: Int) : NavKey {
}

@Serializable
data object FlexboxPlayground : NavKey {
}

@Serializable
data object GridPlayground : NavKey {
}

@Serializable
data object MQPlayground : NavKey {
}

@Serializable
data object Messages : TopLevelRoute {
    override val icon = R.drawable.ic_nav_messages
    override val label = "Messages"
}

@Serializable
data object Favourites : TopLevelRoute {
    override val icon = R.drawable.ic_nav_favourites
    override val label = "Favourites"
}

val TOP_LEVEL_ROUTES: List<TopLevelRoute> = listOf(Feed, Messages, Favourites)

@OptIn(ExperimentalFlexBoxApi::class)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMediaQueryApi::class, ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PawparazziTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: MainViewModel = viewModel()
                    val isWindowLargeEnough by derivedMediaQuery { windowWidth >= WIDTH_DP_MEDIUM_LOWER_BOUND.dp }
                    val backStack = viewModel.backStack
                    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>()
                    val sceneDecorator =
                        rememberNavAreaSceneDecorator<NavKey>(
                            isWindowLargeEnough,
                            TOP_LEVEL_ROUTES,
                            backStack
                        )

                    NavDisplay(
                        backStack = backStack,
                        onBack = {
                            backStack.removeLastOrNull()
                            viewModel.isDetailOpen = false
                        },
                        sceneStrategies = listOf(listDetailStrategy),
                        sceneDecoratorStrategies = listOf(sceneDecorator),
                        entryProvider = entryProvider {
                            entry<Feed>(metadata = ListDetailScene.listPane()) {
                                ReelsScreen(
                                    modifier = Modifier,
                                    viewModel = viewModel,
                                    innerPadding = innerPadding
                                ) {
                                    backStack.add(FeedDetail(it.id))
                                }
                            }
                            entry<FeedDetail>(metadata = ListDetailScene.detailPane()) { detail ->
                                ReelDetailScreen(detail.id, onBack = {
                                    backStack.removeLastOrNull()
                                    viewModel.isDetailOpen = false
                                }, modifier = Modifier)
                            }
                            entry<Messages> {
                                AdditionalExercises(
                                    modifier = Modifier.padding(
                                        innerPadding
                                    )
                                ) { backStack.add(it) }
                            }
                            entry<Favourites> { Placeholder(screenName = "Downloads") }
                            entry<FlexboxPlayground> { FlexboxPlayGround() }
                            entry<GridPlayground> { GridPlayGround() }
                            entry<MQPlayground> { MediaQueryPlayGround() }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun Placeholder(screenName: String, modifier: Modifier = Modifier) {
    FlexBox(
        config = {
            justifyContent(FlexJustifyContent.Center)
        },
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "🐶 $screenName 🐶",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceholderPreview() {
    PawparazziTheme {
        Placeholder("Home")
    }
}
