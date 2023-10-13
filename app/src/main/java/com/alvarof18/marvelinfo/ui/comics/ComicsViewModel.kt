package com.alvarof18.marvelinfo.ui.comics

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.domain.usecases.ComicsFavoritesUseCase
import com.alvarof18.marvelinfo.domain.usecases.GetComicsUseCase
import com.alvarof18.marvelinfo.ui.model.ComicUiState
import com.alvarof18.marvelinfo.ui.model.FavoriteComicModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class TypeComic {
    COMIC, MAGAZINE, DIGITAL, ALL
}

class ComicsViewModel : ViewModel() {

    private val getComicsUseCase = GetComicsUseCase()
    private val comicsFavoritesUseCase = ComicsFavoritesUseCase()

    private var _uiState = MutableStateFlow(ComicUiState())
    private var _comicSelected = MutableStateFlow(ComicsModel())
    private var _comicList = MutableStateFlow(emptyList<ComicsModel>())
    private var _magazineComicList = MutableStateFlow(emptyList<ComicsModel>())
    private var _digitalComicList = MutableStateFlow(emptyList<ComicsModel>())

    val uiState: StateFlow<ComicUiState> = _uiState.asStateFlow()
    val comicSelected: StateFlow<ComicsModel> = _comicSelected.asStateFlow()
    val comicList: StateFlow<List<ComicsModel>> = _comicList.asStateFlow()
    val magazineComicList: StateFlow<List<ComicsModel>> = _magazineComicList.asStateFlow()
    val digitalComicList: StateFlow<List<ComicsModel>> = _digitalComicList.asStateFlow()

    var isFavoriteComic by mutableStateOf(false)
    var favoriteList: List<FavoriteComicModel> = emptyList()

    var typeComicShow:TypeComic = TypeComic.ALL


    init {
        loadFavoriteComics()
        loadAllCategories()
    }

    private fun loadAllCategories(offset: Int = 0) {
        viewModelScope.launch {
            _comicList.value =
                getComicsUseCase.getComicByCategories(category = "comic", offset = "0")
            _magazineComicList.value =
                getComicsUseCase.getComicByCategories(category = "magazine", offset = "0")
            _digitalComicList.value =
                getComicsUseCase.getComicByCategories(category = "digital comic", offset = "0")
            _uiState.update { currentStatus ->
                currentStatus.copy(listComics = currentStatus.listComics + getComicsUseCase(offset = offset.toString()))
            }
        }
    }

    fun loadComicsList(type: TypeComic) {
        typeComicShow = type
        when (type) {
            TypeComic.COMIC -> { _uiState.update { currentState -> currentState.copy(listComics = _comicList.value) } }
            TypeComic.MAGAZINE -> _uiState.update { currentState -> currentState.copy(listComics = _magazineComicList.value) }
            TypeComic.DIGITAL -> _uiState.update { currentState -> currentState.copy(listComics = _digitalComicList.value) }
            TypeComic.ALL -> {}
        }
    }

    private fun loadFavoriteComics() {
        viewModelScope.launch {
            favoriteList = comicsFavoritesUseCase.getAllFavoriteComics()
        }
    }

    fun loadMoreComics() {
        var offsetNext: Int
        Log.i("Nota","$typeComicShow Magazine")
        viewModelScope.launch {
            when (typeComicShow) {
                TypeComic.COMIC -> {
                    offsetNext = _comicList.value.size
                    _uiState.update { currentState -> currentState.copy(isLoading = true) }
                    _comicList.value = _comicList.value + getComicsUseCase.getComicByCategories(
                        category = "comic",
                        offset = offsetNext.toString()
                    )
                    _uiState.update { currentState -> currentState.copy(listComics = _comicList.value) }
                    _uiState.update { currentState -> currentState.copy(isLoading = false) }
                }

                TypeComic.MAGAZINE -> {

                    offsetNext = _magazineComicList.value.size
                    _uiState.update { currentState -> currentState.copy(isLoading = true) }
                    Log.i("offsetNext", "$offsetNext")
                    _magazineComicList.value =
                        _magazineComicList.value + getComicsUseCase.getComicByCategories(
                            category = "magazine",
                            offset = offsetNext.toString()
                        )
                    _uiState.update { currentState -> currentState.copy(listComics = _magazineComicList.value) }
                    _uiState.update { currentState -> currentState.copy(isLoading = false) }
                }

                TypeComic.DIGITAL -> {
                    offsetNext = _digitalComicList.value.size
                    _uiState.update { currentState -> currentState.copy(isLoading = true) }
                    _digitalComicList.value =
                        _digitalComicList.value + getComicsUseCase.getComicByCategories(
                            category = "digital comic",
                            offset = offsetNext.toString()
                        )
                    _uiState.update { currentState -> currentState.copy(listComics = _magazineComicList.value) }
                    _uiState.update { currentState -> currentState.copy(isLoading = false) }
                }

                TypeComic.ALL -> {
                    offsetNext = _uiState.value.listComics.size
                    _uiState.update { currentState -> currentState.copy(isLoading = true) }
                    _uiState.update { currentStatus ->
                        currentStatus.copy(
                            listComics = currentStatus.listComics + getComicsUseCase(
                                offset = offsetNext.toString()
                            )
                        )
                    }
                    _uiState.update { currentState -> currentState.copy(isLoading = false) }
                }
            }

        }
    }

    fun loadInfoComic(idComic: Int) {
        viewModelScope.launch {
            var comicTemp = _uiState.value.listComics.find { it.id.toInt() == idComic }
            if (comicTemp == null) {
                comicTemp = favoriteList.find { it.comic.id.toInt() == idComic }?.comic
            }
            _comicSelected.value = comicTemp!!
            //valido si esta en la lista de favoritos
            isFavoriteComic = comicsFavoritesUseCase.isFavoriteComic(_comicSelected.value.id)
        }
   }

    fun getComicById(comicId: Long) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val call = async { getComicsUseCase.getComicById(comicId) }
            _comicSelected.value = call.await()
            //valido si esta en la lista de favoritos
            isFavoriteComic = comicsFavoritesUseCase.isFavoriteComic(_comicSelected.value.id)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }


    fun toggleFavorite() {
        viewModelScope.launch {
            val comic = comicsFavoritesUseCase.getFavoriteComicById(_comicSelected.value.id)
            isFavoriteComic = if (comic != null) {
                comicsFavoritesUseCase.deleteFavoriteComic(comic.id)
                false

            } else {
                comicsFavoritesUseCase.insertFavoriteComic(_comicSelected.value)
                true
            }
            loadFavoriteComics()
        }

    }
}

