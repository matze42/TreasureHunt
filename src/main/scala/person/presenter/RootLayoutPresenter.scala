package person.presenter

import scalafxml.core.macros.sfxml
import scalafx.application.Platform
import scalafx.event.ActionEvent

@sfxml
class RootLayoutPresenter () {


  println ("Hello from Constructor of RootLayoutPresenter")


  // Close button event handler
  def onClose(event: ActionEvent) {
    Platform.exit()
  }
}
