package com.nazardunets.news_api_interview.news.data

import com.nazardunets.news_api_interview.news.data.network.NewsResponse

class NewsResponseMapper {
    fun map(response: NewsResponse): List<NewsModel>? {
        return response.articles
            ?.filterNotNull()
            ?.mapNotNull {
                val id = it.title?.hashCode() ?: return@mapNotNull null

                NewsModel(
                    id = id,
                    title = it.title,
                    sourceName = it.author ?: return@mapNotNull null,
                    imageUrl = it.urlToImage ?: return@mapNotNull null,
                    isFavorite = false,
                    body = it.content ?: return@mapNotNull null,
                    goToUrl = it.url ?: return@mapNotNull null
                )
            }
    }
}
