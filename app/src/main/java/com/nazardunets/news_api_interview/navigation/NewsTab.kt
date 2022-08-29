package com.nazardunets.news_api_interview.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

enum class NewsTab(
    val route: String,
    val unselected: ImageVector,
    val selectedIcon: ImageVector
) {
    MAIN(
        route = NewsApiGraph.Main.graphRoute,
        unselected = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    ),
    FAVORITES(
        route = NewsApiGraph.Favorites.graphRoute,
        unselected = Icons.Default.FavoriteBorder,
        selectedIcon = Icons.Default.Favorite
    )
}

@Composable
fun NewsTabButton(
    tab: NewsTab,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit
) {
    val icon = if (isSelected) tab.selectedIcon else tab.unselected
    IconButton(
        onClick = {
            onClick(isSelected)
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}
