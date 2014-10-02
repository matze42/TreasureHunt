package person.model

import scala.collection.mutable


case class Person(val name: String, val firstName: String) {


}


object Person {
 val persons = new mutable.MutableList[Person]
 def addPerson(p: Person) = persons += p
}