package com.alvarof18.marvelinfo.ui.comics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.domain.usecases.GetComicsUseCase
import com.alvarof18.marvelinfo.ui.model.ComicUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ComicsViewModel : ViewModel() {
    private val getComicsUseCase = GetComicsUseCase()
    private var _uiState = MutableStateFlow(ComicUiState())
    //private var _comicSelected = MutableStateFlow(ComicsModel())

    val uiState: StateFlow<ComicUiState> = _uiState.asStateFlow()
    //val comicSelected: StateFlow<ComicsModel> = _comicSelected.asStateFlow()
    var comicSelected:ComicsModel = ComicsModel()

    init {
        loadComics()
    }

   private fun loadComics(offset: Int = 0) {
        viewModelScope.launch {
        _uiState.update { currentStatus -> currentStatus.copy(isLoading = true) }
        _uiState.update { currentStatus ->
               currentStatus.copy(listComics = currentStatus.listComics + getComicsUseCase(offset = offset.toString())  )
            }
       _uiState.update { currentStatus -> currentStatus.copy(isLoading = false) }
        }
    }

    fun loadMoreComics(){
        val offsetNext = _uiState.value.listComics.size
         Log.i("ComicsOffset", offsetNext.toString())
        loadComics(offsetNext)
    }

    fun loadInfoComic(idComic:Int){
        comicSelected = _uiState.value.listComics.find { it.id.toInt() == idComic }!!

   //     _comicSelected.value = _uiState.value.listComics.find { it.id.toInt() == idComic }!!
   //     Log.i("ViewmodelComic", _comicSelected.value.id.toString())
    }
}