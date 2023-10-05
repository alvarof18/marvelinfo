package com.alvarof18.marvelinfo.data.mapper

import com.alvarof18.marvelinfo.data.local.ComicEntity
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.data.network.clients.marvel.Resultado
import com.alvarof18.marvelinfo.domain.model.CreatorsModel

class MarvelMapper {
    companion object {
        fun marvelResponseToComicEntity(marvelResponse: Resultado): ComicsModel {
            return ComicsModel(
                id = marvelResponse.id,
                title = marvelResponse.title,
                urlPoster = "${
                    marvelResponse.thumbnail.path.replace(
                        "http",
                        "https"
                    )
                }/portrait_uncanny.${marvelResponse.thumbnail.extension}",
                creators = marvelResponse.creators.items.map { item ->
                    CreatorsModel(name = item.name, role = item.role)
                },
                format = marvelResponse.format,
                pages = marvelResponse.pageCount.toInt()
            )
        }

        fun localResponseToComicModel(marvelLocal: ComicEntity): ComicsModel {
            return ComicsModel(
                id = marvelLocal.id,
                title = marvelLocal.title,
                urlPoster = marvelLocal.urlPoster,
                format = marvelLocal.format
            )
        }
    }
}