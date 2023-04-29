package com.example.newsapp.data.dto


import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.NewsResponse
import com.example.newsapp.domain.model.Source
import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

data class SourceDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
)

data class ArticleDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: SourceDto,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
)

fun ArticleDto.toArticle(): Article {
    return Article(
        author,
        content,
        description,
        publishedAt,
        source.toSource(),
        title,
        url,
        urlToImage
    )
}

fun SourceDto.toSource(): Source {
    return Source(
        id, name
    )
}

fun NewsResponseDto.toNewsResponse(): NewsResponse {
    return NewsResponse(
        articles = articles.map { article ->
            article.toArticle()
        },
        status = status,
        totalResults = totalResults
    )
}