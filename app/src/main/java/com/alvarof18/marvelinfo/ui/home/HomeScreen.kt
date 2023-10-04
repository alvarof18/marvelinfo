package com.alvarof18.marvelinfo.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alvarof18.marvelinfo.R
import com.alvarof18.marvelinfo.ui.model.getAllCategories


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier = modifier) {
        HeaderHomeScreen()
        Categories(navController = navController)
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
fun Categories(navController: NavHostController) {
    val listCategories = getAllCategories
    Text(
        text = "Categories",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .alpha(0.7f),
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White
    )
    listCategories.forEach { category ->
        CategoriesItems(
            categoryLabel = category.name,
            image = category.imageCategory,
            onclick = { navController.navigate(category.route) })
        Spacer(modifier = Modifier.height(24.dp))
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