package com.insight.ga_tech.mvvmsample.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.insight.ga_tech.mvvmsample.data.database.dao.UserDao
import com.insight.ga_tech.mvvmsample.data.database.entity.User

@Database(entities = arrayOf(
    User::class
), version = 8, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun userDao(): UserDao
}