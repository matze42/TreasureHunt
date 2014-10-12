package person.presenter

import person.model.Person
import person.util.DateUtil

import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.control.{TableColumn, TableView, Label}

import scalafxml.core.macros.sfxml
import scalafx.Includes._



import javafx.beans.binding.StringBinding

@sfxml
class PersonOverviewPresenter (
                                private val personTable: TableView[Person],
                                private val firstNameColumn: TableColumn[Person, String],
                                private val lastNameColumn: TableColumn[Person, String],
                                private val lastNameLabel: Label,
                                private val firstNameLabel: Label,
                                private val streetLabel: Label,
                                private val cityLabel: Label,
                                private val postalCodeLabel: Label,
                                private val birthdayLabel: Label) {


  println ("Hello from Constructor of PersonOverviewPresenter")
  firstNameColumn.cellValueFactory = { e: TableColumn.CellDataFeatures[Person, String] => e.getValue.firstName }
  lastNameColumn.cellValueFactory = { e: TableColumn.CellDataFeatures[Person, String] => e.getValue.lastName }
  personTable.items = Person.getTeamMembers

  showPersonDetails(null)
  personTable.selectionModel().selectedItem.onChange(( _, _, newvalue) => {showPersonDetails(newvalue)})

  def showPersonDetails(p: Person) = {
    if(p != null) {
      lastNameLabel.text = p.lastName.value
      firstNameLabel.text = p.firstName.value
      streetLabel.text = p.street.value
      cityLabel.text = p.city.value
      postalCodeLabel.text = p.postalCode.value.toString
      birthdayLabel.text = DateUtil.format(p.birthday.value)
    } else {
      lastNameLabel.text = ""
      firstNameLabel.text = ""
      streetLabel.text = ""
      cityLabel.text = ""
      postalCodeLabel.text = ""
      birthdayLabel.text = ""
    }
  }
}
