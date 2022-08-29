package com.nazardunets.news_api_interview.news.ui.details.mapper

import com.nazardunets.news_api_interview.news.data.NewsModel
import com.nazardunets.news_api_interview.news.ui.details.DetailsScreenUiModel

class DetailsScreenUiMapper {
    fun map(item: NewsModel): DetailsScreenUiModel {
        return DetailsScreenUiModel(
            id = item.id,
            title = item.title,
            body = item.body,
            sourceName = item.sourceName,
            imageUrl = item.imageUrl,
            goToUrl = item.goToUrl,
            isFavorite = item.isFavorite,
        )
    }
}
