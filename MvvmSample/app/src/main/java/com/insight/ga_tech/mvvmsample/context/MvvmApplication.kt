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

//    // DB
//    MvvmApplication.database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DB_NAME)
//        .allowMainThreadQueries()
//        .fallbackToDestructiveMigration()
//        .build()
  }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

//  fun getDatabase(): AppDatabase? = database

  companion object {
//    var database: AppDatabase? = null
//    private const val DB_NAME = "mvvm-sample-db.db"
    lateinit var instance: MvvmApplication
      private set
  }
}