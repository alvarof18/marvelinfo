package com.alvarof18.marvelinfo

sealed class NavigationItem(var route: String, var title: String) {
    object Home : NavigationItem("home", "Home")
    object Music : NavigationItem("music", "Music")
    object Movies : NavigationItem("movies", "Movies")
    object Books : NavigationItem("books", "Books")
    object Profile : NavigationItem("profile",  "Profile")
}