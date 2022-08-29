package com.nazardunets.news_api_interview.news.ui.feed

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nazardunets.news_api_interview.common.ui.ErrorContent
import com.nazardunets.news_api_interview.common.ui.LoadingOverlay
import com.nazardunets.news_api_interview.common.ui.ScreenState
import com.nazardunets.news_api_interview.news.ui.feed.components.FeedItem
import com.nazardunets.news_api_interview.news.ui.feed.components.FeedItemUiModel
import com.nazardunets.news_api_interview.theme.Colors
import com.nazardunets.news_api_interview.theme.Dimens

@Composable
fun FeedScreen(
    state: ScreenState<List<FeedItemUiModel>, Exception>,
    onFavoriteClick: (Int) -> Unit,
    onItemClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Colors.backgroundSecondary()
    ) {
        when (state) {
            is ScreenState.Success -> {
                FeedContent(
                    items = state.data,
                    onFavoriteClick = onFavoriteClick,
                    onItemClick = onItemClick
                )
            }
            is ScreenState.Error -> {
                ErrorContent(Modifier.fillMaxSize())
            }
            ScreenState.Loading -> {
                LoadingOverlay(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun FeedContent(
    items: List<FeedItemUiModel>,
    onFavoriteClick: (Int) -> Unit,
    onItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = Dimens.indent12)
    ) {
        items(items) {
            Spacer(Modifier.height(Dimens.indent12))

            FeedItem(
                model = it,
                onFavoriteClick = onFavoriteClick,
                onItemClick = onItemClick
            )
        }

        item {
            Spacer(Modifier.height(Dimens.indent24))
        }
    }
}

@Preview
@Composable
private fun FeedScreenPreview() {
    val items = List(10) {
        FeedItemUiModel(
            id = 12,
            title = "Tear gas used to disperse protestors Arizona Capitol building, officials say - CNN",
            sourceName = "CNN",
            imageUrl = "https://cdn.cnn.com/cnnnext/dam/assets/220625014924-05-arizona-scotus-protest-restricted-super-tease.jpg",
            isFavorite = false
        )
    }

    FeedScreen(
        state = ScreenState.Success(items),
        onFavoriteClick = {},
        onItemClick = {}
    )
}
