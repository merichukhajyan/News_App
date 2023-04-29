package com.example.newsapp.domain.model

import com.google.gson.annotations.SerializedName


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
data class Source(
    val id: String?,
    val name: String
)
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
)