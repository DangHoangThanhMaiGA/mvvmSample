package com.insight.ga_tech.mvvmsample.view.news

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.databinding.ActivityNewsBinding
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsAdapter
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsObserver
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsObserver.NewsObserverListener
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsViewModel
import java.util.Observable
import java.util.Observer

class NewsActivity : AppCompatActivity(), NewsObserverListener {
  private lateinit var newsBinding: ActivityNewsBinding
  private lateinit var newsViewModel: NewsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // init databinding
    newsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)
    newsBinding.news = NewsObserver(this)

    // setup list
    var newsAdapter = NewsAdapter(applicationContext)
//    newsBinding.newsList.adapter = newsAdapter
//    newsBinding.newsList.layoutManager = LinearLayoutManager(this)
  }

  override fun fetchNews() {

  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, NewsActivity::class.java)
  }
}
