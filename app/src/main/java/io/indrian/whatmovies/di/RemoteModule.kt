package io.indrian.whatmovies.di

import io.indrian.whatmovies.BuildConfig
import io.indrian.whatmovies.data.source.remote.services.MovieService
import io.indrian.whatmovies.data.source.remote.services.TVShowService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val remoteModule = module {

    factory(named("API_KEY")) {
        Interceptor { chain ->

            val newUrl = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    factory<Interceptor>(named("LOGGER")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("API_KEY")))
            .addInterceptor(get<Interceptor>(named("LOGGER")))
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
    single { get<Retrofit>().create(TVShowService::class.java) }

}
