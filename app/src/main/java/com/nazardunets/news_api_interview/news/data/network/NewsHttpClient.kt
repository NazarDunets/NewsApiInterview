package com.nazardunets.news_api_interview.news.data.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun getNewsHttpClient() = HttpClient(OkHttp) {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.i("NewsKtorClient", message)
            }
        }
        level = LogLevel.INFO
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }

    defaultRequest {
        header("X-Api-Key", "25ddbc92d25d48e9b8a19f7553355fdb")
        url("https://newsapi.org/v2/")
    }
}
