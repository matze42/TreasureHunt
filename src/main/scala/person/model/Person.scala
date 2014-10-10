package person.model

import java.time.LocalDate

import scala.collection.mutable
import scalafx.beans.property.{ObjectProperty, IntegerProperty, StringProperty}
import scalafx.collections.ObservableBuffer

class Person(val lastName_ : String, val firstName_ : String) {
  val firstName = new StringProperty(this, "firstName", firstName_)
  val lastName = new StringProperty(this, "lastName", lastName_)
  val street = new StringProperty(this, "street", "Some Street")
  val postalCode = new IntegerProperty(this, "postalCode", 59199)
  val city = new StringProperty(this, "city", "Big City")
  val birthday = new ObjectProperty[LocalDate](this, "birthday", LocalDate.of(1999, 2, 21))
}

object Person {
  val persons = new mutable.MutableList[Person]

  def addPerson(p: Person) = persons += p

  def getTeamMembers: ObservableBuffer[Person] = {
    val teamMembers = new ObservableBuffer[Person]()
    for (i <- 1 to 1000) {
      teamMembers += new Person("FirstName" + i, "LastName" + i)
    }
    teamMembers
  }

}