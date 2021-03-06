package samples


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

object ScalaFXHelloWorld extends JFXApp {

  stage = new PrimaryStage {
    title = "ScalaFX Hello World"
    scene = new Scene {
      fill = BLACK
      content = new HBox {
        padding = Insets(20)
        content = Seq(
          new Text {
            text = "Hello "
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PALEGREEN, SEAGREEN))
          },
          new Text {
            text = "World!!!"
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(CYAN, DODGERBLUE)
            )
            effect = new DropShadow {
              color = DODGERBLUE
              radius = 25
              spread = 0.25
            }
          }
        )
      }
    }
  }
}