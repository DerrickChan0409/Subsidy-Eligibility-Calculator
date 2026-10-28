package model

 trait Subsidy {

  def name: String

  def amount: Double

  def isEligible(applicant: Applicant): Boolean

}