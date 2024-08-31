package com.example.imageloading_with_coil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import com.example.imageloading_with_coil.ui.theme.ImageLoading_with_coilTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageLoading_with_coilTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                Column (
                    modifier = Modifier.fillMaxSize()
                ){

                    val imageUrl = "https://cdn.pixabay.com/photo/2024/05/26/10/15/bird-8788491_1280.jpg"
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "bird image",
                        modifier = Modifier.fillMaxWidth()
                            .aspectRatio(1080f/720f),
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            imageLoader.diskCache?.clear()
                            imageLoader.memoryCache?.clear()

                            //clear() will erase all loading images
                            //if we want to erase only one image: remove() like this:-
//                            imageLoader.diskCache?.remove(imageUrl)
//                            imageLoader.memoryCache?.remove(MemoryCache.Key(imageUrl))
                        }
                    ) {
                        Text("Clear Cache")
                    }

                }


                    /**
                     * coil uses caches to store image
                     * i uses two types of caches:
                     * 1) memory cache and
                     * 2) disk cache
                     *
                     * memory cache keeps the images in the ram
                     * and disk cache keeps the images in the internal storage
                     *
                     * loading from ram is faster than loading from internal storage
                     *
                     * default cacheing are both. coil analize the image size. if the image size is big coil cache it in memory
                     * else it caches in the ram. how ever we can control this behaviour as ourself in our code
                     *
                     * we can do this in an application class
                     *
                     */


                }
            }
        }
    }
}


//dependency coil
//implementation("io.coil-kt:coil-compose:2.7.0")


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageLoading_with_coilTheme {

    }
}