package com.redveloper.movies.ui.genre

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
import com.redveloper.movies.ui.home.MainActivity
import com.redveloper.movies.ui.theme.MoviesTheme
import javax.inject.Inject

class GenreActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: GenreViewModel by viewModels { viewModelFactory }

    fun inject(){
        (application as MyApp).applicationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        setContent {
            MoviesTheme (
                darkTheme = false
            ){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GenreScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                        viewModel = viewModel,
                        onGenreClick = { genre ->
                            MainActivity.navigate(this, genre)
                        }
                    )
                }
            }
        }
    }
}
