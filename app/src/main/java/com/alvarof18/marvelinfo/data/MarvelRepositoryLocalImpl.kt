package com.alvarof18.marvelinfo.data


import com.alvarof18.marvelinfo.data.local.MarvelLocalData
import com.alvarof18.marvelinfo.domain.MarvelRepositoryLocal
import com.alvarof18.marvelinfo.domain.model.ComicsModel

class MarvelRepositoryLocalImpl : MarvelRepositoryLocal {

    private val localDatasource = MarvelLocalData()

    override suspend fun getAllFavoriteComics(): List<ComicsModel> {
        return localDatasource.getAllFavoriteComics()
    }

    override suspend fun getFavoriteComic(idComic: Long): ComicsModel? {
        return localDatasource.getFavoriteComic(idComic = idComic)
    }

    override suspend fun insertFavoriteComic(comic: ComicsModel) {
        localDatasource.insertFavoriteComic(comic)
    }

    override suspend fun deleteFavoriteComic(idComic: Long) {
        localDatasource.deleteFavoriteComic(idComic)
    }

}