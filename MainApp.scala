import scalafx.application.JFXApp3
import util.SceneManager

object MainApp extends JFXApp3 {

  override def start(): Unit = {

    stage = new JFXApp3.PrimaryStage {
      title = "Subsidy Eligibility Calculator"
      width = 900
      height = 600
    }

    SceneManager.setStage(stage)

    SceneManager.showHome()

  }

}