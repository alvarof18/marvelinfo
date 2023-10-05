package com.alvarof18.marvelinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.alvarof18.marvelinfo.ui.model.MarvelBottomNavItem


@Composable
fun MarvelInfoApp(navController: NavHostController = rememberNavController()) {
    //Background all APP

    Box {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.background_app),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {

                TopBar(navController = navController)
            },
            bottomBar = { MarvelBottomBar(navController = navController) }

        ) { padding ->
            MarvelInfoNav(navController = navController, modifier = Modifier.padding(padding))
        }
    }
}

@Composable
fun MarvelBottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        MarvelBottomNavItem.HomeNavBar,
        MarvelBottomNavItem.ComicsNavBar,
        MarvelBottomNavItem.FavoritesNavBar,
    )

    if (currentRoute != Routes.ComicsDetails.route)
        NavigationBar(containerColor = Color.Black) {
            items.forEachIndexed { _, item ->
                NavigationBarItem(

                    selected = currentRoute == item.route,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(30.dp),
                            contentScale = ContentScale.Crop
                        )
                    },
                    label = { Text(text = item.title) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedTextColor = Color.Red,
                        unselectedTextColor = Color.White,
                        indicatorColor = Color.Transparent
                    )
                )
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
                if (currentRoute != Routes.ComicsDetails.route && (currentRoute != Routes.FavoriteComics.route)) {
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
            if ((currentRoute != Routes.Home.route) && (currentRoute != Routes.ComicsDetails.route) && (currentRoute != Routes.FavoriteComics.route)) {
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
