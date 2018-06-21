package com.insight.ga_tech.mvvmsample.viewmodel.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.insight.ga_tech.mvvmsample.model.User
import com.insight.ga_tech.mvvmsample.repository.user.UserRepository

class UserViewModel : ViewModel(), UserView {
  private val user = MutableLiveData<User>()
  private val repository = UserRepository(this)

  fun setUser(user: User) {
    this.user.value = user
  }

  fun getUser(): MutableLiveData<User> {
    return user
  }

  override fun success(user: User) {
    setUser(user)
  }

  override fun failure() {
    setUser(User())
  }

  fun loadDataFromDb() {
    repository.loadUserFromDb()
  }

  fun fetchUser() {
    repository.getUser()
  }
}
