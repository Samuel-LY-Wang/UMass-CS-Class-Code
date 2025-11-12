class event:
    def __init__(self, date, description="NA"):
        self.date = date
        date_parts = date.split('-')
        self.year = int(date_parts[0])
        self.month = int(date_parts[1])
        self.day = int(date_parts[2])
        self.description = description
        self.next = None
        self.previous = None
    
    def __eq__(self, other):
        return self.date == other.date
    
    def __lt__(self, other):
        return self.date < other.date
    
    def __gt__(self, other):
        return self.date > other.date
    
    def __str__(self):
        return f"Event on {self.date}: {self.description}"
    
    def __repr__(self):
        return f"Event(date='{self.date}', description='{self.description}')"