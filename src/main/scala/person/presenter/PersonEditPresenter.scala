package person.presenter

import java.time.LocalDate
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType

import person.model.Person
import person.util.DateUtil

import scalafx.scene.control.TextField
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml

trait PersonEditInterface {
  def setDialogStage(dialogStage: Stage): Unit

  def isOkClicked: Boolean

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

  var dialogStage: Stage = _
  var okClicked: Boolean = false

  private var person: Person = new Person("", "", "", 0, "", LocalDate.now)

  def setDialogStage(dialogStage: Stage): Unit = {
    this.dialogStage = dialogStage
  }

  def isOkClicked: Boolean = okClicked

  def setPerson(p: Person) = {
    this.person = p


    lastNameField.setText(person.lastName.value)
    firstNameField.setText(person.firstName.value)
    streetField.setText(person.street.value)
    cityField.setText(person.city.value)
    postalCodeField.setText(person.postalCode.value.toString)
    birthdayField.setText(DateUtil.format(person.birthday.value).getOrElse("<<ERROR>>"))
    birthdayField.setPromptText("dd.mm.yyyy")

  }

  def handleOK(): Unit = {
    if (isInputValid) {
      person.firstName.set(firstNameField.getText)
      person.lastName.set(lastNameField.getText)
      person.street.set(streetField.getText)
      person.postalCode.set(postalCodeField.getText.toInt)
      person.city.set(cityField.getText)
      person.birthday.set(DateUtil.parse(birthdayField.getText).get)

      okClicked = true
      dialogStage.close()
    }
  }

  def handleCancel() = {
    dialogStage.close()
  }

  private def isInputValid: Boolean = {
    var errorMessage: String = ""
    if (firstNameField.getText == null || firstNameField.getText.length == 0) {
      errorMessage = errorMessage + "No valid first name!\n"
    }

    if (lastNameField.getText == null || lastNameField.getText.length == 0) {
      errorMessage = errorMessage + "No valid last name!\n"
    }

    if (streetField.getText == null || streetField.getText.length == 0) {
      errorMessage = errorMessage + "No valid street!\n"
    }

    if (postalCodeField.getText == null || postalCodeField.getText.length == 0) {
      errorMessage = errorMessage + "No valid postal code!\n"
    } else {
      try {
        Integer.parseInt(postalCodeField.getText)
      }
      catch {
        case e: NumberFormatException => errorMessage = errorMessage + "No valid postal code (must be an integer)!\n"
      }
    }

    if (cityField.getText == null || cityField.getText.length == 0) {
      errorMessage = errorMessage + "No valid city!\n"
    }

    if (birthdayField.getText == null || birthdayField.getText.length == 0) {
      errorMessage = errorMessage + "No valid birthday!\n"
    }
    else {
      if (!DateUtil.validDate(birthdayField.getText)) {
        errorMessage = errorMessage + "No valid birthday! Use the format dd.mm.yyyy!\n"

      }
    }


    if (errorMessage.length != 0) {
      val alertDialog = new Alert(AlertType.ERROR, "Invalid Fields! Please correct invalid fields.\n" + errorMessage)
      alertDialog.showAndWait()
    }
    errorMessage.length == 0
  }
}
