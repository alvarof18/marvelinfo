package com.alvarof18.marvelinfo.ui.model

import com.alvarof18.marvelinfo.R
import com.alvarof18.marvelinfo.config.Routes

sealed class MarvelBottomNavItem(val title:String, val icon:Int, val route:String){
    object HomeNavBar:MarvelBottomNavItem(title = "Home", icon = R.drawable.home_icon, route = Routes.Home.route)
    object ComicsNavBar:MarvelBottomNavItem(title = "Comics", icon = R.drawable.comics_icon, route = Routes.Comics.route)
    object FavoritesNavBar:MarvelBottomNavItem(title = "Favorites", icon = R.drawable.favorite_icon, route = Routes.FavoriteComics.route)
}
