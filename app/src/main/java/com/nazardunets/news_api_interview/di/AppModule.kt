package com.nazardunets.news_api_interview.di

import android.app.Application
import com.nazardunets.news_api_interview.news.data.NewsRepository
import com.nazardunets.news_api_interview.news.data.NewsResponseMapper
import com.nazardunets.news_api_interview.news.data.db.FavoriteNewsDao
import com.nazardunets.news_api_interview.news.data.db.FavoriteNewsDatabase
import com.nazardunets.news_api_interview.news.data.network.NewsApi
import com.nazardunets.news_api_interview.news.data.network.getNewsHttpClient
import com.nazardunets.news_api_interview.news.ui.details.DetailsViewModel
import com.nazardunets.news_api_interview.news.ui.details.mapper.DetailsScreenUiMapper
import com.nazardunets.news_api_interview.news.ui.feed.FeedViewModel
import com.nazardunets.news_api_interview.news.ui.feed.mapper.FeedItemsUiMapper
import io.ktor.client.*
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


private val appModule = module {

    single<HttpClient> {
        getNewsHttpClient()
    }

    single<FavoriteNewsDao> {
        FavoriteNewsDatabase
            .getDatabase(androidApplication())
            .favoriteNewsDao()
    }

    factoryOf(::NewsApi)
    factoryOf(::NewsResponseMapper)
    singleOf(::NewsRepository)

    factoryOf(::FeedItemsUiMapper)
    factoryOf(::DetailsScreenUiMapper)

    viewModel<DetailsViewModel> {
        DetailsViewModel(
            id = it[0],
            newsRepository = get(),
            detailsUiMapper = get()
        )
    }

    viewModel<FeedViewModel> { params ->
        FeedViewModel(
            favoritesOnly = params[0],
            newsRepository = get(),
            feedItemsUiMapper = get()
        )
    }
}

fun Application.initDi() {
    startKoin {
        androidContext(this@initDi)

        modules(appModule)
    }
}
