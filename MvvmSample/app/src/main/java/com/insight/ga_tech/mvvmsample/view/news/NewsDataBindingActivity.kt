package com.insight.ga_tech.mvvmsample.view.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.databinding.ActivityNewsBinding
import com.insight.ga_tech.mvvmsample.model.News
import com.insight.ga_tech.mvvmsample.repository.news.NewsRepository
import com.insight.ga_tech.mvvmsample.viewmodel.news.adapter.NewsAdapter
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsObserver
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsObserver.NewsObserverListener
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsView
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsViewModel

class NewsDataBindingActivity : AppCompatActivity(), NewsObserverListener, NewsView {
  private lateinit var newsBinding: ActivityNewsBinding
  private lateinit var newsViewModel: NewsViewModel
  private lateinit var newsRepository: NewsRepository
  private lateinit var newsObserver: NewsObserver

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    newsRepository = NewsRepository(this, applicationContext)

    // setup list
    var newsAdapter = NewsAdapter(applicationContext)

    // init databinding
    newsObserver = NewsObserver(this)
    newsBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)
    newsBinding.news = newsObserver

    newsBinding.newsList.apply {
      adapter = newsAdapter
      layoutManager = LinearLayoutManager(applicationContext)
    }

    // load data
    newsViewModel = NewsViewModel()
    newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    newsViewModel.getNewsList().observe(this, Observer { newsList ->
      newsList?.let {
        val adapter = newsBinding.newsList.adapter
        if (adapter is NewsAdapter) {
          adapter.apply {
            this.dataSource = null
            this.dataSource = newsList
            notifyDataSetChanged()
          }
        }
      }
    })
    newsRepository.fetchNews()
  }

  override fun fetchNews() {
    newsRepository.fetchNews()
  }

  override fun success(newsList: ArrayList<News>) {
    newsViewModel.setNewsList(newsList)
  }

  override fun failure() {
    newsViewModel.setNewsList(ArrayList<News>())
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, NewsDataBindingActivity::class.java)
  }
}
