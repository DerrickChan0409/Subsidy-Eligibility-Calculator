package model

abstract class Person(
                       val name: String,
                       val icNumber: String,
                       val age: Int
                     ) {

  def displayInfo(): String

}