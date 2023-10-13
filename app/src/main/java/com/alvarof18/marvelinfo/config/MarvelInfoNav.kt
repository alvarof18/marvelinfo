package com.alvarof18.marvelinfo.config


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alvarof18.marvelinfo.ui.characters.CharactersScreen
import com.alvarof18.marvelinfo.ui.comics.ComicDetailScreen
import com.alvarof18.marvelinfo.ui.comics.ComicsScreen
import com.alvarof18.marvelinfo.ui.comics.ComicsViewModel
import com.alvarof18.marvelinfo.ui.comics.FavoriteComicScreen
import com.alvarof18.marvelinfo.ui.comics.TypeComic
import com.alvarof18.marvelinfo.ui.home.HomeScreen
import com.alvarof18.marvelinfo.ui.search.SearchScreen
import com.alvarof18.marvelinfo.ui.search.SearchViewModel


@Composable
fun MarvelInfoNav(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val viewModelComic: ComicsViewModel = viewModel()
    val viewModelSearch: SearchViewModel = viewModel()
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = Routes.Home.route,
          enterTransition = { EnterTransition.None},
          exitTransition = { ExitTransition.None }
    ) {
        composable(route = Routes.Home.route) {
            HomeScreen(
                navController = navController, comicsViewmodel = viewModelComic

            )
        }
        composable(route = Routes.Comics.route) {
                ComicsScreen(
                navController = navController,
                comicViewModel = viewModelComic,

            )
        }
        composable(route = Routes.FavoriteComics.route) {
            FavoriteComicScreen(
                comicViewModel = viewModelComic,
                navController = navController
            )
        }
        composable(route = Routes.Characters.route) { CharactersScreen() }
        composable(route = Routes.SearchComic.route) {
            SearchScreen(
                navController = navController,
                viewModelSearch = viewModelSearch,
                viewModelComic
            )
        }
        composable(route = Routes.ComicsDetails.route) { backStackEntry ->
            ComicDetailScreen(
                comicViewModel = viewModelComic,
                navController = navController,
                comicId = backStackEntry.arguments?.getString(
                    "comicId"
                ).orEmpty()
            )
        }

    }
}
