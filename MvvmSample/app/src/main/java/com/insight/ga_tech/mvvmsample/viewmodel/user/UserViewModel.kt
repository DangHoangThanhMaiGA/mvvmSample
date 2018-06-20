package com.insight.ga_tech.mvvmsample.viewmodel.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.insight.ga_tech.mvvmsample.model.User

class UserViewModel : ViewModel() {

  private val user = MutableLiveData<User>()

  fun setUser(user: User) {
    this.user.value = user
  }

  fun getUser(): MutableLiveData<User> {
    return user
  }
}
