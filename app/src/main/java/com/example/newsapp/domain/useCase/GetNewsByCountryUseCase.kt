package com.example.newsapp.domain.useCase

import android.util.Log
import com.example.newsapp.data.dto.toNewsResponse
import com.example.newsapp.data.remote.Resource
import com.example.newsapp.domain.model.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetNewsByCountryUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(country: String): Flow<Resource<NewsResponse>> = flow {
        try {
            emit(Resource.Loading<NewsResponse>())
            val news: NewsResponse = newsRepository.fetchNews(country).toNewsResponse()
            emit(Resource.Success<NewsResponse>(data = news))
        }catch (e: HttpException){
            Log.d("GetNewsByCountry",e.message())
            emit(Resource.Error<NewsResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            e.message?.let { Log.d("IOException GetNewsByCountryUseCase", it) }
            emit(Resource.Error<NewsResponse>("Couldn't reach server"))
        }
    }
}