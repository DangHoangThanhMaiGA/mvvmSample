package com.insight.ga_tech.mvvmsample.data.network.entity

import com.squareup.moshi.Json

data class News(
  @Json(name = "news") val news: List<NewsContent>
)

data class NewsContent(
    @Json(name = "title") val title: String,
    @Json(name = "content") val content: String
)