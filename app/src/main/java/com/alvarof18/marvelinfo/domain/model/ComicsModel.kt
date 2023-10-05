package com.alvarof18.marvelinfo.domain.model

data class ComicsModel(
    val id: Long = 0,
    val title: String = "",
    val urlPoster: String = "",
    val creators: List<CreatorsModel> = emptyList(),
    val format: String = "",
    val pages: Int = 0,
)
