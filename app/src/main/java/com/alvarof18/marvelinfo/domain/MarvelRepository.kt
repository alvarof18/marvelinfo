package com.alvarof18.marvelinfo.domain

import com.alvarof18.marvelinfo.domain.model.ComicsModel

interface MarvelRepository {
    suspend fun getComics(offset:String):List<ComicsModel>
    suspend fun searchComics(query:String):List<ComicsModel>
    suspend fun getComicById(comicId:Long):ComicsModel
    suspend fun getComicsByCategories(offset:String, category:String):List<ComicsModel>

}