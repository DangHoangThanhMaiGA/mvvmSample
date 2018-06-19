package com.insight.ga_tech.mvvmsample.viewmodel.news

import android.content.Context
import android.databinding.BaseObservable
import android.util.Log
import android.view.View
import com.insight.ga_tech.mvvmsample.data.network.service.NewsService
import com.insight.ga_tech.mvvmsample.model.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Observable
import java.util.Observer

class NewsViewModel(private val newsAdapter: NewsAdapter) : BaseObservable(), Observer {

  init {
//    newsAdapter.addObserver(this)
    fetchNews()
  }

  fun fetchNews() {
    NewsService
        .news()
        .fetchNews()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          if (it.isSuccessful) {
            it.body()?.let {
              newsAdapter.setNewsList(it.news)
              newsAdapter.notifyDataSetChanged()
            }
          }
        })
  }

  fun onClickLoadData(view: View) {
    fetchNews()
  }

  override fun update(o: Observable?, arg: Any?) {
    Log.e("NewsActivity", "update Observer")
//    if (arg is ) {
//      var newsAdapter: NewsAdapter = newsBinding.newsList.adapter as NewsAdapter
//      newsAdapter.setNewsList(newsViewModel.getNews().news)
//    }
  }

//  fun getNews(): News = news
}