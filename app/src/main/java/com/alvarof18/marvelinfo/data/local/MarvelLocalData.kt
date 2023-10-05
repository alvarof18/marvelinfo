package com.alvarof18.marvelinfo.data.local


import com.alvarof18.marvelinfo.data.mapper.MarvelMapper
import com.alvarof18.marvelinfo.domain.model.ComicsModel

class MarvelLocalData {
    private val marvelDao = MarvelDBInstance.InstanceDB?.MarvelDao()

     suspend fun getAllFavoriteComics(): List<ComicsModel> {
        return marvelDao?.getAllComics()
            ?.map { comicDatabase -> MarvelMapper.localResponseToComicModel(comicDatabase) } ?: emptyList()
    }

   suspend  fun getFavoriteComic(idComic: Long): ComicsModel? {
       val comic = marvelDao?.getComicById(idComic = idComic)
       if (comic != null){
           return MarvelMapper.localResponseToComicModel(comic)
       }
       return null
    }

    suspend fun insertFavoriteComic(comic: ComicsModel) {
        val newFavoriteComic = ComicEntity(
            id = comic.id,
            urlPoster = comic.urlPoster,
            title = comic.title,
            format = comic.format
        )
        marvelDao?.addComic(newFavoriteComic)
    }

    suspend fun deleteFavoriteComic(idComic:Long){
        val comic = marvelDao?.getComicById(idComic)
        marvelDao?.deleteComic(comic!!)
    }
}