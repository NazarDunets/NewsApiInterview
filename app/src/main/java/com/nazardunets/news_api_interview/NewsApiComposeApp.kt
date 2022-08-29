package com.nazardunets.news_api_interview

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.nazardunets.news_api_interview.details.ui.DetailsDestination
import com.nazardunets.news_api_interview.navigation.BaseNavigator
import com.nazardunets.news_api_interview.navigation.NewsApiGraph
import com.nazardunets.news_api_interview.navigation.NewsTabButton
import com.nazardunets.news_api_interview.navigation.rememberBaseNavigator
import com.nazardunets.news_api_interview.news.ui.feed.FeedDestination
import com.nazardunets.news_api_interview.theme.Colors
import com.nazardunets.news_api_interview.theme.News_api_interviewTheme

@Composable
fun NewsApiComposeApp() {
    val navController = rememberNavController()
    val navigator = rememberBaseNavigator(navController)

    News_api_interviewTheme {
        Scaffold(
            bottomBar = {
                NewsBottomBar(navigator)
            }
        ) {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = NewsApiGraph.Main.graphRoute
            ) {
                feedNavigation(navigator)
                favoritesNavigation(navigator)
                fullscreenNavigation()
            }
        }
    }
}

@Composable
fun NewsBottomBar(navigator: BaseNavigator) {
    with(navigator) {
        selectedTabState?.let {
            BottomAppBar(
                backgroundColor = Colors.primaryVariant(),
                contentColor = Colors.backgroundMain(),
            ) {

                tabs.forEachIndexed { tabIndex, tabData ->
                    Spacer(Modifier.weight(1f))

                    NewsTabButton(
                        tab = tabData,
                        isSelected = tabIndex == selectedTabState
                    ) { isReselected ->
                        navigator.onTabSelected(tabIndex, isReselected)
                    }
                }

                Spacer(Modifier.weight(1f))
            }
        }
    }
}


fun NavGraphBuilder.feedNavigation(
    navigator: BaseNavigator
) {
    navigation(
        route = NewsApiGraph.Main.graphRoute,
        startDestination = NewsApiGraph.Main.Feed.graphRoute
    ) {
        composable(NewsApiGraph.Main.Feed.graphRoute) {
            FeedDestination(favoritesOnly = false) {
                navigator.navigateToNewsDetails(it)
            }
        }
    }
}

fun NavGraphBuilder.favoritesNavigation(
    navigator: BaseNavigator
) {
    navigation(
        route = NewsApiGraph.Favorites.graphRoute,
        startDestination = NewsApiGraph.Favorites.Feed.graphRoute
    ) {
        composable(NewsApiGraph.Favorites.Feed.graphRoute) {
            FeedDestination(favoritesOnly = true) {
                navigator.navigateToNewsDetails(it)
            }
        }
    }
}


fun NavGraphBuilder.fullscreenNavigation() {
    navigation(
        route = NewsApiGraph.Fullscreen.graphRoute,
        startDestination = NewsApiGraph.Fullscreen.Details.graphRoute
    ) {
        with(NewsApiGraph.Fullscreen.Details) {
            composable(
                route = graphRoute,
                arguments = args
            ) { navEntry ->
                val id = navEntry.arguments?.getInt(KEY_ID) ?: -1

                DetailsDestination(id = id)
            }
        }
    }
}
