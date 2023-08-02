package com.redveloper.movies.ui.home

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
fun HomeScreen(
    viewModel: HomeViewModel
){
    val movieEvents = viewModel.moviesEvent.observeAsState()

    LaunchedEffect(true){
        viewModel.getMovies()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        movieEvents.value?.contentIfNotHaveBeenHandle?.let { movies ->
            movies.forEach { movie ->
                Text(text = movie.title ?: "")
            }
        }
    }
}