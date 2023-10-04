package com.alvarof18.marvelinfo.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBarIgnoringVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.tappableElement
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alvarof18.marvelinfo.TopBar

@Composable
fun Alltest() {

    val lista = (1..50).toList()

        Scaffold(topBar = { TopBarTest() }) {
        LazyColumn(
            modifier = Modifier.padding(it),
        ) {
            items(lista) {
                itemDetails()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTest() {
    CenterAlignedTopAppBar(

           title = { Text(text = "Prueba") },
        modifier = Modifier.background(Color.Transparent)


    )

}


@Composable
fun itemDetails(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp)
            .background(
                Color.Red.copy(alpha = 0.7f),

                ), contentAlignment = Alignment.Center
    ) {
        Text(text = "Hola ")
    }

}

@Preview(showBackground = true)
@Composable
fun previewAllTest() {

    Alltest()
}