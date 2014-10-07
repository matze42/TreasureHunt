package converter

import scala.reflect.runtime.universe.typeOf
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafxml.core.{DependenciesByType, FXMLView}
import scalafx.Includes.jfxParent2sfx


import converter.model.{UnitConverters, MMtoInches, InchesToMM}

object ConverterApp extends JFXApp {

  val root = FXMLView(getClass.getResource("view/Converter.fxml"),
    new DependenciesByType(Map(
      typeOf[UnitConverters] -> new UnitConverters(InchesToMM, MMtoInches))))

  stage = new JFXApp.PrimaryStage() {
    title = "Unit conversion"
    scene = new Scene( root )

  }
}
