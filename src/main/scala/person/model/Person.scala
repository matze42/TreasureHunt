package person.model

import scala.collection.mutable
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer

class Person(val lastName_ : String, val firstName_ : String) {
  val firstName = new StringProperty(this, "firstName", firstName_)
  val lastName = new StringProperty(this, "lastName", lastName_)
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