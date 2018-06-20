package com.insight.ga_tech.mvvmsample.viewmodel.news.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.model.News
import com.insight.ga_tech.mvvmsample.viewmodel.news.adapter.NewsWithoutBindingAdapter.NewsViewHolder
import kotterknife.bindView

class NewsWithoutBindingAdapter(val context: Context) : RecyclerView.Adapter<NewsViewHolder>() {
  var dataSource: List<News>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
    NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news_without_data_binding, parent, false))

  override fun getItemCount(): Int = dataSource?.size ?: 0

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    dataSource?.let {
      holder.title.text = it[position].title
      holder.content.text = it[position].content
    }
  }

  inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val title by bindView<TextView>(R.id.title)
    val content by bindView<TextView>(R.id.content)
  }
}