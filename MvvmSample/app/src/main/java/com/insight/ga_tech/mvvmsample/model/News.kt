package com.insight.ga_tech.mvvmsample.model

import java.util.Observable

class News: Observable() {
  var title: String = ""
  set(value) {
    field = value
    setChangedAndNotify("title")
  }

  var content: String = ""
  set(value) {
    field = value
    setChangedAndNotify("content")
  }

  private fun setChangedAndNotify(field: Any) {
    setChanged()
    notifyObservers(field)
  }
}