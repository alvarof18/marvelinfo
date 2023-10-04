package com.alvarof18.marvelinfo.data.network.clients.marvel

import com.google.gson.annotations.SerializedName

data class MarvelResponse(
    @SerializedName("code") var code: String,
    @SerializedName("status") var status: String,
    @SerializedName("data") var data: Data
)


data class Data(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    @SerializedName("results") val resultado: List<Resultado>

)

data class Resultado(
    val id: Long,
    val digitalID: Long,
    val title: String,
    val issueNumber: Long,
    val variantDescription: String,
    val description: String? = null,
    val modified: String,
    val upc: String,
    val ean: String,
    val issn: String,
    val pageCount: Long,
    val resourceURI: String,
    val collections: List<Any?>,
    val thumbnail: Thumbnail,
    val images: List<Thumbnail>,
    val creators: Creators,
    val format:String,

)

data class Creators (
    val available: Long,
    val collectionURI: String,
    val items: List<CreatorsItem>,
    val returned: Long
)

data class CreatorsItem (
    val resourceURI: String,
    val name: String,
    val role: String
)


data class Thumbnail (
    val path: String,
    val extension: String
)
