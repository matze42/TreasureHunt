package person.presenter

import java.time.LocalDate
import java.util.Optional
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{Alert, ButtonType}

import person.PersonApp
import person.model.Person
import person.util.DateUtil

import scalafx.Includes._
import scalafx.scene.control.{Label, TableColumn, TableView}
import scalafxml.core.macros.sfxml

@sfxml
class PersonOverviewPresenter(
                               private val personTable: TableView[Person],
                               private val firstNameColumn: TableColumn[Person, String],
                               private val lastNameColumn: TableColumn[Person, String],
                               private val lastNameLabel: Label,
                               private val firstNameLabel: Label,
                               private val streetLabel: Label,
                               private val cityLabel: Label,
                               private val postalCodeLabel: Label,
                               private val birthdayLabel: Label) {


  firstNameColumn.cellValueFactory = { e: TableColumn.CellDataFeatures[Person, String] => e.getValue.firstName}
  lastNameColumn.cellValueFactory = { e: TableColumn.CellDataFeatures[Person, String] => e.getValue.lastName}

  //TODO: Vernünftige REferenz zum Modell aufbauen
  var persons = Person.getTeamMembers
  personTable.items = persons

  showPersonDetails(null)

  personTable.selectionModel().selectedItem.onChange((_, _, newValue) => {
    showPersonDetails(newValue)
  })


  def showPersonDetails(p: Person) = {
    if (p != null) {
      lastNameLabel.text = p.lastName.value
      firstNameLabel.text = p.firstName.value
      streetLabel.text = p.street.value
      cityLabel.text = p.city.value
      postalCodeLabel.text = p.postalCode.value.toString
      birthdayLabel.text = DateUtil.format(p.birthday.value).getOrElse("<<ERROR>>")
    } else {
      lastNameLabel.text = ""
      firstNameLabel.text = ""
      streetLabel.text = ""
      cityLabel.text = ""
      postalCodeLabel.text = ""
      birthdayLabel.text = ""
    }
  }


  def handleDeletePerson(): Unit = {

    val personToDelete = personTable.selectionModel().getSelectedItem
    val alertDialog = new Alert(AlertType.CONFIRMATION, "Are you sure that you want to delete " + personToDelete.firstName.value + " " + personToDelete.lastName.value + "?")

    val response: Optional[ButtonType] = alertDialog.showAndWait()
    if (response.get() == ButtonType.OK) {

      //TODO: Vernünftige REferenz zum Modell aufbauen
      persons.-=(personToDelete)
      personTable.getSelectionModel.clearSelection()
    }
  }

  def handleNewPerson(): Unit = {
    val tempPerson: Person = new Person("", "", "", 0, "", LocalDate.now)
    val okClicked: Boolean = PersonApp.showPersonEditDialog(tempPerson)
    if (okClicked) {
      persons.+=(tempPerson)
    }
  }

  def handleEditPerson(): Unit = {
    val personToEdit = personTable.selectionModel().getSelectedItem
    if (personToEdit != null) {
      val okClicked: Boolean = PersonApp.showPersonEditDialog(personToEdit)
      if (okClicked) {
        showPersonDetails(personToEdit)
      }
    }
  }
}
