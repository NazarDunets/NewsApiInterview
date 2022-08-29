package com.nazardunets.news_api_interview.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object NewsApiGraph {

    object Main : RootGraph("main") {
        object Feed : Destination("feed")
    }

    object Favorites : RootGraph("favorites") {
        object Feed : Destination("feed")
    }

    object Fullscreen : RootGraph("fullscreen") {
        object Details : Destination("details") {
            const val KEY_ID = "id"

            override val args: List<NamedNavArgument> = listOf(
                navArgument(KEY_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        }
    }
}
