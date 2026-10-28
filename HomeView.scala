package view

import scalafx.geometry.Insets
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox
import scalafx.scene.text.Font
import util.SceneManager

object HomeView {

  def createScene(): Scene = {

    new Scene(900, 600) {

      root = new VBox {

        alignment = Pos.Center
        spacing = 20
        padding = Insets(30)

        children = Seq(

          new Label("Subsidy Eligibility Calculator") {
            font = Font(30)
          },

          new Label("Determine Your Government Assistance Eligibility") {
            font = Font(16)
          },

          new Button("Start New Application") {
            onAction = _ => SceneManager.showApplicantForm()
          },

          new Button("View Records") {
            onAction = _ => SceneManager.showRecords()
          },

          new Button("About") {
            onAction = _ => SceneManager.showAbout()
          },

          new Button("Exit") {
            onAction = _ => sys.exit(0)
          }

        )

      }

    }

  }

}