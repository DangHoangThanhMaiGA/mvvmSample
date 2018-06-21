package com.insight.ga_tech.mvvmsample.viewmodel.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.insight.ga_tech.mvvmsample.model.News
import com.insight.ga_tech.mvvmsample.repository.news.NewsRepository

class NewsViewModel: ViewModel(), NewsView {

  private var newsList = MutableLiveData<List<News>>()
  private var repository = NewsRepository(this)

  fun setNewsList(newsList: List<News>) {
    this.newsList.value = newsList
  }

  fun getNewsList(): MutableLiveData<List<News>> {
    return this.newsList
  }

  override fun success(newsList: List<News>) {
    setNewsList(newsList)
  }

  override fun failure() {
    setNewsList(ArrayList<News>())
  }

  fun fetchNews() {
    repository.fetchNews()
  }
}