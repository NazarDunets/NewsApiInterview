package com.nazardunets.news_api_interview.news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nazardunets.news_api_interview.news.data.NewsModel

@Database(entities = [NewsModel::class], version = 1)
abstract class FavoriteNewsDatabase : RoomDatabase() {
    abstract fun favoriteNewsDao(): FavoriteNewsDao

    companion object {
        private const val DB_NAME = "favorite_news_db"

        @Volatile
        private var INSTANCE: FavoriteNewsDatabase? = null

        fun getDatabase(context: Context): FavoriteNewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteNewsDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
