package com.insight.ga_tech.mvvmsample.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.insight.ga_tech.mvvmsample.data.database.entity.User

@Dao
interface UserDao {

  @Query("SELECT * FROM user LIMIT 1")
  fun getUser(): User

  @Update
  fun updateUser(user: User)

  @Insert
  fun insertUser(user: User): Long
}