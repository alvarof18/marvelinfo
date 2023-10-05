package com.alvarof18.marvelinfo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.core.view.WindowCompat
import androidx.room.Room
import com.alvarof18.marvelinfo.data.local.MarvelDatabase
import com.alvarof18.marvelinfo.ui.theme.MarvelinfoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelinfoTheme {
                MarvelInfoApp()
            }
        }
    }
}

