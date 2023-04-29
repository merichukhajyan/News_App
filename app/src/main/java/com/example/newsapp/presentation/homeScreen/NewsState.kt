package com.example.newsapp.presentation.homeScreen

import com.example.newsapp.domain.model.NewsResponse

data class NewsState(
    var isLoading: Boolean = false,
    var error: String = "",
    var news: NewsResponse? = null,
    var searchValue: String = ""
)
