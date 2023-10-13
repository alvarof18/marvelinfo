package com.alvarof18.marvelinfo.ui.home

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.alvarof18.marvelinfo.R
import com.alvarof18.marvelinfo.config.Routes
import com.alvarof18.marvelinfo.domain.model.ComicsModel
import com.alvarof18.marvelinfo.ui.comics.ComicsViewModel
import com.alvarof18.marvelinfo.ui.comics.TypeComic


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    comicsViewmodel: ComicsViewModel
) {


    val comicList by comicsViewmodel.comicList.collectAsState()
    val magazineList by comicsViewmodel.magazineComicList.collectAsState()
    val digitalComicList by comicsViewmodel.digitalComicList.collectAsState()
    val uiStateComic by comicsViewmodel.uiState.collectAsState()


    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        HeaderHomeScreen()

        Spacer(modifier = Modifier.height(32.dp))
        FormatComic(
            navController = navController,
            listComics = comicList,
            title = "Comic",
            isLoading = uiStateComic.isLoading,
            onClick = {
                comicsViewmodel.loadComicsList(TypeComic.COMIC)
                navController.navigate(Routes.Comics.route)
            },
            loadComics = {
                comicsViewmodel.typeComicShow = TypeComic.COMIC
                comicsViewmodel.loadMoreComics()
            },
            selectComic = { comicsViewmodel.getComicById(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        FormatComic(
            navController = navController,
            listComics = magazineList,
            title = "Magazine",
            isLoading = uiStateComic.isLoading,
            onClick = {
                comicsViewmodel.loadComicsList(TypeComic.MAGAZINE)
                navController.navigate(Routes.Comics.route)
            }, loadComics = {
                comicsViewmodel.typeComicShow = TypeComic.MAGAZINE
                comicsViewmodel.loadMoreComics()
            },
            selectComic = { comicsViewmodel.getComicById(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        FormatComic(
            navController = navController,
            listComics = digitalComicList,
            title = "Digital Comics",
            isLoading = uiStateComic.isLoading,
            onClick = {
                comicsViewmodel.loadComicsList(TypeComic.DIGITAL)
                navController.navigate(Routes.Comics.route)
            },
            loadComics = {
                comicsViewmodel.typeComicShow = TypeComic.DIGITAL
                comicsViewmodel.loadMoreComics()
            },
            selectComic = { comicsViewmodel.getComicById(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))

    }
}

@Composable
fun HeaderHomeScreen(modifier: Modifier = Modifier) {

    //fading-edge-effect
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                .drawWithContent {
                    val colors = listOf(Color.Black, Color.Transparent)
                    drawContent()
                    drawRect(brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn)

                },
            painter = painterResource(id = R.drawable.header_screen_image),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

    }

}

@Composable
fun FormatComic(
    navController: NavHostController,
    listComics: List<ComicsModel> = emptyList(),
    title: String,
    onClick: () -> Unit,
    isLoading: Boolean,
    loadComics: () -> Unit,
    selectComic: (comicId: Long) -> Unit

) {
    val scrollState = rememberLazyListState()
    val isItemReachEndScroll by remember {
        derivedStateOf {
            scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ==
                    scrollState.layoutInfo.totalItemsCount - 5
        }
    }

    if (isItemReachEndScroll) {
        loadComics()
    }

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "See all", color = Color.Red,
                modifier = Modifier.clickable {
                    onClick()
                })
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (listComics.isEmpty()) {
            Row {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .height(160.dp)
                            .width(100.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .shimmerEffect(true)
                    )

                }
            }

        }

        LazyRow(state = scrollState, verticalAlignment = Alignment.CenterVertically) {
            items(listComics) { comic ->
                itemComic(comic = comic, onClick = {
                    selectComic(comic.id)
                    navController.navigate(
                        Routes.ComicsDetails.route.replace(
                            oldValue = "{comicId}", newValue = comic.id.toString()
                        )
                    )
                })
            }
            if (isLoading) {
                item {
                    CircularProgressIndicator(color = Color.Red)
                }
            }
        }
    }
}


@Composable
fun itemComic(modifier: Modifier = Modifier, comic: ComicsModel, onClick: () -> Unit) {


    Column(modifier = Modifier
        .padding(horizontal = 8.dp)
        .clickable { onClick() }) {
        Box {
            SubcomposeAsyncImage(
                model = comic.urlPoster,
                loading = { CircularProgressIndicator(color = Color.Red) },
                contentDescription = null,
                modifier = Modifier
                    .height(160.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = comic.title,
            modifier = Modifier.width(100.dp),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            lineHeight = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )
    }

}

fun Modifier.shimmerEffect(enable: Boolean): Modifier = composed {
    if (enable) {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        val transition = rememberInfiniteTransition(label = "")
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000)
            ), label = ""
        )
        background(
            brush = Brush.linearGradient(
                colors = listOf(
              //      Color(0xFFB8B5B5),
                    Color.Red.copy(alpha = 0.87f),
                    Color.Red.copy(alpha = 0.33f),
                    Color.Transparent,
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            )
        )
            .onGloballyPositioned {
                size = it.size
            }
    } else {
        Modifier
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController, comicsViewmodel = viewModel())
}
