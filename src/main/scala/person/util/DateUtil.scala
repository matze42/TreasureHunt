package person.util

import java.time.LocalDate
import java.time.format.{DateTimeFormatter, DateTimeParseException}

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory


object DateUtil {
  val logger = Logger(LoggerFactory.getLogger("DateUtil"))
  val DATE_PATTERN = "dd.MM.yyyy"
  val DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN)

  def format(d: LocalDate): Option[String] = {
    if (d == null) None else Some(DATE_FORMATTER.format(d))
  }

  def parse(dateString: String): Option[LocalDate] = {
    require( dateString != null )
    try {
      Some(LocalDate.parse(dateString, DATE_FORMATTER))
    }
    catch {
      case e: DateTimeParseException => None
      case e: Exception => logger.error("Unexpected exception "+e); throw e
    }
  }

  def validDate(dateString: String): Boolean = {
    DateUtil.parse(dateString).isDefined
  }

}

