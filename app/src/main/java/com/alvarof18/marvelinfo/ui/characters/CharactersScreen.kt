package com.alvarof18.marvelinfo.ui.characters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alvarof18.marvelinfo.R


@Composable
fun CharactersScreen(modifier:Modifier = Modifier) {

    val list = listOf(1, 2, 3, 5, 6, 7, 78, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8)
    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(list) {
                CharacterItemCard()
            }
        }

    }
}

@Composable
fun CharacterItemCard() {
    Card(

        modifier = Modifier
            .width(120.dp)
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.categories_comics_image),
                contentDescription = null,
                modifier = Modifier
                    .height(205.dp),

                contentScale = ContentScale.Crop
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.BottomEnd)) {
                Icon(
                    imageVector = Icons.Sharp.FavoriteBorder,
                    contentDescription = "",
                    tint = Color.Red
                )
            }
        }
        Text(
            text = "Ant-Man & the Wasp (2018) #3",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            maxLines = 2,
            modifier = Modifier.padding(top = 8.dp),
            fontWeight = FontWeight.SemiBold,
            color = Color(0xffBCBCBC)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharactersScreen() {
    CharactersScreen()
}