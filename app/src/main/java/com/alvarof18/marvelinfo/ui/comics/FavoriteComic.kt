package com.alvarof18.marvelinfo.ui.comics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.alvarof18.marvelinfo.config.Routes
import com.alvarof18.marvelinfo.domain.model.ComicsModel

@Composable
fun FavoriteComicScreen(comicViewModel: ComicsViewModel, navController: NavHostController) {

    val favoriteList = comicViewModel.favoriteList

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)
    ) {
        items(favoriteList, key = {
            it.comic.id
        }) {
            GridItem(comic = it.comic, height = it.height, onClick = {
                comicViewModel.loadInfoComic(it.comic.id.toInt())

                navController.navigate(
                    Routes.ComicsDetails.route.replace(
                        oldValue = "{comicId}",
                        newValue = it.comic.id.toString()
                    )
                )

            })
        }
    }
}

@Composable
fun GridItem(comic: ComicsModel, height: Dp, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .height(height = height)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = comic.urlPoster,
            contentDescription = comic.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Preview
@Composable
fun PreviewFavoriteComicScreen() {
    // FavoriteComicScreen(comicViewModel = viewModel(), navController = navController)
}