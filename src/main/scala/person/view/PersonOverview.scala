package person.view

import scalafx.scene.text.Text

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{AnchorPane, HBox, BorderPane}
import scalafx.scene.paint.Color._
import scalafx.scene.control.{TableView, TableColumn, SplitPane, Label}

import person.model.Person


object PersonOverview extends JFXApp {

  val firstNameColumn = new TableColumn[Person, String] {
    text = "First Name"
    cellValueFactory = {
      _.value.firstName
    }
    prefWidth = 180
  }

  val lastNameColumn = new TableColumn[Person, String] {
    text = "Last Name"
    cellValueFactory = {
      _.value.lastName
    }
    prefWidth = 180
  }

  val leftSplitPane = new BorderPane {
    center = new TableView[Person](Person.getTeamMembers) {
      columns +=(firstNameColumn, lastNameColumn)


    }
  }

  val label = new Label {
    text = "Right SplitPane / Center"

  }

  val label3 = new Label {
    text = "Second label"
  }

  AnchorPane.setTopAnchor(label, 20.0)
  AnchorPane.setTopAnchor(label3, 50.0)

  val rightSplitPane = new BorderPane {
    center = new AnchorPane {
      content = Seq(
        label, label3

      )

    }
  }

  stage = new PrimaryStage {
    title = "Address App"

    scene = new Scene {
      fill = WHITE

      content = new BorderPane {
        padding = Insets(20)
        top = new Label {
          text = "Hallo im TOP!"
        }
        center = new SplitPane {
          items.addAll(leftSplitPane, rightSplitPane)

        }
      }
    }
  }
}

