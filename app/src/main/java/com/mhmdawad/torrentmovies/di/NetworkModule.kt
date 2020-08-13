package com.mhmdawad.torrentmovies.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mhmdawad.torrentmovies.data.source.ApiService
import com.mhmdawad.torrentmovies.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single{
       HttpLoggingInterceptor()
           .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single{
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        GsonConverterFactory.create()
    }
    single {
        CoroutineCallAdapterFactory()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<CoroutineCallAdapterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }

    factory {
        get<Retrofit>().create(ApiService::class.java)
    }
}