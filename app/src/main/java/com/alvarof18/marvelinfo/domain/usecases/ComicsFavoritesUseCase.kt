package com.alvarof18.marvelinfo.domain.usecases

import android.content.Context
import com.alvarof18.marvelinfo.data.MarvelRepositoryLocalImpl
import com.alvarof18.marvelinfo.domain.model.ComicsModel

class ComicsFavoritesUseCase {
    private val repositoryLocal = MarvelRepositoryLocalImpl()

    suspend fun getFavoriteComicById(comicId:Long): ComicsModel?{
        return repositoryLocal.getFavoriteComic(comicId)
    }

    suspend fun isFavoriteComic(comicId: Long):Boolean{
        val comic = getFavoriteComicById(comicId)
        return comic != null
    }

    suspend fun insertFavoriteComic(comic: ComicsModel) {
        repositoryLocal.insertFavoriteComic(comic)
    }

    suspend fun deleteFavoriteComic(comicId: Long){
        repositoryLocal.deleteFavoriteComic(comicId)
    }

}