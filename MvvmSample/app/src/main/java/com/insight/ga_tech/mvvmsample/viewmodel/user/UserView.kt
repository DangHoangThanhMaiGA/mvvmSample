package com.insight.ga_tech.mvvmsample.viewmodel.user

import com.insight.ga_tech.mvvmsample.model.User

interface UserView {
  fun success(user: User)
  fun failure()
}