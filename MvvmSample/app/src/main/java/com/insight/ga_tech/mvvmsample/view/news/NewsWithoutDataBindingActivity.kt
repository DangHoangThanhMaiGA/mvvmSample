package com.insight.ga_tech.mvvmsample.view.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.model.NetworkState
import com.insight.ga_tech.mvvmsample.model.News
import com.insight.ga_tech.mvvmsample.repository.news.NewsRepository
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsPagedListViewModel
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsView
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsViewModel
import com.insight.ga_tech.mvvmsample.viewmodel.news.adapter.NewsWithoutBindingAdapter

class NewsWithoutDataBindingActivity : AppCompatActivity(), OnClickListener {
  private lateinit var newsViewModel: NewsPagedListViewModel

  // layout
  private lateinit var recyclerView: RecyclerView
  private lateinit var btnLoad: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_news_without_data_binding)
    btnLoad = findViewById(R.id.btn_load)
    recyclerView = findViewById(R.id.news_list)

    newsViewModel = ViewModelProviders.of(this).get(NewsPagedListViewModel::class.java)

    // setup list
    var newsAdapter = NewsWithoutBindingAdapter()
    newsViewModel.newsList.observe(this, Observer<PagedList<News>> {
      val adapter = recyclerView.adapter
      if (adapter is NewsWithoutBindingAdapter) {
        adapter.apply {
          this.dataSource = null
          this.dataSource = it
          notifyDataSetChanged()
        }
      }
    })
    recyclerView.apply {
      this.adapter = newsAdapter
      this.layoutManager = LinearLayoutManager(applicationContext)
    }

    btnLoad.setOnClickListener(this)
  }

  // [OnClickListener]
  override fun onClick(v: View?) {
    v ?: return
    when(v.id) {
      R.id.btn_load -> {
        newsViewModel.refresh()
      }
    }
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, NewsWithoutDataBindingActivity::class.java)
  }
}
