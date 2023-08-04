package com.redveloper.movies.ui.genre

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redveloper.movies.domain.entity.Genre

@Composable
fun GenreScreen(
    modifier: Modifier = Modifier,
    viewModel: GenreViewModel,
    onGenreClick: ((data: Genre) -> Unit)? = null
) {
    val genresEvent = viewModel.genresEvent.observeAsState()
    val errorEvent by viewModel.errorEvent.observeAsState()
    val loadingEvent by viewModel.loadingEvent.observeAsState()

    val dataGenres = remember {
        mutableListOf<Genre>()
    }

    LaunchedEffect(true){
        dataGenres.clear()
        viewModel.getGenreMovie()
    }

    genresEvent.value?.contentIfNotHaveBeenHandle?.let {
        dataGenres.addAll(it)
    }

    errorEvent?.contentIfNotHaveBeenHandle?.let {
        Toast.makeText(LocalContext.current, it, Toast.LENGTH_LONG).show()
    }

    loadingEvent?.contentIfNotHaveBeenHandle?.let { show ->
        if (show){
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            )
        }
    }

    GenreContent(
        modifier = modifier,
        genres = dataGenres,
        onClick = {
            onGenreClick?.invoke(it)
        }
    )
}

@Composable
fun GenreContent(
    modifier: Modifier = Modifier,
    genres: List<Genre>,
    onClick: ((Genre) -> Unit)
){

    val listState = rememberLazyGridState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Genre Movie",
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

        LazyVerticalGrid(
            modifier = modifier,
            state = listState,
            columns = GridCells.Fixed(3),
            content = {
                itemsIndexed(genres){ index, item ->
                    CardGenre(
                        data = item,
                        modifier = Modifier
                            .clickable {
                                onClick.invoke(item)
                            }
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGenreContent(){
    val genres = listOf(
        Genre(1, "Action"),
        Genre(1, "Action"),
        Genre(1, "Action"),
        Genre(1, "Action"),
        Genre(1, "Action"),
        Genre(1, "Action"),
    )
    MaterialTheme() {
        GenreContent(
            genres = genres,
            onClick = {

            }
        )
    }
}

@Composable
fun CardGenre(
    modifier: Modifier = Modifier,
    data: Genre
){
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Red
        )
    ) {
        Text(
            text = data.name,
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

@Preview(showBackground = true)
@Composable
fun PreviewCardGenre(){
    MaterialTheme{
        CardGenre(data = Genre(id = 0, name = "Action"))
    }
}