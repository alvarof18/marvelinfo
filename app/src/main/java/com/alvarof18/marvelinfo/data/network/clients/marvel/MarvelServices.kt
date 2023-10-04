package com.alvarof18.marvelinfo.data.network.clients.marvel

import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.data.mapper.MarvelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelServices {

    private val retrofit = Retrofit.Builder().baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    suspend fun getComics(offset:String): List<ComicsModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MarvelClient::class.java).getComics(offset = offset)
            if (response.isSuccessful) {
                val listResultado = response.body()?.data?.resultado
                listResultado?.map { comic ->
                    MarvelMapper.marvelResponseToComicEntity(comic)
                } ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}


