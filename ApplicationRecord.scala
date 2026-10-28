package model

case class ApplicationRecord(

                              applicant: Applicant,

                              eligibleSubsidies: List[Subsidy],

                              totalBenefit: Double

                            )