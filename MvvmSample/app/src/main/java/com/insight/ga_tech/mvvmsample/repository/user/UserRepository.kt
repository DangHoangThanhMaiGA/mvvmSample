package com.insight.ga_tech.mvvmsample.repository.user

import com.insight.ga_tech.mvvmsample.context.MvvmApplication
import com.insight.ga_tech.mvvmsample.data.network.service.UserService
import com.insight.ga_tech.mvvmsample.model.User
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(private val userView: UserView) {

  fun loadUserFromDb() {
    var userDb = MvvmApplication().getDatabase()?.userDao()?.getUser()

    userDb?.let {
      var user = User()
      user.firstName = it.firstName
      user.lastName = it.lastName
      user.age = it.age
      userView.success(user)
    } ?: userView.failure()
  }

  fun getUser() {
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
              MvvmApplication().getDatabase()?.let {
                it.beginTransaction()
                it.userDao().insertUser(com.insight.ga_tech.mvvmsample.data.database.entity.User(user.firstName, user.lastName, user.age))
                it.setTransactionSuccessful()
                it.endTransaction()
              }

              userView.success(user)
            }
          }
        }) ?: userView.failure()
  }
}