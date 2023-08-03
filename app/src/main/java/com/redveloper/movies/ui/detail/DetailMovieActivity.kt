package com.redveloper.movies.ui.detail

import android.app.Activity
import android.content.Intent
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
import com.redveloper.movies.ui.theme.MoviesTheme
import javax.inject.Inject

class DetailMovieActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: DetailViewModel by viewModels { viewModelFactory }

    fun inject(){
        (application as MyApp).applicationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        setContent {
            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    intent.extras?.let {
                        val movieId = it.getInt(KEY_MOVIE_ID, 0)
                        DetailScreen(
                            movieId = movieId,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }

    companion object{
        private const val KEY_MOVIE_ID = "KEY_MOVIE_ID"

        fun navigate(activity: Activity, movieId: Int){
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(KEY_MOVIE_ID, movieId)
            activity.startActivity(intent)
        }
    }
}
