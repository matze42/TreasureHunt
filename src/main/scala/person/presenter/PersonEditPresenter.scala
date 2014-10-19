package person.presenter

import java.time.LocalDate
import java.util.Optional

import person.model.Person
import person.util.DateUtil

import scalafx.scene.control.{TextField}
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml

import javafx.scene.control.{ButtonType, Alert}
import javafx.scene.control.Alert.AlertType

trait PersonEditInterface {
  def setDialogStage(dialogStage: Stage): Unit
  def isOkClicked():Boolean
  def setPerson(p: Person): Unit
}

@sfxml
class PersonEditPresenter(
                           private val lastNameField: TextField,
                           private val firstNameField: TextField,
                           private val streetField: TextField,
                           private val cityField: TextField,
                           private val postalCodeField: TextField,
                           private val birthdayField: TextField)
extends PersonEditInterface {

  var dialogStage: Stage = null
  var okClicked: Boolean = false

  private var person: Person = new Person("", "", "", 0, "", LocalDate.now)

  def setDialogStage(dialogStage: Stage): Unit = {
    this.dialogStage = dialogStage
  }

  def isOkClicked(): Boolean = {
    return okClicked
  }

  def setPerson(p: Person) = {
    this.person = p


    lastNameField.setText(person.lastName.value)
    firstNameField.setText(person.firstName.value)
    streetField.setText(person.street.value)
    cityField.setText(person.city.value)
    postalCodeField.setText(person.postalCode.value.toString)
    birthdayField.setText(DateUtil.format(person.birthday.value))
    birthdayField.setPromptText("dd.mm.yyyy")

  }

  def handleOK(): Unit = {
    if (isInputValid()) {
      person.firstName.set(firstNameField.getText())
      person.lastName.set(lastNameField.getText)
      person.street.set(streetField.getText())
      person.postalCode.set(postalCodeField.getText.toInt)
      person.city.set(cityField.getText())
      person.birthday.set(DateUtil.parse(birthdayField.getText))

      okClicked = true
      dialogStage.close()
    }
  }

  def handleCancel() = {
    dialogStage.close()
  }

  private def isInputValid(): Boolean = {
    var errorMessage: String = ""
    if (firstNameField.getText == null || firstNameField.getText().length == 0) {
      errorMessage = errorMessage + "No valid first name!\n"
    }

    //TODO Mehrere Prüfungen

    if (errorMessage.length != 0) {
      val alertDialog = new Alert(AlertType.ERROR, "Invalid Fields! Please correct invalid fields.")
      val response: Optional[ButtonType] = alertDialog.showAndWait()
    }
    errorMessage.length == 0
  }
}
