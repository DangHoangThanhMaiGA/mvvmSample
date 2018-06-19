package com.insight.ga_tech.mvvmsample.data.network.service

import com.insight.ga_tech.mvvmsample.data.network.ApiClient
import com.insight.ga_tech.mvvmsample.data.network.api.UserApi

object UserService {
  private val retrofit = ApiClient.getRetrofit()
  fun news(): UserApi = retrofit.create(UserApi::class.java)
}