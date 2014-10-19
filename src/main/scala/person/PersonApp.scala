package person

import scala.reflect.runtime.universe.typeOf
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane

import scalafxml.core.{DependenciesByType, FXMLView, NoDependencyResolver}
import scalafx.Includes._


object PersonApp extends JFXApp {

  val root = FXMLView(getClass.getResource("view/RootLayout.fxml"), NoDependencyResolver)

  // TODO: Very clumsy. need to find a better way to access setCenter
  val bp = root.asInstanceOf[javafx.scene.layout.BorderPane]

  val personOverview = FXMLView(getClass.getResource("view/PersonOverview.fxml"), NoDependencyResolver)

  stage = new JFXApp.PrimaryStage() {
    title = "Person Overview"
    scene = new Scene(root)
  }

  showPersonOverview()

  def initRootLayout() = {

  }

  def showPersonOverview() = {

 // Dialogs.create().owner(stage.).title("Titel des Dialogs").masthead("masthead ....").message("Dies ist die Message!").showError()
  bp.setCenter(personOverview)
  }
}