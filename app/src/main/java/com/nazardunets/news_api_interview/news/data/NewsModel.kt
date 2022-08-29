package com.nazardunets.news_api_interview.news.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_news")
data class NewsModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "source_name")
    val sourceName: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "go_to_url")
    val goToUrl: String,
    @Ignore
    val isFavorite: Boolean = false,
) {
    constructor(
        id: Int,
        title: String,
        sourceName: String,
        imageUrl: String,
        body: String,
        goToUrl: String,
    ) : this(
        id,
        title,
        sourceName,
        imageUrl,
        body,
        goToUrl,
        false
    )
}