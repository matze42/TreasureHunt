package person.model

import java.time.LocalDate

import scala.util.Random
import scalafx.collections.ObservableBuffer


object PersonsStorage {
  val persons = new ObservableBuffer[Person]()
  initDemoPersons()

  def addPerson(p: Person) = persons += p

  def deletePerson(p: Person) = persons.-=(p)

  def initDemoPersons(): Unit = {
    persons.clear()
    for (i <- 1 to 100) {
      val p = new Person("LastName" + i, "FirstName" + i, "Street " + i, 59111, "Some City", LocalDate.of(1999, Random.nextInt(12) + 1, 21))
      addPerson(p)
    }
  }
}
