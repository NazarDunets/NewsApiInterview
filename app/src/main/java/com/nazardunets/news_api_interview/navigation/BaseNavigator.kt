package com.nazardunets.news_api_interview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun rememberBaseNavigator(
    navController: NavController
): BaseNavigator = remember(navController) {
    BaseNavigator(
        navController = navController,
        tabs = NewsTab.values().asList()
    )
}

open class BaseNavigator(
    private val navController: NavController,
    val tabs: List<NewsTab>
) {
    val selectedTabState: Int?
        @Composable get() = navController.currentBackStackEntryAsState().value.selectedTab()

    fun navigateUp(): Boolean {
        return navController.navigateUp()
    }

    fun onTabSelected(index: Int, isReselected: Boolean) {
        val targetRoute: String = tabs[index].route

        if (isReselected) {
            // don't do anything if we are on the first page of tab's nested navigation
            val possibleTabDestinationIndex = (navController.backQueue.size - 2).coerceAtLeast(0)
            if (navController.backQueue[possibleTabDestinationIndex].destination.route == targetRoute) return

            navController.navigate(targetRoute) {
                restoreState = false
                popUpTo(targetRoute) {
                    saveState = false
                    inclusive = true
                }
            }
        } else {
            navController.navigate(targetRoute) {
                launchSingleTop = true
                restoreState = true
                // user will go to main page after popping tab's nested backstack (or quit the app if already on main page)
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToNewsDetails(id: Int) {
        navigate(
            NewsApiGraph.Fullscreen.Details,
            NewsApiGraph.Fullscreen.Details.KEY_ID to id
        )
    }

    fun navigate(destination: Graph.Destination, vararg args: Pair<String, Any>) {
        navigate(
            destination = destination,
            args = args,
            builderOptions = {}
        )
    }

    fun navigate(destination: Graph.Destination, vararg args: Pair<String, Any>, builderOptions: NavOptionsBuilder.() -> Unit) {
        navController.navigate(
            route = destination.getNavRouteWithArgs(args),
            builder = builderOptions
        )
    }

    private fun (NavBackStackEntry?).selectedTab(): Int? {
        if (this == null) return null

        val root = destination.route?.substringBefore(ROUTE_SEPARATOR) ?: return null

        return tabs.indexOfFirst { it.route == root }.let {
            if (it == -1) null
            else it
        }
    }
}
