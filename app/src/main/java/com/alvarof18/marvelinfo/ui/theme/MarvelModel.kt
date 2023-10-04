package com.alvarof18.marvelinfo.ui.theme

import com.alvarof18.marvelinfo.data.network.clients.marvel.Resultado

data class MarvelModel(var title: String = "", var description: String = "", var pages: Int = 0)

fun MarvelModel.toComic(json: List<Resultado>): List<MarvelModel> {
    val allComics = mutableListOf<MarvelModel>()
    json.forEach {
        val newComic = MarvelModel();
        newComic.title = it.title
        newComic.description = it.description ?: ""
        newComic.pages = it.pageCount.toInt()
        allComics.add(newComic)
    }
    return allComics
}