package com.alvarof18.marvelinfo.domain.usecases

import com.alvarof18.marvelinfo.data.MarvelRepositoryImpl
import com.alvarof18.marvelinfo.domain.model.ComicsModel

class GetComicsUseCase {
    private val repository = MarvelRepositoryImpl()
    suspend operator fun invoke(offset: String): List<ComicsModel> =
        repository.getComics(offset = offset)
}

