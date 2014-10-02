package person.view

import scalafx.scene.text.Text

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, BorderPane}
import scalafx.scene.paint.Color._
import scalafx.scene.control.{SplitPane, Label}


object PersonOverview extends JFXApp {

  val leftSplitPane = new BorderPane {
    center = new Label {
      text = "Left SplitPane / Center"
    }
  }

  val rightSplitPane = new BorderPane {
    center = new Label {
      text = "Right SplitPane / Center"
    }
  }

  stage = new PrimaryStage {
    title = "Address App"
    width = 500
    height = 400
    scene = new Scene {
      fill = WHITE

      content = new BorderPane {
        padding = Insets(20)
        top = new Label {
          text = "HAllo im TOP!"
        }
        center = new SplitPane {
          items.addAll(leftSplitPane, rightSplitPane)
        }
      }
    }
  }
}

