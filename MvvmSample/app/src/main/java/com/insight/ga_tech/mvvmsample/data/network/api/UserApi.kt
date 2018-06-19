package com.insight.ga_tech.mvvmsample.data.network.api

import com.insight.ga_tech.mvvmsample.data.network.entity.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

  @GET(PATH)
  fun fetchUser(): Observable<Response<User>>

  companion object {
    private const val PATH = "http://www.mocky.io/v2/5b28c1a42f00006300f55e32"
  }
}