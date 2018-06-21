package com.insight.ga_tech.mvvmsample.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import com.insight.ga_tech.mvvmsample.data.network.service.NewsService
import com.insight.ga_tech.mvvmsample.model.NetworkState
import com.insight.ga_tech.mvvmsample.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsDataSource(private val compositeDisposable: CompositeDisposable) : ItemKeyedDataSource<Int, News>() {
  val networkState = MutableLiveData<NetworkState>()
  val initialLoad = MutableLiveData<NetworkState>()

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<News>) {
    networkState.postValue(NetworkState.LOADING)
    initialLoad.postValue(NetworkState.LOADING)
    Log.e("NewsDataSource", "loadInitial")
    compositeDisposable.add(
        NewsService
            .news()
            .fetchNews()
            .subscribe({
              if (it.isSuccessful) {
                it.body()?.let {
                  var newsList = ArrayList<News>()
                  for (news in it.news) {
                    var newsModel = News()
                    newsModel.title = news.title
                    newsModel.content = news.content
                    newsList.add(newsModel)
                  }
                  Log.e("NewsDataSource", "loadInitial ${newsList.size}")
                  networkState.postValue(NetworkState.LOADED)
                  initialLoad.postValue(NetworkState.LOADED)
                  callback.onResult(newsList)
                }
              }
            }, {
              val error = NetworkState.error(it.message)
              networkState.postValue(error)
              initialLoad.postValue(error)
            })
    )
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<News>) {
    networkState.postValue(NetworkState.LOADING)
    compositeDisposable.add(
        NewsService
            .news()
            .fetchNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
              if (it.isSuccessful) {
                it.body()?.let {
                  var newsList = ArrayList<News>()
                  for (news in it.news) {
                    var newsModel = News()
                    newsModel.title = news.title
                    newsModel.content = news.content
                    newsList.add(newsModel)
                  }

                  networkState.postValue(NetworkState.LOADED)
                  callback.onResult(newsList)
                }
              }
            }, {
              networkState.postValue(NetworkState.error(it.message))
            })
    )
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<News>) {
  }

  override fun getKey(item: News): Int {
    return item.id
  }
}