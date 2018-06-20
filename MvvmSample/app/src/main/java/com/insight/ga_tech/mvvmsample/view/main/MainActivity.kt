package com.insight.ga_tech.mvvmsample.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.R.layout
import com.insight.ga_tech.mvvmsample.view.news.NewsDataBindingActivity
import com.insight.ga_tech.mvvmsample.view.news.NewsWithoutDataBindingActivity
import com.insight.ga_tech.mvvmsample.view.user.UserDataBindingActivity
import com.insight.ga_tech.mvvmsample.view.user.UserWithoutDataBindingActivity

class MainActivity : AppCompatActivity(), OnClickListener {
  private lateinit var btnLoadDataWithBinding: Button
  private lateinit var btnLoadDataWithoutBinding: Button
  private lateinit var btnLoadListWithBinding: Button
  private lateinit var btnLoadListWithoutBinding: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    btnLoadDataWithBinding = findViewById(R.id.btn_load_data_databinding)
    btnLoadDataWithoutBinding = findViewById(R.id.btn_load_data_no_databinding)
    btnLoadListWithBinding = findViewById(R.id.btn_load_list_with_binding)
    btnLoadListWithoutBinding = findViewById(R.id.btn_load_list_without_binding)

    btnLoadDataWithBinding.setOnClickListener(this)
    btnLoadDataWithoutBinding.setOnClickListener(this)
    btnLoadListWithBinding.setOnClickListener(this)
    btnLoadListWithoutBinding.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    v ?: return
    when(v.id) {
      R.id.btn_load_data_databinding -> {
        startActivity(UserDataBindingActivity.getIntent(applicationContext))
      }
      R.id.btn_load_data_no_databinding -> {
        startActivity(UserWithoutDataBindingActivity.getIntent(applicationContext))
      }
      R.id.btn_load_list_with_binding -> {
        startActivity(NewsDataBindingActivity.getIntent(applicationContext))
      }
      R.id.btn_load_list_without_binding -> {
        startActivity(NewsWithoutDataBindingActivity.getIntent(applicationContext))
      }
    }
  }
}
