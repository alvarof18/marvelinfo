package com.alvarof18.marvelinfo.ui.model

import android.icu.text.CaseMap.Title
import androidx.annotation.DrawableRes
import com.alvarof18.marvelinfo.R
import com.alvarof18.marvelinfo.config.Routes

data class CategoriesModel(val title:String, val route:String)

val getAllCategories = listOf(
    CategoriesModel(title = "Comics", route = Routes.Comics.route)


)

