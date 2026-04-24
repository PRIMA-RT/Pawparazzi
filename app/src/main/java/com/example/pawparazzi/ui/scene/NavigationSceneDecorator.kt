package com.example.pawparazzi.ui.scene

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneDecoratorStrategy
import androidx.navigation3.scene.SceneDecoratorStrategyScope
import androidx.window.core.ExperimentalWindowApi
import com.example.pawparazzi.Feed
import com.example.pawparazzi.TopLevelRoute
import com.example.pawparazzi.ui.component.TopBar
import com.example.pawparazzi.ui.scene.ListDetailScene.Companion.DETAIL_KEY
import com.example.pawparazzi.ui.theme.BackgroundColor


@Composable
private fun List<TopLevelRoute>.NavBar(backStack: NavBackStack<NavKey>) {
    NavigationBar(containerColor = BackgroundColor) {
        this@NavBar.forEach { topLevelRoute ->

            val isSelected = topLevelRoute == backStack.lastOrNull()
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    backStack.add(topLevelRoute)
                },
                icon = {
                    Icon(
                        painter = painterResource(topLevelRoute.icon),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                },
                label = {},
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
private fun List<TopLevelRoute>.NavRails(backStack: NavBackStack<NavKey>) {
    NavigationRail(containerColor = BackgroundColor, header = { TopBar() }) {
        Spacer(Modifier.weight(1f))
        this@NavRails.forEach { topLevelRoute ->
            val isSelected = topLevelRoute == backStack.lastOrNull()
            NavigationRailItem(
                onClick = { backStack.add(topLevelRoute) },
                selected = isSelected,
                icon = {
                    Icon(
                        painterResource(topLevelRoute.icon),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                },
                label = {},
                alwaysShowLabel = false
            )
        }
        Spacer(Modifier.weight(1f))
    }
}


@OptIn(ExperimentalWindowApi::class)
@Composable
fun <T : Any> rememberNavAreaSceneDecorator(
    isWindowLargeEnough: Boolean,
    navigationItems: List<TopLevelRoute>,
    backStack: NavBackStack<NavKey>
) = remember { NavAreaSceneDecorator<T>(isWindowLargeEnough, navigationItems, backStack) }

class NavAreaSceneDecorator<T : Any>(
    val isWindowLargeEnough: Boolean,
    val navigationItems: List<TopLevelRoute>,
    val backStack: NavBackStack<NavKey>,
) : SceneDecoratorStrategy<T> {

    override fun SceneDecoratorStrategyScope<T>.decorateScene(scene: Scene<T>): Scene<T> {
        return when {
            scene.metadata.containsKey(DETAIL_KEY) && (!backStack.contains(Feed) || !isWindowLargeEnough) -> scene
            isWindowLargeEnough -> {
                NavRailScene(scene, navRail = {
                    navigationItems.NavRails(backStack)
                })
            }

            else -> {
                NavBarScene(scene, navBar = {
                    navigationItems.NavBar(backStack)
                })
            }
        }
    }

}

class NavBarScene<T : Any>(scene: Scene<T>, navBar: @Composable () -> Unit) : Scene<T> {
    override val key = scene.key
    override val entries = scene.entries
    override val previousEntries = scene.previousEntries
    override val metadata = scene.metadata
    override val content: @Composable () -> Unit = {
        NavBarLayout(bar = navBar, content = scene.content)
    }
}

@Composable
fun NavBarLayout(bar: @Composable () -> Unit, content: @Composable () -> Unit) {
    Column {
        TopBar()
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
        bar()
    }
}

class NavRailScene<T : Any>(scene: Scene<T>, navRail: @Composable () -> Unit) : Scene<T> {
    override val key = scene.key
    override val entries = scene.entries
    override val previousEntries = scene.previousEntries
    override val metadata = scene.metadata
    override val content: @Composable () -> Unit = {
        NavRailLayout(rail = navRail, content = scene.content)
    }
}

@Composable
fun NavRailLayout(rail: @Composable () -> Unit, content: @Composable () -> Unit) {
    Row {
        rail()
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}