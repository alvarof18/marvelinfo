package com.alvarof18.marvelinfo.ui.model

import com.alvarof18.marvelinfo.domain.model.ComicsModel

data class ComicUiState(var isLoading: Boolean = false, val listComics:List<ComicsModel> = emptyList())
