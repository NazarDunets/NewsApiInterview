package com.nazardunets.news_api_interview.news.ui.feed.mapper

import com.nazardunets.news_api_interview.news.data.NewsModel
import com.nazardunets.news_api_interview.news.ui.feed.components.FeedItemUiModel

class FeedItemsUiMapper {
    fun map(items: List<NewsModel>): List<FeedItemUiModel> {
        return items.map {
            map(it)
        }
    }

    fun map(item: NewsModel): FeedItemUiModel {
        return FeedItemUiModel(
            id = item.id,
            title = item.title,
            sourceName = item.sourceName,
            imageUrl = item.imageUrl,
            isFavorite = item.isFavorite
        )
    }
}
