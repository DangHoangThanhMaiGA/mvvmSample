package com.insight.ga_tech.mvvmsample.viewmodel.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.BaseObservable
import android.util.Log
import android.view.View
import com.insight.ga_tech.mvvmsample.data.network.service.NewsService
import com.insight.ga_tech.mvvmsample.model.News
import com.insight.ga_tech.mvvmsample.repository.news.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Observable
import java.util.Observer

class NewsViewModel: ViewModel(), NewsView {
  private var newsList = MutableLiveData<List<News>>()
  private var repository = NewsRepository(this)

  fun setNewsList(newsList: List<News>) {
    this.newsList.value = newsList
  }

  fun getNewsList(): MutableLiveData<List<News>> {
    return this.newsList
  }

  override fun success(newsList: ArrayList<News>) {
    setNewsList(newsList)
  }

  override fun failure() {
    setNewsList(ArrayList<News>())
  }

  fun fetchNews() {
    repository.fetchNews()
  }
}