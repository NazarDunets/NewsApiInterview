package com.nazardunets.news_api_interview.news.data

import com.nazardunets.news_api_interview.news.data.db.FavoriteNewsDao
import com.nazardunets.news_api_interview.news.data.network.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val newsApi: NewsApi,
    private val newsResponseMapper: NewsResponseMapper,
    private val favoriteNewsDao: FavoriteNewsDao
) {
    private val remoteNews = MutableStateFlow<List<NewsModel>?>(null)

    suspend fun getFeedNews(forceUpdate: Boolean = false): Flow<Result<List<NewsModel>>> {
        val resultFlow = suspend {
            val favoriteIdFlow = favoriteNewsDao.getAllFavorites()
                .map { favorites ->
                    favorites.map {
                        it.id
                    }
                }

            remoteNews
                .combine(favoriteIdFlow) { remote, favoriteIds ->
                    remote?.let {
                        it.map { remoteEntity ->
                            remoteEntity.copy(
                                isFavorite = favoriteIds.contains(remoteEntity.id)
                            )
                        }
                    }
                }
                .map {
                    if (it != null) Result.success(it)
                    else Result.failure(Exception("Failed to load news"))
                }
        }

        if (remoteNews.value != null && !forceUpdate) return resultFlow()

        val newsRemoteResult = Result.runCatching {
            newsApi.getNews()
        }

        remoteNews.value =
            if (newsRemoteResult.isSuccess) newsResponseMapper.map(newsRemoteResult.getOrThrow())
            else null

        return resultFlow()
    }

    fun getFavoriteNews(): Flow<Result<List<NewsModel>>> {
        return favoriteNewsDao.getAllFavorites().map { favorites ->
            favorites.map { entity ->
                entity.copy(isFavorite = true)
            }
        }.map {
            Result.success(it)
        }
    }

    suspend fun getNewsById(id: Int): Flow<Result<NewsModel>> {
        val entity = favoriteNewsDao.getNewsById(id).firstOrNull()
            ?: remoteNews.value?.find { it.id == id }
            ?: return flow {
                emit(Result.failure(Exception("Entity with id $id not found")))
            }

        return favoriteNewsDao.getAllFavorites()
            .map { favorites ->
                entity.copy(isFavorite = favorites.find { it.id == id } != null)
            }.map {
                Result.success(it)
            }
    }

    suspend fun toggleFavoritesStatus(id: Int) {
        val isEntityCurrentlyFavorite = favoriteNewsDao.getNewsById(id).isNotEmpty()

        if (isEntityCurrentlyFavorite) {
            favoriteNewsDao.deleteNewsById(id)
        } else {
            remoteNews.value?.firstOrNull { it.id == id }?.let { entity ->
                favoriteNewsDao.insertNews(entity)
            }
        }
    }
}