package com.redveloper.movies.ui.home

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
import com.redveloper.movies.domain.entity.Genre
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
                    intent.extras?.let {
                        val genreId = it.getInt(KEY_GENRE_ID, 0)
                        val genreName = it.getString(KEY_GENRE_NAME, "")

                        HomeScreen(
                            viewModel = viewModel,
                            toDetailMovie = { id ->
                                DetailMovieActivity.navigate(this, id)
                            },
                            genreId = genreId,
                            genreName = genreName
                        )
                    }
                }
            }
        }
    }

    companion object{

        private const val KEY_GENRE_ID = "KEY_GENRE_ID"
        private const val KEY_GENRE_NAME = "KEY_GENRE_NAME"
        fun navigate(activity: Activity, data: Genre? = null){
            val intent = Intent(activity, MainActivity::class.java)
            data?.id?.let {
                intent.putExtra(KEY_GENRE_ID, data.id)
            }
            data?.name?.let {
                intent.putExtra(KEY_GENRE_NAME, data.name)
            }
            activity.startActivity(intent)
        }
    }
}