package person

import person.model.Person
import person.presenter.{PersonOverviewPresenter, PersonEditInterface}

import scala.reflect.runtime.universe.typeOf
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.stage.{Modality, Stage}

import scalafxml.core.{DependenciesByType, FXMLView, FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import javafx.{scene => jfxs}

object PersonApp extends JFXApp {

  val rootLayout = FXMLView(getClass.getResource("view/RootLayout.fxml"), NoDependencyResolver)

  // TODO: Very clumsy. need to find a better way to access setCenter
  val bp = rootLayout.asInstanceOf[javafx.scene.layout.BorderPane]

     val personOverview = FXMLView(getClass.getResource("view/PersonOverview.fxml"), NoDependencyResolver)

//  val personOverviewLoader = new FXMLLoader(getClass.getResource("view/PersonOverview.fxml"), NoDependencyResolver)
//  // Load the FXML, the controller will be instantiated
//  personOverviewLoader.load()
//  // Get the scene root
//  val personOverview = personOverviewLoader.getRoot[jfxs.Parent]
//  val personOverviewPresenter = personOverviewLoader.getController[PersonOverviewPresenter]


  // val personEditDialog = FXMLView(getClass.getResource("view/PersonEditDialog.fxml"), NoDependencyResolver)
 // Instead of FXMLView, we create a new ScalaFXML loader
  val loader = new FXMLLoader(getClass.getResource("view/PersonEditDialog.fxml"), NoDependencyResolver)
  // Load the FXML, the controller will be instantiated
  loader.load()
   // Get the scene root
  val personEditDialog = loader.getRoot[jfxs.Parent]

  stage = new JFXApp.PrimaryStage() {
    title = "Person Overview"
    scene = new Scene(rootLayout)
  }

  showPersonOverview()

  def initRootLayout() = {

  }

  def showPersonOverview() = {
    bp.setCenter(personOverview)
  }

  def showPersonEditDialog(person: Person): Boolean = {
    //TODO Stürtz ab beim zweiten aufruf
    val dialogStage: Stage = new Stage()
    dialogStage.title = "Edit Person"
    dialogStage.initModality(Modality.WINDOW_MODAL)
    dialogStage.initOwner(stage)
    dialogStage.scene = new Scene(personEditDialog)

    val controller = loader.getController[PersonEditInterface]
    controller.setDialogStage(dialogStage)
    controller.setPerson(person)
    dialogStage.showAndWait()

    controller.isOkClicked()

  }
}