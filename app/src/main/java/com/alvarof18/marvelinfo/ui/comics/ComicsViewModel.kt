package com.alvarof18.marvelinfo.ui.comics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.domain.usecases.ComicsFavoritesUseCase
import com.alvarof18.marvelinfo.domain.usecases.GetComicsUseCase
import com.alvarof18.marvelinfo.ui.model.ComicUiState
import com.alvarof18.marvelinfo.ui.model.FavoriteComicModel
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
    var favoriteList:List<FavoriteComicModel> = emptyList()


    init {
        loadComics()
        loadFavoriteComics()
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

    private fun loadFavoriteComics(){
        viewModelScope.launch {
            favoriteList = comicsFavoritesUseCase.getAllFavoriteComics()
        }
    }

    fun loadMoreComics(){
        val offsetNext = _uiState.value.listComics.size
        loadComics(offsetNext)
    }

    fun loadInfoComic(idComic:Int){
        viewModelScope.launch {
        var comicTemp = _uiState.value.listComics.find { it.id.toInt() == idComic }
        if (comicTemp == null){
                comicTemp = favoriteList.find { it.comic.id.toInt() == idComic }?.comic
            }
              comicSelected = comicTemp!!
        }


        //Sacar esto de esta funcion
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
             loadFavoriteComics()
        }

    }
}

