package com.insight.ga_tech.mvvmsample.repository.news

import android.content.Context
import com.insight.ga_tech.mvvmsample.data.network.service.NewsService
import com.insight.ga_tech.mvvmsample.model.News
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepository(private val newsView: NewsView) {
  fun fetchNews() {
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
              newsView.success(newsList)
            }?: newsView.failure()
          }
        })
  }
}