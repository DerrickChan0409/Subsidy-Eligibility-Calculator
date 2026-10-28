package view

import model.ApplicationRecord
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.control.cell.TextFieldTableCell
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import storage.ApplicantRepository
import util.SceneManager

object RecordsView {

  def createScene(): Scene = {

    val records = ObservableBuffer[ApplicationRecord]()
    records ++= ApplicantRepository.getAllRecords

    val table = new TableView[ApplicationRecord](records) {

      columns ++= List(

        new TableColumn[ApplicationRecord, String]("Name") {
          cellValueFactory = cellData =>
            StringProperty(cellData.value.applicant.name)
        },

        new TableColumn[ApplicationRecord, String]("Age") {
          cellValueFactory = cellData =>
            StringProperty(cellData.value.applicant.age.toString)
        },

        new TableColumn[ApplicationRecord, String]("Income (RM)") {
          cellValueFactory = cellData =>
            StringProperty(f"${cellData.value.applicant.monthlyIncome}%.2f")
        },

        new TableColumn[ApplicationRecord, String]("Subsidies") {
          cellValueFactory = cellData =>
            StringProperty(
              cellData.value.eligibleSubsidies
                .map(_.name)
                .mkString(", ")
            )
        },

        new TableColumn[ApplicationRecord, String]("Total Benefit (RM)") {
          cellValueFactory = cellData =>
            StringProperty(f"${cellData.value.totalBenefit}%.2f")
        }

      )

      columnResizePolicy = TableView.ConstrainedResizePolicy
    }

    new Scene(900, 600) {

      root = new BorderPane {

        padding = Insets(20)

        top = new VBox {

          alignment = Pos.Center

          children = Seq(

            new Label("Application Records") {
              style =
                "-fx-font-size: 24px; -fx-font-weight: bold;"
            }

          )

        }

        center = table

        bottom = new HBox {

          alignment = Pos.Center
          spacing = 20
          padding = Insets(20)

          children = Seq(

            new Button("Back") {

              onAction = _ =>
                SceneManager.showHome()

            }

          )

        }

      }

    }

  }

}