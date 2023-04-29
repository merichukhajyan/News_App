package com.example.newsapp.data.repository

import com.example.newsapp.data.dto.NewsResponseDto
import com.example.newsapp.data.remote.ApiService
import com.example.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val api: ApiService
): NewsRepository {
    override suspend fun fetchNews(country: String): NewsResponseDto {
       return api.fetchNews(country)
    }
}