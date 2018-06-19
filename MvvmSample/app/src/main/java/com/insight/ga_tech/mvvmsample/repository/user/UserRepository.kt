package com.insight.ga_tech.mvvmsample.repository.user

import android.content.Context
import android.util.Log
import com.insight.ga_tech.mvvmsample.data.database.AppDatabase
import com.insight.ga_tech.mvvmsample.data.network.service.UserService
import com.insight.ga_tech.mvvmsample.model.User
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(private val userView: UserView, private val context: Context) {

  fun loadUserFromDb() {
    Log.e("UserRepository", "loadUserFromDb")
    var userDb = AppDatabase.getInstance(context)?.userDao()?.getUser()

    userDb?.let {
      var user = User()
      user.firstName = it.firstName
      user.lastName = it.lastName
      user.age = it.age
      userView.success(user)
    } ?: userView.failure()
  }

  fun getUser() {
    Log.e("UserRepository", "getUser")
    UserService
        .news()
        .fetchUser()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          if (it.isSuccessful) {
            it.body()?.let {
              var user = User()
              user.firstName = it.firstName
              user.lastName = it.lastName
              user.age = it.age


              // insert DB
              AppDatabase.getInstance(context)?.let {
                it.beginTransaction()
                it.userDao().insertUser(com.insight.ga_tech.mvvmsample.data.database.entity.User(user.firstName, user.lastName, user.age))
                it.setTransactionSuccessful()
                it.endTransaction()
                var userDb = it.userDao().getUser()
                Log.e("UserRepository", "user: $userDb")
              }

              userView.success(user)
            }
          }
        }) ?: userView.failure()
  }
}