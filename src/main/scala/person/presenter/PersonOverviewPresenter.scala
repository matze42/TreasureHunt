package person.presenter

import person.model.Person

import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafx.scene.control.{TableColumn, TableView, Label}

import scalafxml.core.macros.sfxml



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
}
