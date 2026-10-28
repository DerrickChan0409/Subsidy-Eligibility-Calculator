package view

import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.StackPane

object AboutView {

  def createScene(): Scene = {

    new Scene(900, 600) {

      root = new StackPane {

        children = Seq(
          new Label("About View")
        )

      }

    }

  }

}