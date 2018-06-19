package com.insight.ga_tech.mvvmsample.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.insight.ga_tech.mvvmsample.data.database.dao.UserDao
import com.insight.ga_tech.mvvmsample.data.database.entity.User

@Database(entities = arrayOf(
    User::class
), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun userDao(): UserDao

  companion object {
    private var INSTANCE: AppDatabase? = null
    private const val DB_NAME = "mvvm-sample-db.db"

    fun getInstance(context: Context): AppDatabase? {
      if (INSTANCE == null) {
        synchronized(AppDatabase::class) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              AppDatabase::class.java, DB_NAME)
              .allowMainThreadQueries()
              .build()
        }
      }
      return INSTANCE
    }
  }
}