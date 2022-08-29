package com.nazardunets.news_api_interview.news.data.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class NewsApi(
    private val client: HttpClient
) {
    suspend fun getNews(): NewsResponse {
        return client.get("top-headlines") {
            url {
                parameters.append("country", "us")
                parameters.append("pageSize", "50")
            }
        }.body()
    }
}
