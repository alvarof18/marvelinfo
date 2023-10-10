package com.alvarof18.marvelinfo.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.alvarof18.marvelinfo.R


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier = modifier) {
        HeaderHomeScreen()
        Spacer(modifier = Modifier.height(32.dp))
        FormatComic(navController = navController)
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
fun FormatComic(navController: NavHostController) {

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Comics",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "See all", color = Color.Red,
                modifier = Modifier.clickable {})
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            repeat(10) {
                item { itemComic() }
            }
        }
    }
}


@Composable
fun itemComic(modifier: Modifier = Modifier) {

    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Box {
            AsyncImage(
                model = "https://i.annihil.us/u/prod/marvel/i/mg/d/d0/5727882d64025.jpg",
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
            text = "Ultimate Fantastic Four (2003) #13",
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


@Composable
fun CategoriesItems(categoryLabel: String, image: Int, onclick: () -> Unit) {
    Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.clickable { onclick() }) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = image),
            contentDescription = "comics_categories", contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .alpha(0.45f)
                .background(Color.White)
        )
        Text(
            text = categoryLabel,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewItemComic() {
    itemComic()
}