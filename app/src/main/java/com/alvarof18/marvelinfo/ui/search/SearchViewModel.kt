package com.alvarof18.marvelinfo.ui.search

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.domain.usecases.SearchComicUseCase
import com.alvarof18.marvelinfo.ui.comics.ComicsViewModel
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel(){

    private val searchUseCase = SearchComicUseCase()


    private var _listResult = mutableStateListOf<ComicsModel>()
    var listResult: List<ComicsModel> = _listResult

    fun searchComic(query:String) {
        _listResult.clear()
        if (query.length >= 3) {
            viewModelScope.launch {
                _listResult.addAll(searchUseCase.searchComics(query))
            }
        }
    }
}