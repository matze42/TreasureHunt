package converter.controller

import scalafx.event.ActionEvent
import scalafx.application.Platform
import scalafx.scene.control.{ComboBox, TextField}
import scalafxml.core.macros.sfxml

import converter.model.UnitConverters
import converter.model.UnitConverter

import javafx.beans.binding.StringBinding

@sfxml
class UnitConverterPresenter(
                              private val from: TextField,
                              private val to: TextField,
                              private val types: ComboBox[UnitConverter],
                              private val converters: UnitConverters) {

  // Filling the combo box
  for (converter <- converters.available) {
    types += converter
  }
  types.getSelectionModel.selectFirst()

  // Data binding
  to.text <== new StringBinding {
    bind(from.text.delegate, types.getSelectionModel.selectedItemProperty)

    def computeValue() = types.getSelectionModel.getSelectedItem.run(from.text.value)
  }

  // Close button event handler
  def onClose(event: ActionEvent) {
    Platform.exit()
  }
}