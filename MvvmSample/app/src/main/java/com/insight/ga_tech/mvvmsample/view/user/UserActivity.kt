package com.insight.ga_tech.mvvmsample.view.user

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.databinding.ActivityUserBinding
import com.insight.ga_tech.mvvmsample.model.User
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserViewModel

class UserActivity : AppCompatActivity() {
private lateinit var userBinding: ActivityUserBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user)

    var user = User()
    user.firstName = "aaa"
    user.lastName = "bbb"
    user.age = 63

    val userViewModel = UserViewModel(user)
    userBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
    userBinding.user = userViewModel
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, UserActivity::class.java)
  }
}
