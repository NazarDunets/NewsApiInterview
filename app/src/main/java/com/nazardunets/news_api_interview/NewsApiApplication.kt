package com.nazardunets.news_api_interview

import android.app.Application
import com.nazardunets.news_api_interview.di.initDi


class NewsApiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDi()
    }
}