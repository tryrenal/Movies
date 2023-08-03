package com.redveloper.movies.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.redveloper.movies.MyApp
import com.redveloper.movies.ui.ViewModelFactory
import com.redveloper.movies.ui.detail.DetailMovieActivity
import com.redveloper.movies.ui.theme.MoviesTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: HomeViewModel by viewModels { viewModelFactory }

    fun inject(){
        (application as MyApp).applicationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        setContent {
            MoviesTheme(
                darkTheme = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        viewModel = viewModel,
                        toDetailMovie = { id ->
                            DetailMovieActivity.navigate(this, id)
                        }
                    )
                }
            }
        }
    }
}