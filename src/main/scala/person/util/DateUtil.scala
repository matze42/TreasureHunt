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

  def parse(dateString: String): Option[LocalDate] = {
   try {
      Some(LocalDate.parse(dateString, DATE_FORMATTER))
    }
    catch {
      case e: DateTimeParseException => None
      case e: Exception => throw e
    }
   }

  def validDate(dateString: String): Boolean = {
    DateUtil.parse(dateString).isDefined
  }

}

