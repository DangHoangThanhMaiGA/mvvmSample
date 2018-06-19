package com.insight.ga_tech.mvvmsample.viewmodel.news

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.insight.ga_tech.mvvmsample.R
import com.insight.ga_tech.mvvmsample.data.network.entity.NewsContent
import com.insight.ga_tech.mvvmsample.databinding.ItemNewsBinding
import com.insight.ga_tech.mvvmsample.viewmodel.news.NewsAdapter.NewsViewHolder

class NewsAdapter(val context: Context) : RecyclerView.Adapter<NewsViewHolder>() {
  var datasource = ArrayList<NewsContent>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder = NewsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_news, parent, false))

  override fun getItemCount(): Int = datasource.size

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    holder.bindNews(datasource[position])
  }

  fun setNewsList(newsList: List<NewsContent>) {
    datasource = newsList as ArrayList<NewsContent>
    notifyDataSetChanged()
  }

  inner class NewsViewHolder(private val itemNewsBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemNewsBinding.itemList) {
    fun bindNews(newsContent: NewsContent) {
      itemNewsBinding.itemNewsViewModel?.setNews(newsContent)
    }
  }
}