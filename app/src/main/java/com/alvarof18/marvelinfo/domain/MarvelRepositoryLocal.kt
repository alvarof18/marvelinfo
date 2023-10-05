package com.alvarof18.marvelinfo.domain

import com.alvarof18.marvelinfo.domain.model.ComicsModel

interface MarvelRepositoryLocal {
   suspend fun getAllFavoriteComics():List<ComicsModel>
   suspend fun getFavoriteComic(idComic:Long): ComicsModel?
   suspend fun insertFavoriteComic(comic: ComicsModel)
   suspend fun deleteFavoriteComic(idComic: Long)
}