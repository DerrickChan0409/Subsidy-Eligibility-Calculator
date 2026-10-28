package view

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.*
import scalafx.scene.layout.{GridPane, HBox, VBox}
import scalafx.scene.text.Font
import util.SceneManager
import model.Applicant
import service.EligibilityCalculator
import model.ApplicationRecord
import storage.ApplicantRepository

object ApplicantFormView {

  val nameField = new TextField()
  val icField = new TextField()
  val ageField = new TextField()
  val incomeField = new TextField()
  val dependentField = new TextField()

  val employmentCombo = new ComboBox[String](
    Seq(
      "Employed",
      "Unemployed",
      "Self-Employed",
      "Student",
      "Retired"
    )
  )

  val disabilityCheck = new CheckBox("Applicant has a disability")

  def createScene(): Scene = {

    new Scene(900, 600) {

      root = new VBox {

        spacing = 20
        padding = Insets(30)
        alignment = Pos.TopCenter

        children = Seq(

          new Label("Applicant Registration") {
            font = Font(28)
          },

          new GridPane {

            hgap = 15
            vgap = 15

            add(new Label("Full Name:"), 0, 0)
            add(nameField, 1, 0)

            add(new Label("IC Number:"), 0, 1)
            add(icField, 1, 1)

            add(new Label("Age:"), 0, 2)
            add(ageField, 1, 2)

            add(new Label("Monthly Income (RM):"), 0, 3)
            add(incomeField, 1, 3)

            add(new Label("Number of Dependents:"), 0, 4)
            add(dependentField, 1, 4)

            add(new Label("Employment Status:"), 0, 5)
            add(employmentCombo, 1, 5)

            add(new Label("Disability Status:"), 0, 6)
            add(disabilityCheck, 1, 6)

          },

          new HBox {

            spacing = 20
            alignment = Pos.Center

            children = Seq(

              new Button("Calculate Eligibility") {

                onAction = _ => {

                  // Check for empty fields
                  if (
                    nameField.text.value.trim.isEmpty ||
                      icField.text.value.trim.isEmpty ||
                      ageField.text.value.trim.isEmpty ||
                      incomeField.text.value.trim.isEmpty ||
                      dependentField.text.value.trim.isEmpty ||
                      employmentCombo.value.value == null
                  ) {

                    new Alert(Alert.AlertType.Warning) {

                      title = "Incomplete Form"

                      headerText = "Missing Information"

                      contentText =
                        "Please fill in all fields and select an employment status."

                    }.showAndWait()

                  } else {

                    try {

                      // Create Applicant object
                      val applicant = Applicant(

                        name = nameField.text.value.trim,

                        icNumber = icField.text.value.trim,

                        age = ageField.text.value.toInt,

                        monthlyIncome = incomeField.text.value.toDouble,

                        dependents = dependentField.text.value.toInt,

                        employmentStatus = employmentCombo.value.value,

                        disabilityStatus = disabilityCheck.selected.value

                      )

                      // Additional validation
                      if (applicant.age <= 0)
                        throw new IllegalArgumentException("Age must be greater than 0.")

                      if (applicant.monthlyIncome < 0)
                        throw new IllegalArgumentException("Monthly income cannot be negative.")

                      if (applicant.dependents < 0)
                        throw new IllegalArgumentException("Number of dependents cannot be negative.")

                      // Create calculator
                      val calculator = new EligibilityCalculator()

                      // Get eligible subsidies
                      val eligibleSubsidies =
                        calculator.getEligibleSubsidies(applicant)

                      // Calculate total benefit
                      val totalBenefit =
                        calculator.calculateTotalBenefit(applicant)

                      val record = ApplicationRecord(
                        applicant,
                        eligibleSubsidies,
                        totalBenefit
                      )

                      ApplicantRepository.addRecord(record)

                      // Store result
                      ResultView.setResult(
                        applicant,
                        eligibleSubsidies,
                        totalBenefit
                      )

                      // Navigate to Result screen
                      SceneManager.showResult()

                    }

                    catch {

                      case _: NumberFormatException =>

                        new Alert(Alert.AlertType.Error) {

                          title = "Invalid Input"

                          headerText = "Invalid Number"

                          contentText =
                            "Age, Monthly Income and Number of Dependents must be numeric."

                        }.showAndWait()

                      case e: IllegalArgumentException =>

                        new Alert(Alert.AlertType.Warning) {

                          title = "Validation Error"

                          headerText = "Invalid Input"

                          contentText = e.getMessage

                        }.showAndWait()

                    }

                  }

                }

              },

              new Button("Back") {

                onAction = _ =>
                  SceneManager.showHome()

              }

            )

          }

        )

      }

    }

  }

}