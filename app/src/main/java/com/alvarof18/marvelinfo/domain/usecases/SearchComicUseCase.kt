package com.alvarof18.marvelinfo.domain.usecases

import com.alvarof18.marvelinfo.data.MarvelRepositoryImpl
import com.alvarof18.marvelinfo.domain.model.ComicsModel

class SearchComicUseCase {

    private val repository = MarvelRepositoryImpl()

    suspend fun searchComics(query: String): List<ComicsModel> {
        return repository.searchComics(query)

    }

}