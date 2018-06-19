package com.insight.ga_tech.mvvmsample.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
  fun getRetrofit(): Retrofit = Retrofit
      .Builder()
      .baseUrl("http://www.mocky.io/v2/5b289d482f00002c00f55d5a/")
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
}