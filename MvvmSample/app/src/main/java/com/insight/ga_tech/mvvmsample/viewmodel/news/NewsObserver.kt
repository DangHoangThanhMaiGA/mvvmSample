package com.insight.ga_tech.mvvmsample.viewmodel.news

import android.databinding.BaseObservable
import android.view.View
import java.util.Observable
import java.util.Observer

class NewsObserver(private val newsObserverListener: NewsObserverListener) : BaseObservable(), Observer {
  interface NewsObserverListener {
    fun fetchNews()
  }

  fun onClickLoadData(view: View) {
    newsObserverListener.fetchNews()
  }

  override fun update(o: Observable?, arg: Any?) {
  }
}