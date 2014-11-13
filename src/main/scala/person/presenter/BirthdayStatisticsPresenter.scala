package person.presenter

import java.text.DateFormatSymbols
import java.util.Locale

import person.model.Person

import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import scalafx.scene.chart.{BarChart, CategoryAxis, XYChart}
import scalafxml.core.macros.sfxml


trait BirthdayStatisticsInterface {
  def setPersonData(persons: List[Person]): Unit
}

@sfxml
class BirthdayStatisticsPresenter(
                                   private val barChart: BarChart[String, Integer],
                                   private val xAxis: CategoryAxis
                                   ) extends BirthdayStatisticsInterface {

  private val monthNames = new ObservableBuffer[String]()


  val months: Array[String] = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths
  monthNames ++= months.toList
  xAxis.setCategories(monthNames)


  def setPersonData(persons: List[Person]) = {
    val monthCounter = new Array[Integer](12)
    for (i <- 0 until monthCounter.length) {
      monthCounter(i) = 0
    }

    for (p <- persons) {
      val month = p.birthday.value.getMonthValue - 1
      monthCounter(month) = monthCounter(month) + 1
    }
    val series = new XYChart.Series[String, Integer]
    for (i <- 0 until monthCounter.length ) {
      series.data() += XYChart.Data[String, Integer](monthNames.get(i), monthCounter(i))
    }

//    val content = new ObservableBuffer[XYChart.Series[String, Integer]]()
//    content.addAll(series)
    barChart.getData.addAll(series)

  }
}


