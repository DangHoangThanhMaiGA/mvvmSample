package com.insight.ga_tech.mvvmsample.context

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.support.multidex.MultiDex
import com.insight.ga_tech.mvvmsample.data.database.AppDatabase

class MvvmApplication: Application() {
  override fun onCreate() {
    super.onCreate()
    instance = this
  }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  companion object {
    lateinit var instance: MvvmApplication
  }
}