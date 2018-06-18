package com.insight.ga_tech.mvvmsample.view.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.data.database.entity.User
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserViewModel

class UserActivity : AppCompatActivity(), OnClickListener {
  lateinit var txtName: TextView
  lateinit var txtAddress: TextView
  lateinit var txtUserCode: TextView
  lateinit var btnEdit: Button

  private lateinit var userViewModel: UserViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user)

    txtName = findViewById(R.id.txt_name)
    txtAddress = findViewById(R.id.txt_address)
    txtUserCode = findViewById(R.id.txt_code)
    btnEdit = findViewById(R.id.btn_edit)

    btnEdit.setOnClickListener(this)

    userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    userViewModel.getUser()?.observe(UserActivity@this, Observer<User> { user ->
      Log.e("UserActivity", user.toString())
      user?.let {
        txtName.text = user.name
        txtAddress.text = user.address
        txtUserCode.text = user.userCode
      }

    })
  }

  override fun onClick(v: View?) {
    v ?: return
    when(v.id) {
      R.id.btn_edit -> {
        startActivity(EditUserActivity.getIntent(applicationContext))
      }
    }
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, UserActivity::class.java)
  }
}
