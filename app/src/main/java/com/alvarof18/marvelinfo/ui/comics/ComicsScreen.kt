package com.alvarof18.marvelinfo.ui.comics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.alvarof18.marvelinfo.config.Routes
import com.alvarof18.marvelinfo.domain.model.ComicsModel


@Composable
fun ComicsScreen(
    modifier: Modifier = Modifier,
    comicViewModel: ComicsViewModel,
    navController: NavHostController
) {

    val uiComicState by comicViewModel.uiState.collectAsState()
    val scrollState = rememberLazyGridState()
    val isItemReachEndScroll by remember {
        derivedStateOf {
            scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                    scrollState.layoutInfo.totalItemsCount - 1
        }
    }

    if (isItemReachEndScroll) {
        comicViewModel.loadMoreComics()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        if (uiComicState.listComics.isEmpty()) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
              //  Image(painter = painterResource(id = R.drawable.loading), contentDescription = "Loading")

             CircularProgressIndicator(color = Color.Red)
            }

        }

        LazyVerticalGrid(
            state = scrollState,
            columns = GridCells.Fixed(2),
        ) {
            itemsIndexed(
                uiComicState.listComics,
                key = { index, _ -> index }) { _, comic ->
                ComicItemCard(
                    comic = comic,
                    viewModelComic = comicViewModel,
                    navigateTo = {
                        navController.navigate(
                            Routes.ComicsDetails.route.replace(
                                oldValue = "{comicId}", newValue = comic.id.toString()
                            )
                        )
                    })
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                if (uiComicState.isLoading && uiComicState.listComics.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = Color.Red)

                    }
                }
            }

        }
    }

}

//Todo {
// - Colocar una imagen gif para el loading
// - Realizar la peticion HTTP para buscar informacion del comic,
// - Crear viewModelDetalle, navegar desde la principal hacia el detalle
// }

@Composable
fun ComicItemCard(comic: ComicsModel, navigateTo: () -> Unit, viewModelComic: ComicsViewModel) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                viewModelComic.loadInfoComic(idComic = comic.id.toInt())
                navigateTo()
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            AsyncImage(
                model = comic.urlPoster,
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )
        }
        Text(
            text = comic.title,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            fontWeight = FontWeight.SemiBold,
            color = Color(0xffBCBCBC)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewComicsScreen() {

    val navController = rememberNavController()
   // ComicsScreen(navController = navController)
}