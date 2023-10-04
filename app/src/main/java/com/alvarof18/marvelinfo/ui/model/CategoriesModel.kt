package com.alvarof18.marvelinfo.ui.model

import androidx.annotation.DrawableRes
import com.alvarof18.marvelinfo.R
import com.alvarof18.marvelinfo.config.Routes

data class CategoriesModel(val name: String, @DrawableRes val imageCategory: Int, val route:String)

val getAllCategories = listOf(
    CategoriesModel(
        name = "Comics",
        imageCategory = R.drawable.categories_comics_image,
        route = Routes.Comics.route
    ), CategoriesModel(
        name = "Characters",
        imageCategory = R.drawable.categories_characters_image,
        route = Routes.Characters.route
    )
)


