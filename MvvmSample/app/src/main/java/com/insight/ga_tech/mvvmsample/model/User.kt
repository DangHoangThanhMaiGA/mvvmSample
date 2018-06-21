package com.insight.ga_tech.mvvmsample.model

import java.util.Observable

class User: Observable() {
  var firstName: String = ""
    set(value) {
      field = value
      setChangedAndNotify("firstName")
    }

  var lastName: String = ""
    set(value) {
      field = value
      setChangedAndNotify("lastName")
    }
  /// The age of the user
  var age: Int = 0
    set(value) {
      field = value
      setChangedAndNotify("age")
    }

  private fun setChangedAndNotify(field: Any)
  {
    setChanged()
    notifyObservers(field)
  }
}