package com.nazardunets.news_api_interview.news.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerialName("status")
    val status: String?,
    @SerialName("totalResults")
    val totalResults: Int?,
    @SerialName("articles")
    val articles: List<ArticleDataModel?>?
)

@Serializable
data class ArticleDataModel(
    @SerialName("source")
    val source: SourceDataModel?,
    @SerialName("author")
    val author: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("urlToImage")
    val urlToImage: String?,
    @SerialName("publishedAt")
    val publishedAt: String?,
    @SerialName("content")
    val content: String?
)

@Serializable
data class SourceDataModel(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
)
