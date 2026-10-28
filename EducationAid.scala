package model

class EducationAid extends Subsidy {

  override val name: String = "Education Support Grant"

  override val amount: Double = 300.00

  override def isEligible(applicant: Applicant): Boolean = {
    applicant.dependents >= 3
  }

}