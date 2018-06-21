package com.insight.ga_tech.mvvmsample.view.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.databinding.ActivityUserBinding
import com.insight.ga_tech.mvvmsample.model.User
import com.insight.ga_tech.mvvmsample.repository.user.UserRepository
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserObserver
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserObserver.UserObserverListener
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserView
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserViewModel

class UserDataBindingActivity : AppCompatActivity(), UserObserverListener {

  private lateinit var userBinding: ActivityUserBinding
  private lateinit var userObserver: UserObserver
  private lateinit var userViewModel: UserViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user)

    userObserver = UserObserver(User(), this)
    userBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
    userBinding.user = userObserver

    // load data
    userViewModel = UserViewModel()
    userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    userViewModel.getUser().observe(this, Observer {  user ->
      user?.let {
        userObserver.setUser(user)
      }
    })
    userViewModel.loadDataFromDb()
  }

  //[UserViewModelListener]
  override fun onLoadData() {
    userViewModel.fetchUser()
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, UserDataBindingActivity::class.java)
  }
}
