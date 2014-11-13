package person.presenter

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import person.PersonApp

import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutPresenter() {

  val logger = Logger(LoggerFactory.getLogger("RootLayoutPresenter"))
  logger.debug("Entering Constructor of RootLayoutPresenter")

  // Close button event handler
  def onClose(event: ActionEvent) {
    Platform.exit()
  }

  def handleShowBirthdayStatistics() = {
    PersonApp.showBirthdayStatistics()
  }
}
