package person

import javafx.{scene => jfxs}

import person.model.Person
import person.presenter.{BirthdayStatisticsInterface, PersonEditInterface}

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.stage.{Modality, Stage}
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}

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
    //TODO fxml mus jedesmal geladen werden, damit nicht beimzweiten Aufruf exception mit blahbla ist already root of another element
    // evtl. optimieren und geladenes fxml wiederverwenden.

    // val personEditDialog = FXMLView(getClass.getResource("view/PersonEditDialog.fxml"), NoDependencyResolver)
    // Instead of FXMLView, we create a new ScalaFXML loader
    val loader = new FXMLLoader(getClass.getResource("view/PersonEditDialog.fxml"), NoDependencyResolver)
    // Load the FXML, the controller will be instantiated
    loader.load()
    // Get the scene root
    val personEditDialog = loader.getRoot[jfxs.Parent]

    val dialogStage: Stage = new Stage()
    dialogStage.title = "Edit Person"
    dialogStage.initModality(Modality.WINDOW_MODAL)
    dialogStage.initOwner(stage)

    dialogStage.scene = new Scene(personEditDialog)

    val controller = loader.getController[PersonEditInterface]()
    controller.setDialogStage(dialogStage)
    controller.setPerson(person)
    dialogStage.showAndWait()
    controller.isOkClicked

  }

  def showBirthdayStatistics() = {
    val loader = new FXMLLoader(getClass.getResource("view/BirthdayStatistics.fxml"), NoDependencyResolver)
    // Load the FXML, the controller will be instantiated
    loader.load()
    // Get the scene root
    val birthdayStatisticsDialog = loader.getRoot[jfxs.Parent]

    val dialogStage: Stage = new Stage()
    dialogStage.title = "Birthday Statistics Title"
    dialogStage.initModality(Modality.WINDOW_MODAL)
    dialogStage.initOwner(stage)

    dialogStage.scene = new Scene(birthdayStatisticsDialog)

    val controller = loader.getController[BirthdayStatisticsInterface]()
//   controller.setPersonData( Person.persons.toList )
    controller.setPersonData( Person.persons.toList )
    dialogStage.show()

  }
}