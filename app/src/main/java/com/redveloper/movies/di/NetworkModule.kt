package com.redveloper.movies.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [RetrofitApiServiceModule::class])
class NetworkModule {

    //baseurl
    //https://api.themoviedb.org

    //token
    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNjg2MjhiMTFhZWFlZWQ1NDE1ZDBhNzljMjNhNWZlNSIsInN1YiI6IjY0Y2E2NWI1MGI3NGU5MDEyYzcxMjdlMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ImkwHugR6b9uLGy31XRE6O_zyKNEHw2DZY0PZC8U7GU"

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context) : OkHttpClient{
        val builder = OkHttpClient.Builder()

        val referrerInterceptor = Interceptor { chain ->
            val chainBuilder = chain.request()
                .newBuilder()
                .addHeader("Authorization","Bearer $token")
            val request = chainBuilder.build()
            chain.proceed(request)
        }
        builder.addInterceptor(referrerInterceptor)
        builder.addInterceptor(ChuckerInterceptor.Builder(context).build())
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.connectTimeout(60, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}