package com.alvarof18.marvelinfo.ui.comics

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.domain.usecases.ComicsFavoritesUseCase

import com.alvarof18.marvelinfo.domain.usecases.GetComicsUseCase
import com.alvarof18.marvelinfo.ui.model.ComicUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ComicsViewModel: ViewModel() {

    private val getComicsUseCase = GetComicsUseCase()
    private val comicsFavoritesUseCase = ComicsFavoritesUseCase()
    private var _uiState = MutableStateFlow(ComicUiState())
    val uiState: StateFlow<ComicUiState> = _uiState.asStateFlow()

    var comicSelected:ComicsModel = ComicsModel()
    var isFavoriteComic by mutableStateOf(false)

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
        loadComics(offsetNext)
    }

    fun loadInfoComic(idComic:Int){
        comicSelected = _uiState.value.listComics.find { it.id.toInt() == idComic }!!
        viewModelScope.launch {
            //valido si esta en la lista de favoritos
            isFavoriteComic = comicsFavoritesUseCase.isFavoriteComic(comicSelected.id)

        }
    }

    fun toggleFavorite(){
         viewModelScope.launch {
            val comic = comicsFavoritesUseCase.getFavoriteComicById(comicSelected.id)
             isFavoriteComic = if (comic != null){
                 comicsFavoritesUseCase.deleteFavoriteComic(comic.id)
                 false

             }else{
                 comicsFavoritesUseCase.insertFavoriteComic(comicSelected)
                 true
             }

        }

    }
}