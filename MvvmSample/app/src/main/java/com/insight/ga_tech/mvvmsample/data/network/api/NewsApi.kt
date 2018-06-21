package com.insight.ga_tech.mvvmsample.data.network.api

import com.insight.ga_tech.mvvmsample.data.network.entity.News
import retrofit2.Response
import retrofit2.http.GET
import io.reactivex.Observable

interface NewsApi {

  @GET(PATH)
  fun fetchNews(): Observable<Response<News>>

  companion object {
    private const val PATH = "http://www.mocky.io/v2/5b2b038a3000004c009cd528"
  }
}