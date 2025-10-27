class Person:
    def __init__(self, n="no one", p = "555-555-0000", e = "abcd@gmail.com", a=0):
        self.name = n
        self.phone = p
        self.email = e
        self.age = a


    def __str__(self):
        return("{" + self.name + "," + self.phone + "," + self.email + "," + str(self.age) + "}")

    def __repr__(self):
        return f"Person({self.name}, {self.phone}, {self.email}, {self.age})"

    def __eq__(self, other):
        return self.name==other.name and self.phone==other.phone and self.email==other.email and self.age==other.age

    def eq_age(self, other_age):
        return self.age == other_age


if __name__=="__main__":
    p1 = Person("Steve", a=30)
    p2 = Person("Steve", a=30)
    print(p1==p2)