package com.alvarof18.marvelinfo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MarvelDao{

    @Query("Select * from favoriteComic")
    suspend fun getAllComics():List<ComicEntity>

    @Query("Select * from favoriteComic where id = :idComic")
    suspend fun getComicById(idComic:Long):ComicEntity

    @Insert
    suspend fun addComic(comic:ComicEntity)

    @Delete
    suspend fun deleteComic(comic:ComicEntity)

}