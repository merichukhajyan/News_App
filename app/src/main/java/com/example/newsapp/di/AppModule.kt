package com.example.newsapp.di

import com.example.newsapp.data.remote.ApiConstants
import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.newsapp.data.remote.ApiService
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.useCase.GetNewsByCountryUseCase
import com.example.newsapp.presentation.homeScreen.NewsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<ApiService>{
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BASIC)).build())
            .build()
            .create(ApiService::class.java)
    }

    single<NewsRepository>{
            NewsRepositoryImpl(get())
    }
    single <GetNewsByCountryUseCase>{
        GetNewsByCountryUseCase(get())
    }
    viewModel<NewsViewModel>{
        NewsViewModel(get())
    }
}