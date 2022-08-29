package com.nazardunets.news_api_interview.news.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun FeedDestination(
    favoritesOnly: Boolean,
    navigateToDetails: (Int) -> Unit
) {
    val vm = koinViewModel<FeedViewModel> { parametersOf(favoritesOnly) }
    val state by vm.collectAsState()

    vm.collectSideEffect {
        navigateToDetails(it.id)
    }

    FeedScreen(
        state = state,
        onFavoriteClick = vm::onFavoriteClick,
        onItemClick = vm::onItemClick
    )
}
