package com.insight.ga_tech.mvvmsample.model

import java.util.Observable

class User: Observable() {
  /// The first name of the user
  var firstName: String = ""
    set(value) {
      field = value
      setChangedAndNotify("firstName")
    }

  /// The last name of the user
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