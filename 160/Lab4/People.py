class People():
    def __init__(self):
        self.people = []
    def add(self, person):
        self.people.append(person)
    def search_people(self, p):
        found = []
        for person in self.people:
            if person == p:
                found.append(person)
        return found