package com.redveloper.movies.ui.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.redveloper.movies.R
import com.redveloper.movies.ui.home.model.MoviesUiModel
import com.redveloper.movies.utils.Constant
import dagger.Lazy


@Composable
fun HomeScreen(
    viewModel: HomeViewModel
){
    val movieEvents = viewModel.moviesEvent.observeAsState()
    val errorEvents = viewModel.showErrorEvent.observeAsState()
    val loadingEvents = viewModel.loadingEvent.observeAsState()

    val moviesUiModel = remember { mutableListOf<MoviesUiModel>() }

    LaunchedEffect(true){
        moviesUiModel.clear()
        viewModel.getMovies()
    }

    movieEvents.value?.contentIfNotHaveBeenHandle?.let {
        val items = it.map {
            MoviesUiModel(
                urlImage = Constant.BASE_URL_IMAGE + it.backdropPath,
                movieTitle = it.originalTitle ?: ""
            )
        }
        moviesUiModel.addAll(items)
    }

    HomeContent(
        listMovie = moviesUiModel,
        modifier = Modifier
            .fillMaxSize(),
        scrollToBottomCallback = {
            viewModel.loadMoreMovies()
        }
    )

    loadingEvents.value?.contentIfNotHaveBeenHandle?.let { show ->
        if (show){
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            )
        }
    }

    errorEvents.value?.contentIfNotHaveBeenHandle?.let { message ->
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    listMovie: List<MoviesUiModel>,
    scrollToBottomCallback: (() -> Unit)? = null
){
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        state = listState,
        content = {
            itemsIndexed(items = listMovie){ index, item ->
                CardMovie(
                    urlImage = item.urlImage,
                    text = item.movieTitle,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
            }
        }
    )

    listState.OnBottomReached {
        scrollToBottomCallback?.invoke()
    }
}

@Composable
private fun LazyGridState.OnBottomReached(
    buffer : Int = 0,
    onLoadMore : () -> Unit
) {
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                return@derivedStateOf true

            lastVisibleItem.index >=  layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) onLoadMore() }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent(){
    val imageUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    val datas = listOf(
        MoviesUiModel(urlImage = imageUrl, movieTitle = "Title 1"),
        MoviesUiModel(urlImage = imageUrl, movieTitle = "Title 1"),
        MoviesUiModel(urlImage = imageUrl, movieTitle = "Title 1"),
        MoviesUiModel(urlImage = imageUrl, movieTitle = "Title 1"),
        MoviesUiModel(urlImage = imageUrl, movieTitle = "Title 1"),
    )
    MaterialTheme {
        HomeContent(
            listMovie = datas,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}



@Composable
fun CardMovie(
    modifier: Modifier = Modifier,
    urlImage: String,
    text: String
){
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(200.dp)
            .width(100.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            AsyncImage(
                model = urlImage,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(color = Color.Gray)
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardMovie(){
    val imageUrl = "https://image.tmdb.org/t/p/original/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg"
    CardMovie(
        urlImage = imageUrl,
        text = "image Url"
    )
}