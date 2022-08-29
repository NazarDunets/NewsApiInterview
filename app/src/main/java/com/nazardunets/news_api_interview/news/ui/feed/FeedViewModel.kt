package com.nazardunets.news_api_interview.news.ui.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazardunets.news_api_interview.common.ui.ScreenState
import com.nazardunets.news_api_interview.news.data.NewsRepository
import com.nazardunets.news_api_interview.news.ui.feed.components.FeedItemUiModel
import com.nazardunets.news_api_interview.news.ui.feed.mapper.FeedItemsUiMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FeedViewModel(
    private val favoritesOnly: Boolean,
    private val newsRepository: NewsRepository,
    private val feedItemsUiMapper: FeedItemsUiMapper
) : ViewModel(),
    ContainerHost<ScreenState<List<FeedItemUiModel>, Exception>, NavigateToDetails> {

    override val container: Container<ScreenState<List<FeedItemUiModel>, Exception>, NavigateToDetails> =
        container(ScreenState.Loading)

    init {
        intent {
            reduce {
                ScreenState.Loading
            }
        }

        viewModelScope.launch {
            newsRepository
                .run {
                    if (favoritesOnly) getFavoriteNews()
                    else getFeedNews()
                }
                .map {
                    if (it.isSuccess) {
                        ScreenState.Success(
                            feedItemsUiMapper.map(it.getOrThrow())
                        )
                    } else {
                        ScreenState.Error(it.exceptionOrNull() as? Exception ?: Exception())
                    }
                }.collect {
                    Log.i("FeedVm", it.toString())
                    intent {
                        reduce {
                            it
                        }
                    }
                }
        }
    }

    fun onItemClick(id: Int) = intent {
        postSideEffect(NavigateToDetails(id))
    }

    fun onFavoriteClick(id: Int) = intent {
        newsRepository.toggleFavoritesStatus(id)
    }
}

@JvmInline
value class NavigateToDetails(val id: Int)
