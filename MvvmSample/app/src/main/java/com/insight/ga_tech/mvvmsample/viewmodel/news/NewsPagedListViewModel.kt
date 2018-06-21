package com.insight.ga_tech.mvvmsample.viewmodel.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.insight.ga_tech.mvvmsample.data.datasource.NewsDataSourceFactory
import com.insight.ga_tech.mvvmsample.model.News
import io.reactivex.disposables.CompositeDisposable

class NewsPagedListViewModel: ViewModel() {
  var newsList: LiveData<PagedList<News>>
  private val compositeDisposable = CompositeDisposable()
  private val pageSize = 10
  private val sourceFactory: NewsDataSourceFactory

  init {
    sourceFactory = NewsDataSourceFactory(compositeDisposable)
    val config = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(pageSize)
        .setEnablePlaceholders(false)
        .build()
    newsList = LivePagedListBuilder<Int, News>(sourceFactory, config).build()
  }

  fun refresh() {
    sourceFactory.newsDataSourceLiveData.value!!.invalidate()
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }
}