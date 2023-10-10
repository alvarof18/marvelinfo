package com.alvarof18.marvelinfo.ui.comics


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.alvarof18.marvelinfo.domain.model.CreatorsModel

@Composable
fun ComicDetailScreen(
    comicId: String,
    comicViewModel: ComicsViewModel,
    navController: NavHostController
) {

    val comicSelected by comicViewModel.comicSelected.collectAsState()


    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
        ) {

            Box {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    model = comicSelected.urlPoster,
                    contentDescription = "Test",
                    contentScale = ContentScale.FillBounds,
                )

                //   Gradiant black
                IconButton(
                    onClick = { comicViewModel.toggleFavorite() },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = if (comicViewModel.isFavoriteComic) Icons.Filled.Favorite else Icons.Sharp.FavoriteBorder,
                        contentDescription = "",
                        tint = Color.Red,

                        )
                }

                /*            Box(
                                modifier = Modifier
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                Color.Black.copy(alpha = 0.80f),
                                                Color.Transparent,
                                            ), start = Offset(0.0f, 0.0f),
                                            end = Offset(200.0f, 250f)
                                        )
                                    )
                                    .fillMaxWidth()
                                    .height(250.dp)
                            )
            */
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(80.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    model = comicSelected.urlPoster,
                    contentDescription = "Test",
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = comicSelected.title, fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Pages: ${comicSelected.pages}", fontSize = 12.sp)
                    if (comicSelected.format.isNotEmpty()) {
                        Text(text = "Format: ${comicSelected.format}", fontSize = 12.sp)
                    }
                    HorizontalDivider()
                    Text(text = "Creators", fontSize = 12.sp)
                    comicSelected.creators.forEach { creator ->
                        CreateCard(creator)

                    }

                }
            }
        }
    }
}


@Composable
fun CreateCard(creator: CreatorsModel) {
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        Text(text = creator.name, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text(text = creator.role, fontSize = 12.sp)
    }
}


@Composable
fun GradientBox() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.80f),
                        Color.Transparent,
                    ), start = Offset(0.0f, 0.0f),
                    end = Offset(200.0f, 250f)
                )
            )
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun previewComicDetailScreen() {

    //  ComicDetailScreen(comicId = "12", comicViewModel = viewModelComic)
    GradientBox()
}

