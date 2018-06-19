package com.insight.ga_tech.mvvmsample.context

import android.app.Application
import android.arch.persistence.room.Room
import com.insight.ga_tech.mvvmsample.data.database.AppDatabase

class MvvmApplication: Application() {
  override fun onCreate() {
    super.onCreate()
    instance = this

    // DB
    database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DB_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
  }

  companion object {
    private const val DB_NAME = "mvvm-sample-db.db"
    var database: AppDatabase? = null
    lateinit var instance: MvvmApplication
  }
}