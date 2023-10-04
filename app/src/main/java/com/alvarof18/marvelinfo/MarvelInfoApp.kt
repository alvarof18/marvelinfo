package com.alvarof18.marvelinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alvarof18.marvelinfo.config.MarvelInfoNav
import com.alvarof18.marvelinfo.config.Routes


@Composable
fun MarvelInfoApp(navController: NavHostController = rememberNavController()) {
    //Background all APP

    Box {
        Image(
            modifier = Modifier
                .fillMaxSize()
               ,
            painter = painterResource(id = R.drawable.background_app),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {

                TopBar(navController = navController)
            }) { padding ->
            MarvelInfoNav(navController = navController, modifier = Modifier.padding(padding))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

        TopAppBar(

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),


            title = {
                currentRoute?.uppercase()?.let {
                    if (currentRoute != Routes.ComicsDetails.route) {
                        Text(
                            textAlign = TextAlign.Center,
                            text = it,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White
                        )
                    }
                }
            },
            navigationIcon = {
                if (currentRoute != Routes.Home.route) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            },

            actions = {
                if ((currentRoute != Routes.Home.route) && (currentRoute != Routes.ComicsDetails.route)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }

            },
        )


}

@Preview
@Composable
fun PreviewMarvelApp() {
    MarvelInfoApp()
}
