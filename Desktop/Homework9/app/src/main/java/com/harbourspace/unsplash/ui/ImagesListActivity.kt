package com.harbourspace.unsplash.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.harbourspace.unsplash.ui.details.DetailsActivity
import com.harbourspace.unsplash.ui.theme.UnsplashTheme

class ImagesListActivity : ComponentActivity() {

    private val imagesViewModel: ImagesViewModel by viewModels()
    private val imageGetModel: ImageGetModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnsplashTheme {

                val images = imagesViewModel.items.observeAsState(emptyList())
                imagesViewModel.fetchImages()
                LazyColumn(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) {
                    items(images.value) { image ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { openDetails(image.id) },
                            color = Color.LightGray
                        ) {
                            Log.d("samanCheck", "this is the url ${image.urls}")

                            val painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(image.urls?.regular)
                                    .build()
                            )
                            Image(
                                painter = painter,
                                contentDescription = image.description,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop,

                                )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Text(image.user?.name ?: "")

                                Spacer(modifier = Modifier.height(8.dp))

                                image.description?.let { description ->
                                    Text(
                                        text = description,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun openDetails(imageId: String) {
        imageGetModel.getImageById(imageId)
        imageGetModel.itemI.observe(this) { image ->
            image?.let {
                val intent = Intent(this, DetailsActivity::class.java).apply {
                    putExtra("image", it) // assuming ImageDatas implements Parcelable
                }
                startActivity(intent)
                startActivity(intent)
            }
        }
    }
}