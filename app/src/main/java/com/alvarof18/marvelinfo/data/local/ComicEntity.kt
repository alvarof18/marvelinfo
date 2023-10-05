package com.alvarof18.marvelinfo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteComic")
data class ComicEntity(
    @PrimaryKey
    val id: Long = 0,
    val title: String = "",
    val urlPoster: String = "",
    val format: String = "",
)
