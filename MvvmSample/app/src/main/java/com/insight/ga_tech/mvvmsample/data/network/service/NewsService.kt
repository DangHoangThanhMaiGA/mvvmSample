package com.insight.ga_tech.mvvmsample.data.network.service

import com.insight.ga_tech.mvvmsample.data.network.ApiClient
import com.insight.ga_tech.mvvmsample.data.network.api.NewsApi

object NewsService {
  private val retrofit = ApiClient.getRetrofit()
  fun news(): NewsApi = retrofit.create(NewsApi::class.java)
}