/*
package com.alvarof18.marvelinfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alvarof18.marvelinfo.ui.home.HomeScreen
import com.alvarof18.marvelinfo.ui.theme.MarvelinfoTheme


@Composable
fun MarvelApp(marvelViewModel: MarvelViewModel = viewModel()) {
    val marvelUiState by marvelViewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {

        Button(
            onClick = { marvelViewModel.onGetAllComics() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(text = "Get Comics")
        }

        LazyColumn {
            items(marvelUiState) { comics ->
                Text(text = comics.title.substring(1, 5))
            }
        }

    }
}


@Composable
fun MyBottomApp() {

    val navController = rememberNavController()

    Scaffold(
     //   topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Navigation(navController = navController)
            }

        }

    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelinfoTheme {
        MyBottomApp()
    }
}



@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavigationItem.Music.route) {
            MusicScreen()
        }
        composable(NavigationItem.Movies.route) {
            MoviesScreen()
        }
        composable(NavigationItem.Books.route) {
            BooksScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Music,
        NavigationItem.Movies,
        NavigationItem.Books,
        NavigationItem.Profile
    )
    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            NavigationBarItem(
                label = { Text(text = it.title) },
                selected = currentRoute == it.route, onClick = {
                    navController.navigate(it.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }, icon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null
                    )
                })
        }
    }
}*/
