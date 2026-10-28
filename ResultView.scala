package view

import model.{Applicant, Subsidy}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import util.SceneManager

object ResultView {

  private var applicant: Applicant = _
  private var subsidies: List[Subsidy] = List()
  private var totalBenefit: Double = 0.0

  def setResult(
                 app: Applicant,
                 eligibleSubsidies: List[Subsidy],
                 total: Double
               ): Unit = {
    applicant = app
    subsidies = eligibleSubsidies
    totalBenefit = total
  }

  def createScene(): Scene = {

    new Scene(900, 600) {

      root = new VBox {

        spacing = 15
        padding = Insets(30)
        alignment = Pos.TopCenter

        children = Seq(

          new Label("Eligibility Result") {
            font = Font(28)
          },

          new Label(s"Applicant: ${applicant.name}") {
            font = Font(18)
          },

          new Label(s"IC Number: ${applicant.icNumber}"),

          new Label(s"Monthly Income: RM ${applicant.monthlyIncome}"),

          new Label(s"Dependents: ${applicant.dependents}"),

          new Label("Eligible Subsidies:") {
            font = Font(20)
          }

        ) ++
          (if (subsidies.isEmpty)
            Seq(
              new Label("No subsidy available.")
            )
          else
            subsidies.map { subsidy =>
              new Label(s"• ${subsidy.name} - RM ${subsidy.amount}")
            }) ++
          Seq(

            new Label(s"Total Benefit: RM $totalBenefit") {
              font = Font(22)
            },

            new Button("Back to Home") {
              onAction = _ => SceneManager.showHome()
            }

          )

      }

    }

  }

}