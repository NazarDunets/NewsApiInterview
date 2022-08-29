package com.nazardunets.news_api_interview.news.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nazardunets.news_api_interview.news.data.NewsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteNewsDao {
    @Query("SELECT * FROM favorite_news")
    fun getAllFavorites(): Flow<List<NewsModel>>

    @Query("SELECT * FROM favorite_news WHERE id=:id")
    suspend fun getNewsById(id: Int): List<NewsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(entity: NewsModel)

    @Query("DELETE FROM favorite_news WHERE id=:id")
    suspend fun deleteNewsById(id: Int)
}