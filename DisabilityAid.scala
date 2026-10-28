package model

class DisabilityAid extends Subsidy {

  override val name: String = "Disability Living Allowance"

  override val amount: Double = 400.00

  override def isEligible(applicant: Applicant): Boolean = {
    applicant.disabilityStatus
  }

}