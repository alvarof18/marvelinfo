package com.alvarof18.marvelinfo.config

sealed class Routes(val route:String){
    object Home:Routes(route = "home")
    object Comics:Routes(route = "comics")
    object Characters:Routes(route = "characters")
    object FavoriteComics:Routes(route = "favoriteComics")
    object ComicsDetails:Routes(route = "comicDetail/{comicId}")
 }
