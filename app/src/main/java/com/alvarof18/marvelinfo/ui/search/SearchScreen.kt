package com.alvarof18.marvelinfo.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alvarof18.marvelinfo.config.Routes
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.ui.comics.ComicsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModelSearch: SearchViewModel,
    viewModelComic: ComicsViewModel
) {

    var query by remember {
        mutableStateOf("")
    }

    var showClear by remember {
        mutableStateOf(false)
    }

    var active by remember {
        mutableStateOf(true)
    }

    val controller = LocalSoftwareKeyboardController.current

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        },
        trailingIcon = {
            if (showClear)
                IconButton(onClick = {
                    query = ""
                    showClear = false
                }) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                }
        },
        colors = SearchBarDefaults.colors(containerColor = Color.White),
        placeholder = { Text(text = "Search Comic") },
        shape = SearchBarDefaults.fullScreenShape,
        query = query,
        onQueryChange = {
            query = it
            showClear = query.isNotEmpty()
            viewModelSearch.searchComic(query)

        },
        onSearch = {
            viewModelSearch.searchComic(query)
            controller?.hide()
        },
        active = active,
        onActiveChange = { active = it }) {

        //Resultado de la busqueda
        if (viewModelSearch.listResult.isEmpty()) {
            NotFoundData()
        }
        LazyColumn() {
            items(viewModelSearch.listResult) { comic ->
                ResultItem(comic = comic) {

                    viewModelComic.getComicById(comicId = comic.id)
                    navController.navigate(
                        Routes.ComicsDetails.route.replace(
                            oldValue = "{comicId}",
                            newValue = comic.id.toString()
                        )
                    ){
                       // popUpTo(Routes.SearchComic.route){inclusive = true}
                        launchSingleTop = true
                    }
                }
            }
        }
    }

}

@Composable
fun NotFoundData() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Not found results")
    }
}

@Composable
fun ResultItem(comic: ComicsModel, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(comic.urlPoster).crossfade(true)
                .build(), contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            Modifier
                .fillMaxHeight()
                .padding(vertical = 8.dp)
        ) {
            Text(text = comic.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Pages: ${comic.pages}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Format: ${comic.format}")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun previewSearchScreen() {

    //  SearchScreen(navController, viewModelSearch)
}

