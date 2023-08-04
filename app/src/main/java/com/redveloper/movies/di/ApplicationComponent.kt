package com.redveloper.movies.di

import androidx.lifecycle.ViewModelProvider
import com.redveloper.movies.ui.home.MainActivity
import com.redveloper.movies.MyApp
import com.redveloper.movies.ui.detail.DetailMovieActivity
import com.redveloper.movies.ui.genre.GenreActivity
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DatabaseModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(myApp: MyApp): Builder

        fun build(): ApplicationComponent
    }

    fun retrofit(): Retrofit
    fun viewModelProviderFactory(): ViewModelProvider.Factory

    fun inject(mainActivity: MainActivity)
    fun inject(detailMovieActivity: DetailMovieActivity)
    fun inject(genreActivity: GenreActivity)
}