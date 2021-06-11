package io.indrian.whatmovies.di

import io.indrian.whatmovies.BuildConfig
import io.indrian.whatmovies.data.services.MovieService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val remoteModule = module {

    factory<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MovieService::class.java) }

}
