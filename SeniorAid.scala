package model

class SeniorAid extends Subsidy {

  override val name: String = "Senior Citizen Assistance"

  override val amount: Double = 350.00

  override def isEligible(applicant: Applicant): Boolean = {
    applicant.age >= 60
  }

}