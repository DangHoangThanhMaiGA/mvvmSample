package com.insight.ga_tech.mvvmsample.viewmodel.user

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import android.view.View
import com.insight.ga_tech.mvvmsample.model.User
import com.insight.ga_tech.mvvmsample.repository.user.UserRepository
import java.util.Locale
import java.util.Observable
import java.util.Observer

class UserViewModel(private var user: User, private val context: Context) : BaseObservable(), UserView, Observer {

  private var repository = UserRepository(this, context)

  init {
    Log.e("UserViewModel", "init")
    user.addObserver(this)
    repository.loadUserFromDb()
  }

  override fun success(userAPI: User) {
    Log.e("UserViewModel", "success")
    user = userAPI
    notifyChange()
  }

  override fun failure() {
    user = User()
  }

  val name: String
    @Bindable get() {
      Log.e("UserViewModel", "getName")
      return user.firstName + " " + user.lastName
    }

  val age: String
    @Bindable get() {
      Log.e("UserViewModel", "getAge")
      return if (user.age <= 0) return ""
      else String.format(Locale.ENGLISH, "%d years old", user.age)
    }

  override fun update(o: Observable?, arg: Any?) {
  }

  fun onButtonClick(view: View) {
    repository.getUser()
  }
}
