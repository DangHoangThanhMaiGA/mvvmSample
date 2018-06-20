package com.insight.ga_tech.mvvmsample.viewmodel.user

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import com.insight.ga_tech.mvvmsample.model.User
import java.util.Locale
import java.util.Observable
import java.util.Observer

class UserObserver(private var user: User, private val userObserverListener: UserObserverListener) : BaseObservable(), Observer {
  interface UserObserverListener {
    fun onLoadData()
  }

  private var listener: UserObserverListener
  init {
    user.addObserver(this)
    listener = userObserverListener
  }

  fun setUser(user: User) {
    this.user = user
    notifyChange()
  }

  val name: String
    @Bindable get() {
      return user.firstName + " " + user.lastName
    }

  val age: String
    @Bindable get() {
      return if (user.age <= 0) return ""
      else String.format(Locale.ENGLISH, "%d years old", user.age)
    }

  override fun update(o: Observable?, arg: Any?) {
  }

  fun onButtonClick(view: View) {
    listener.onLoadData()
  }
}
