package person.model

import java.time.LocalDate

import scala.collection.mutable
import scalafx.beans.property.{ObjectProperty, IntegerProperty, StringProperty}
import scalafx.collections.ObservableBuffer


class Person(
              val lastName_ : String,
              val firstName_ : String,
              val street_ : String,
              val postalCode_ : Integer,
              val city_ : String,
              val birthday_ : LocalDate
              ) {
  val firstName = new StringProperty(this, "firstName", firstName_)
  val lastName = new StringProperty(this, "lastName", lastName_)
  val street = new StringProperty(this, "street", street_)
  val postalCode = new IntegerProperty(this, "postalCode", postalCode_)
  val city = new StringProperty(this, "city", city_)
  val birthday = new ObjectProperty[LocalDate](this, "birthday", birthday_)
}

object Person {
  val persons = new mutable.MutableList[Person]

  def addPerson(p: Person) = persons += p

  def getTeamMembers: ObservableBuffer[Person] = {
    val teamMembers = new ObservableBuffer[Person]()
    for (i <- 1 to 100) {
      val p = new Person("LastName" + i, "FirstName" + i, "Street " + i, 59111, "Some City", LocalDate.of(1999, 2, 21))
      teamMembers += p
      addPerson(p)
    }
    teamMembers
  }

}