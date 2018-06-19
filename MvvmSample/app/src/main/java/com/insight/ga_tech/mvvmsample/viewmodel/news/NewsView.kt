package com.insight.ga_tech.mvvmsample.viewmodel.news

import com.insight.ga_tech.mvvmsample.data.network.entity.News

interface NewsView {
  fun success(news: News)
  fun failure()
}