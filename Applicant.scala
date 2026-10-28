package model

class Applicant(
                 name: String,
                 icNumber: String,
                 age: Int,
                 val monthlyIncome: Double,
                 val dependents: Int,
                 val employmentStatus: String,
                 val disabilityStatus: Boolean
               ) extends Person(name, icNumber, age) {

  override def displayInfo(): String = {
    s"""
       |Applicant Information
       |---------------------
       |Name: $name
       |IC Number: $icNumber
       |Age: $age
       |Monthly Income: RM$monthlyIncome
       |Dependents: $dependents
       |Employment Status: $employmentStatus
       |Disability Status: $disabilityStatus
       |""".stripMargin
  }

}