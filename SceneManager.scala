package util

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import view._

object SceneManager {

  private var primaryStage: JFXApp3.PrimaryStage = _

  def setStage(stage: JFXApp3.PrimaryStage): Unit = {
    primaryStage = stage
  }

  def showHome(): Unit = {
    primaryStage.scene = HomeView.createScene()
  }

  def showApplicantForm(): Unit = {
    primaryStage.scene = ApplicantFormView.createScene()
  }

  def showResult(): Unit = {
    primaryStage.scene = ResultView.createScene()
  }

  def showRecords(): Unit = {
    primaryStage.scene = RecordsView.createScene()
  }

  def showAbout(): Unit = {
    primaryStage.scene = AboutView.createScene()
  }

}