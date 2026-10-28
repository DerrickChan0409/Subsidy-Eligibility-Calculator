package service

import model._

class EligibilityCalculator {

  // List of all available subsidies
  private val subsidies: List[Subsidy] = List(
    new FinancialAid(),
    new EducationAid(),
    new DisabilityAid(),
    new SeniorAid()
  )

  // Returns all subsidies the applicant qualifies for
  def getEligibleSubsidies(applicant: Applicant): List[Subsidy] = {
    subsidies.filter(_.isEligible(applicant))
  }

  // Calculates the total monetary benefit
  def calculateTotalBenefit(applicant: Applicant): Double = {
    getEligibleSubsidies(applicant)
      .map(_.amount)
      .sum
  }

}