package samples

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.{Label, TableView}
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

import person.model.Person

class Test2 extends Application {
  println("Tesdddddt2()")

  override def start(primaryStage: Stage) {
    primaryStage.setTitle("Sup!")

    val root = new BorderPane
    root.setTop(new Label("Hello World from the top!"))
    root.setCenter(new TableView[Person]())


    primaryStage.setScene(new Scene(root, 300, 300))
    primaryStage.show()
  }
}

object Test2 {
  def main(args: Array[String]) {
    Application.launch(classOf[Test2], args: _*)
  }
}