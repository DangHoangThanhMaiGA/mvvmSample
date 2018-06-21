package com.insight.ga_tech.mvvmsample.viewmodel.news

import com.insight.ga_tech.mvvmsample.model.News

interface NewsView {
  fun success(newsList: List<News>)
  fun failure()
}