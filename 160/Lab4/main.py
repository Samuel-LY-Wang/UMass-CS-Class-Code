from Person import Person
from People import People

p1 = Person("Steve", "555-1234", "steve@gmail.com")
p2 = Person("Bob", "555-5678", "bob@gmail.com")
p3 = Person("Alice", "555-9999", "alice@gmail.com")
p4 = Person("Steve", "555-0000", "steve@gmail.com")


roster = People()
roster.add(p1)
roster.add(p2)
roster.add(p3)


print(roster.search_people(p4))