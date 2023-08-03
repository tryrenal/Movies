package com.redveloper.movies.ui.detail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.redveloper.movies.R
import com.redveloper.movies.domain.entity.DetailMovie
import com.redveloper.movies.utils.Constant
import java.util.Date

@Composable
fun DetailScreen(
    movieId: Int,
    viewModel: DetailViewModel
) {

    val detailMovieEvent by viewModel.detailMovieEvent.observeAsState()
    val reviewMovieEvent by viewModel.reviewMovieEvent.observeAsState()
    val errorEvent  by viewModel.errorEvent.observeAsState()

    val movieData = remember{
        mutableStateOf<DetailMovie>(DetailMovie())
    }

    LaunchedEffect(true){
        viewModel.getDetailMovie(movieId)
        viewModel.getReviewMovie(movieId)
    }

    errorEvent?.contentIfNotHaveBeenHandle?.let { message ->
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
    }


    detailMovieEvent?.contentIfNotHaveBeenHandle?.let { movie ->
        movieData.value = movie
        Log.i("dataMovie", movie.toString())
    }

    reviewMovieEvent?.contentIfNotHaveBeenHandle?.let { reviews ->

    }

    DetailContent(
        modifier = Modifier
            .fillMaxSize(),
        data = movieData.value
    )
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: DetailMovie
){
    LazyColumn(
        modifier = modifier,
        content = {
            item {
                CardImageHeader(
                    urlImage = data.backdropPath,
                    title = data.originalTitle,
                    tagline = data.tagline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }

            item {
                Text(
                    text = "Story",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                )
            }

            item{
                Text(
                    text = data.overview,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                )
            }

            item {
                Text(
                    text = "Trailler",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                )
            }

            item {
                CardTraillerMovie(
                    videoUri = data.youtubeKey,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp
                        )
                        .height(300.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailContent(){
    val imageUrl = "/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    val desc = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
    val data = DetailMovie(
        id = 0,
        youtubeKey = "youtubekey",
        backdropPath = imageUrl,
        originalTitle = "Original Title Movie",
        overview = desc,
        posterPath = imageUrl,
        releaseDate = Date(),
        status = "now playing",
        tagline = "It is good movie"
    )

    MaterialTheme{
        DetailContent(
            modifier = Modifier
                .fillMaxSize(),
            data = data
        )
    }
}

@Composable
fun CardImageHeader(
    modifier: Modifier = Modifier,
    urlImage: String,
    title: String,
    tagline: String
){
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            AsyncImage(
                model = Constant.BASE_URL_IMAGE + urlImage,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.3f))
                    .blur(16.dp)
            )

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(10.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )

                Text(
                    text = tagline,
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardImageHeader(){
    val imageUrl = "/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    MaterialTheme() {
        CardImageHeader(
            urlImage = imageUrl,
            title = "Hai Barbie",
            tagline = "Barbie is good movie right know"
        )
    }
}

@Composable
fun CardTraillerMovie(
    modifier: Modifier = Modifier,
    videoUri: String
){
    if (videoUri.isNotBlank()) {
        AndroidView(
            modifier = modifier,
            factory = {
                val view = YouTubePlayerView(it)
                view.addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.loadVideo(videoUri, 0f)
                        }
                    }
                )
                view
            })
    }
}