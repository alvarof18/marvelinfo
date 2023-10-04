package com.alvarof18.marvelinfo.data

import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.domain.MarvelRepository
import com.alvarof18.marvelinfo.data.network.clients.marvel.MarvelServices

class MarvelRepositoryImpl: MarvelRepository {
    private val api = MarvelServices()
    //Aqui llamo al Datasource
    override suspend fun getComics(offset:String): List<ComicsModel> {
        return api.getComics(offset = offset)
    }
}