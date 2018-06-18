package com.insight.ga_tech.mvvmsample.viewmodel.user

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import android.util.Log
import com.insight.ga_tech.mvvmsample.context.MvvmApplication
import com.insight.ga_tech.mvvmsample.data.database.entity.User
import com.insight.ga_tech.mvvmsample.repository.user.UserRepository
import java.util.concurrent.Executor

class UserViewModel : ViewModel() {
  private var user: LiveData<User> = MutableLiveData<User>()
  private var repository: UserRepository = UserRepository()


  fun getUser(): LiveData<User>? {
    return repository.getUser()
  }

  fun updateUser(user: User) {
    repository.update(user)
  }

  fun insertUser(user: User) {
//    var id = MvvmApplication.database?.userDao()?.insertUser(user)
//    Log.e("UserViewModel", "insert $id")
//    id?.let {
//      if (it >= 0) {
//        Log.e("UserViewModel", "insert success")
//      } else {
//        Log.e("UserViewModel", "insert failed")
//      }
//    }
    doAsync().execute(user)
  }

}

class doAsync() : AsyncTask<User, Void, Long>() {
  override fun doInBackground(vararg params: User): Long?{
    MvvmApplication.database?.userDao()?.insertUser(params[0])
//    Log.e("doAsync", "id: $id")
    return null
  }
}