package com.insight.ga_tech.mvvmsample.viewmodel.news

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import android.widget.Toast
import com.insight.ga_tech.mvvmsample.data.network.entity.NewsContent

class ItemNewsViewModel(private var newsContent: NewsContent, private var context: Context) : BaseObservable() {

  val title: String
    @Bindable get() = newsContent.title

  val content: String
    @Bindable get() = newsContent.content
//  fun getTitle() { newsContent.title }
//  fun getContent() { newsContent.content }
  fun setNews(newsContent: NewsContent) {
    this.newsContent = newsContent
    notifyChange()
  }
  fun onItemClick(v: View) {Toast.makeText(context, "You're touching ${newsContent.title}", Toast.LENGTH_SHORT).show()}
}