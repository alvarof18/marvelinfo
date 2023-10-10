package com.alvarof18.marvelinfo.data.network.clients.marvel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelClient{
    @GET("comics?ts=1&apikey=c99506d64c21e849503f53b22673b19f&hash=51b06d05d152ade14234d6579d79f9ae")
    suspend fun getComics( @Query("offset") offset:String): Response<MarvelResponse>

    @GET("comics?ts=1&apikey=c99506d64c21e849503f53b22673b19f&hash=51b06d05d152ade14234d6579d79f9ae")
    suspend fun searchComics( @Query("titleStartsWith") query:String): Response<MarvelResponse>

    @GET("comics/{comicId}?ts=1&apikey=c99506d64c21e849503f53b22673b19f&hash=51b06d05d152ade14234d6579d79f9ae")
    suspend fun getComicById(@Path("comicId") comicId:Long):Response<MarvelResponse>

}