package com.redveloper.movies.di

import androidx.lifecycle.ViewModelProvider
import com.redveloper.movies.MainActivity
import com.redveloper.movies.MyApp
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
        ViewModelModule::class
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
}