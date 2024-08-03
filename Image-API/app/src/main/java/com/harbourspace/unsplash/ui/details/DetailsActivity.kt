package com.harbourspace.unsplash.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.harbourspace.unsplash.data.ImageDatas
import com.harbourspace.unsplash.model.UnsplashItem
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

class DetailsActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val image = intent.getParcelableExtra<ImageDatas>("image")
            ?: throw IllegalArgumentException("Missing image data")
        setContent {
            UnsplashTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    DetailsScreen(image)
                }
            }
        }
    }
}