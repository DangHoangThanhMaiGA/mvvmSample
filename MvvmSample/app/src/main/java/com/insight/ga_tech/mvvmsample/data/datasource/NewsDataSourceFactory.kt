package com.insight.ga_tech.mvvmsample.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.insight.ga_tech.mvvmsample.model.News
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(private val compositeDisposable: CompositeDisposable)
                            : DataSource.Factory<Int, News>() {
  val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()
  override fun create(): DataSource<Int, News> {
    val newsDataSource = NewsDataSource(compositeDisposable)
    newsDataSourceLiveData.postValue(newsDataSource)
    return newsDataSource
  }

}