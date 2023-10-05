package com.alvarof18.marvelinfo.domain.usecases

import androidx.compose.ui.unit.dp
import com.alvarof18.marvelinfo.data.MarvelRepositoryLocalImpl
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.ui.model.FavoriteComicModel
import kotlin.random.Random

class ComicsFavoritesUseCase {
    private val repositoryLocal = MarvelRepositoryLocalImpl()

    suspend fun getFavoriteComicById(comicId:Long): ComicsModel?{
        return repositoryLocal.getFavoriteComic(comicId)
    }

    suspend fun isFavoriteComic(comicId: Long):Boolean{
        val comic = getFavoriteComicById(comicId)
        return comic != null
    }

    suspend fun getAllFavoriteComics():List<FavoriteComicModel>{
        val favoriteComicsDb = repositoryLocal.getAllFavoriteComics()
       return  favoriteComicsDb.map {
            val randomHeight = Random.nextInt(150, 250).dp
                FavoriteComicModel(comic = it, height = randomHeight)
        }
    }

    suspend fun insertFavoriteComic(comic: ComicsModel) {
        repositoryLocal.insertFavoriteComic(comic)
    }

    suspend fun deleteFavoriteComic(comicId: Long){
        repositoryLocal.deleteFavoriteComic(comicId)
    }

}