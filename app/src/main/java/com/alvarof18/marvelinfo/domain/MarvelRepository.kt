package com.alvarof18.marvelinfo.domain

import com.alvarof18.marvelinfo.domain.model.ComicsModel

interface MarvelRepository {
    suspend fun getComics(offset:String):List<ComicsModel>
}