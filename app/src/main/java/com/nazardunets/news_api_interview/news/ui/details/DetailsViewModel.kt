package com.nazardunets.news_api_interview.news.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazardunets.news_api_interview.common.ui.ScreenState
import com.nazardunets.news_api_interview.news.data.NewsRepository
import com.nazardunets.news_api_interview.news.ui.details.mapper.DetailsScreenUiMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DetailsViewModel(
    private val id: Int,
    private val newsRepository: NewsRepository,
    private val detailsUiMapper: DetailsScreenUiMapper
) : ViewModel(),
    ContainerHost<ScreenState<DetailsScreenUiModel, Exception>, OpenInBrowser> {

    override val container: Container<ScreenState<DetailsScreenUiModel, Exception>, OpenInBrowser> =
        container(ScreenState.Loading)

    init {
        intent {
            reduce {
                ScreenState.Loading
            }
        }
        viewModelScope.launch {
            newsRepository.getNewsById(id)
                .map {
                    if (it.isSuccess) {
                        ScreenState.Success(
                            detailsUiMapper.map(it.getOrThrow())
                        )
                    } else {
                        ScreenState.Error(it.exceptionOrNull() as? Exception ?: Exception())
                    }
                }
                .collect {
                    intent {
                        reduce {
                            it
                        }
                    }
                }
        }
    }

    fun onFavoriteClick(id: Int) = intent {
        newsRepository.toggleFavoritesStatus(id)
    }

    fun onOpenLinkClick(link: String) = intent {
        postSideEffect(OpenInBrowser(link))
    }
}

@JvmInline
value class OpenInBrowser(val link: String)
