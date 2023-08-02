package com.redveloper.movies.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DetailScreen(
    movieId: Int,
    viewModel: DetailViewModel
) {

    val detailMovieEvent = viewModel.detailMovieEvent.observeAsState()
    val reviewMovieEvent = viewModel.reviewMovieEvent.observeAsState()
    val traillerEvent = viewModel.trailerMovieEvent.observeAsState()

    LaunchedEffect(true){
        viewModel.getDetailMovie(movieId)
        viewModel.getReviewMovie(movieId)
        viewModel.getTrailler(movieId)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        detailMovieEvent.value?.contentIfNotHaveBeenHandle?.let { movies ->
            Text(text = "title: ${movies.title}")
        }

        reviewMovieEvent.value?.contentIfNotHaveBeenHandle?.let { reviews ->
            reviews.forEach {
                Text(text = "review: ${it.author}")
            }
        }
        
        traillerEvent.value?.contentIfNotHaveBeenHandle?.let { trailler ->
            Text(text = "trailler: ${trailler.key}")
        }
    }
}