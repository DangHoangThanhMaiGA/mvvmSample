package com.insight.ga_tech.mvvmsample.repository.user

import android.arch.lifecycle.LiveData
import com.insight.ga_tech.mvvmsample.context.MvvmApplication
import com.insight.ga_tech.mvvmsample.data.database.entity.User

class UserRepository {

  fun getUser(): LiveData<User>? {
    return MvvmApplication.database?.userDao()?.getUser()
  }

  fun update(user: User) {
    MvvmApplication.database?.userDao()?.updateUser(user)
  }

  fun insert(user: User): Long? {
    return MvvmApplication.database?.userDao()?.insertUser(user)
  }
}