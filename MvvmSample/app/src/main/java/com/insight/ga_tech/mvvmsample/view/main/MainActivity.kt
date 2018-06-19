package com.insight.ga_tech.mvvmsample.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.amitshekhar.DebugDB
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.R.layout
import com.insight.ga_tech.mvvmsample.view.news.NewsActivity
import com.insight.ga_tech.mvvmsample.view.user.UserActivity

class MainActivity : AppCompatActivity(), OnClickListener {
  lateinit var btnViewUser: Button
  lateinit var btnViewNews: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    btnViewUser = findViewById(R.id.btn_view_user)
    btnViewNews = findViewById(R.id.btn_view_news)

    btnViewUser.setOnClickListener(this)
    btnViewNews.setOnClickListener(this)

    DebugDB.getAddressLog()
  }


  override fun onClick(v: View?) {
    v ?: return
    when(v.id) {
      R.id.btn_view_user -> {
        startActivity(UserActivity.getIntent(applicationContext))
      }
      R.id.btn_view_news -> {
        startActivity(NewsActivity.getIntent(applicationContext))
      }
    }
  }
}
