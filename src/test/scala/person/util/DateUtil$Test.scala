package person.util

import java.time.LocalDate
import java.time.format.DateTimeParseException

import org.scalatest.FunSuite

/**
 * Created by matthiasheck on 12.10.14.
 */
class DateUtil$Test extends FunSuite {

  test("Format Date for null") {
    assert(DateUtil.format(null) === null)
  }

  test("Format Date for various dates") {
    assert(DateUtil.format(LocalDate.of(1990, 4, 14)) === "14.04.1990")
    assert(DateUtil.format(LocalDate.of(2014, 10, 12)) === "12.10.2014")
    assert(DateUtil.format(LocalDate.of(2000, 1, 1)) === "01.01.2000")
    assert(DateUtil.format(LocalDate.of(1, 1, 1)) === "01.01.0001")
    assert(DateUtil.format(LocalDate.of(9999, 12, 31)) === "31.12.9999")
  }

  test("parse ") {
    assert(DateUtil.parse("14.04.1990") === LocalDate.of(1990, 4, 14))
  }

  test("parse with invalid date string") {
    assert(DateUtil.parse("wurstsalat") === null)
  }

  test("parse with valid date strings") {
    assert(DateUtil.parse("14.04.1990") === LocalDate.of(1990, 4, 14))
    assert(DateUtil.parse("12.10.2014") === LocalDate.of(2014, 10, 12))
    assert(DateUtil.parse("01.01.2000") === LocalDate.of(2000, 1, 1))
  }

  test("Valid date") {
    assert(DateUtil.validDate("invalid date") === false)
    assert(DateUtil.validDate("08.07.1996") === true)
  }

}
