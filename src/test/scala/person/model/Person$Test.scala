package person.model

import org.scalatest.FunSuite


class Person$Test extends FunSuite {
     test("add persons") {
       Person.addPerson(new Person("Müller", "Klaus"))
       Person.addPerson(new Person("Bremmer", "Wolfgang"))
       Person.addPerson(new Person("Rodensiel", "Herbert"))
       Person.addPerson(new Person("Klaasen", "Maria"))
       Person.addPerson(new Person("Beckenstiel", "Andrea"))

       for (p<- Person.persons) {
         println("Person: "+p)
       }
       assert(true == true)
     }
}
