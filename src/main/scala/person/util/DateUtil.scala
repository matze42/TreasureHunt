package person.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


object DateUtil {
  val DATE_PATTERN = "dd.MM.yyyy"
  val DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN)

  def format(d: LocalDate): String = {
    if (d == null) null else DATE_FORMATTER.format(d)
  }

  def parse(dateString: String): LocalDate = {
    //TODO Find better approach without var
    var localDate: LocalDate = null
    try {
     localDate = LocalDate.parse(dateString, DATE_FORMATTER)
    }
    catch
      {
        case e : DateTimeParseException => localDate = null
        case e: Exception => throw  e
      }
    localDate
  }

  def validDate(dateString: String): Boolean = {
    DateUtil.parse(dateString) != null
  }

}

