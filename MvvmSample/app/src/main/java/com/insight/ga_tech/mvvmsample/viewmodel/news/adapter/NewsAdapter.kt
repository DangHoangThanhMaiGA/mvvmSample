package com.insight.ga_tech.mvvmsample.viewmodel.news.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.databinding.ItemNewsBinding
import com.insight.ga_tech.mvvmsample.model.News
import com.insight.ga_tech.mvvmsample.viewmodel.news.ItemNewsViewModel
import com.insight.ga_tech.mvvmsample.viewmodel.news.adapter.NewsAdapter.NewsViewHolder

class NewsAdapter(val context: Context) : RecyclerView.Adapter<NewsViewHolder>() {
  var dataSource: List<News>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder = NewsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
      R.layout.item_news,
      parent,
      false))

  override fun getItemCount(): Int = dataSource?.size ?: 0

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    dataSource?.let {
      holder.bindNews(it[position])
    }
  }

  inner class NewsViewHolder(private val itemNewsBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemNewsBinding.root) {
    fun bindNews(news: News?) {
      news?.let {
        var itemNewsViewModel = ItemNewsViewModel(news, context)
        itemNewsBinding.itemNewsViewModel = itemNewsViewModel
      }
    }
  }
}