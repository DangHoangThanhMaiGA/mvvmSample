package com.insight.ga_tech.mvvmsample.view.user

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.data.database.entity.User
import com.insight.ga_tech.mvvmsample.viewmodel.user.UserViewModel

class EditUserActivity : AppCompatActivity(), OnClickListener {

  lateinit var txtName: EditText
  lateinit var txtAddress: EditText
  lateinit var txtUserCode: EditText
  lateinit var btnSave: Button

  private lateinit var userViewModel: UserViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_user)

    txtName = findViewById(R.id.txt_name)
    txtAddress = findViewById(R.id.txt_address)
    txtUserCode = findViewById(R.id.txt_user_code)
    btnSave = findViewById(R.id.btn_save)

    userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    btnSave.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    v ?: return
    when(v.id) {
      R.id.btn_save -> {
        var user = userViewModel.getUser()
        var editUser = User(0, txtName.text.toString(), txtAddress.text.toString(), txtUserCode.text.toString())
        if (user?.value != null) {
          userViewModel.updateUser(editUser)
        } else {
          userViewModel.insertUser(editUser)
        }
        finish()
      }
    }
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, EditUserActivity::class.java)
  }
}
