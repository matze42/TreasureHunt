package person.model

import java.time.LocalDate

import org.scalatest.FunSuite


class Person$Test extends FunSuite {
     test("add persons") {
       Person.addPerson(new Person("Müller", "Klaus", "Some Street", 60987, "Big City", LocalDate.of(1987,4,7)))
       Person.addPerson(new Person("Bremmer", "Wolfgang", "Some Street", 60987, "Big City", LocalDate.of(1987,4,7)))
       Person.addPerson(new Person("Rodensiel", "Herbert", "Some Street", 60987, "Big City", LocalDate.of(1987,4,7)))
       Person.addPerson(new Person("Klaasen", "Maria", "Some Street", 60987, "Big City", LocalDate.of(1987,4,7)))
       Person.addPerson(new Person("Beckenstiel", "Andrea", "Some Street", 60987, "Big City", LocalDate.of(1987,4,7)))

       for (p<- Person.persons) {
         println("Person: "+p.lastName.value+" "+p.firstName.value+" "+p.birthday.value)
       }

       assert(Person.persons.length === 5)
       assert( Person.persons(0).firstName.value === "Klaus")
       assert( Person.persons(4).lastName.value === "Beckenstiel")
     }
}
