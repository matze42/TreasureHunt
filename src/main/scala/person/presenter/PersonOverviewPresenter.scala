package person.presenter

import scalafx.application.Platform
import scalafx.event.ActionEvent

import scalafxml.core.macros.sfxml



import javafx.beans.binding.StringBinding

@sfxml
class PersonOverviewPresenter {
  // Close button event handler
  def onClose(event: ActionEvent) {
    Platform.exit()
  }
}
