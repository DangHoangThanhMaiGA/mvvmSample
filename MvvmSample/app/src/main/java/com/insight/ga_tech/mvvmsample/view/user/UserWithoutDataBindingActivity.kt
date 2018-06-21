package com.insight.ga_tech.mvvmsample.view.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.model.User
import com.insight.ga_tech.mvvmsample.repository.user.UserRepository
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserView
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserViewModel
import java.util.Locale

class UserWithoutDataBindingActivity : AppCompatActivity(), OnClickListener {
  private lateinit var userViewModel: UserViewModel

  private lateinit var txtName: TextView
  private lateinit var txtAge: TextView
  private lateinit var btnLoad: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_without_data_binding)

    txtName = findViewById(R.id.txt_name)
    txtAge = findViewById(R.id.txt_age)
    btnLoad = findViewById(R.id.btn_load)

    // load data
    userViewModel = UserViewModel()
    userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    userViewModel.getUser().observe(this, Observer {  user ->
      user?.let {
        txtName.setText("${user.firstName} ${user.lastName}")
        txtAge.setText(String.format(Locale.ENGLISH, "%d years old", user.age))
      }
    })
    userViewModel.loadDataFromDb()
  }

  override fun onClick(v: View?) {
    v ?: return
    when(v.id) {
      R.id.btn_load -> {
        userViewModel.fetchUser()
      }
    }
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, UserWithoutDataBindingActivity::class.java)
  }
}
