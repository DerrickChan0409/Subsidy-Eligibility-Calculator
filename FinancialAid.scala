package model

class FinancialAid extends Subsidy {

  override val name: String = "B40 Financial Assistance"

  override val amount: Double = 500.00

  override def isEligible(applicant: Applicant): Boolean = {
    applicant.monthlyIncome < 3000
  }

}