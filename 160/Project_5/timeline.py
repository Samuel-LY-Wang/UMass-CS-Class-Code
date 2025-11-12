from event import event

class timeline:
    def __init__(self):
        self.start = None
        self.end = None
        self.count = 0
    
    def __len__(self):
        return self.count
    
    def __str__(self):
        events = []
        current = self.start
        while current:
            events.append(current.date)
            current = current.next
        return "→".join(events)

    def add(self, e: event, front: bool = False) -> None:
        if not isinstance(e, event):
            raise NotImplementedError
        
        if e.next is not None or e.previous is not None:
            raise NotImplementedError
        
        if self.start is None:
            self.start = e
            self.end = e
        elif front:
            e.next = self.start
            self.start.previous = e
            self.start = e
        else:
            self.end.next = e
            e.previous = self.end
            self.end = e
        
        self.count += 1

    def remove(self, e: event, front: bool = False) -> None:
        if not isinstance(e, event):
            raise NotImplementedError
        if front:
            current = self.start
            while current:
                if current == e:
                    if current.previous:
                        current.previous.next = current.next
                    if current.next:
                        current.next.previous = current.previous
                    if current == self.start:
                        self.start = current.next
                    if current == self.end:
                        self.end = current.previous
                    self.count -= 1
                    return
                current = current.next
        else:
            current = self.end
            while current:
                if current == e:
                    if current.previous:
                        current.previous.next = current.next
                    if current.next:
                        current.next.previous = current.previous
                    if current == self.start:
                        self.start = current.next
                    if current == self.end:
                        self.end = current.previous
                    self.count -= 1
                    return
                current = current.previous
        raise ValueError("event not found in the Timeline.")
    
    def swap(self, ind1: int, ind2: int) -> None:
        # for simplicity's sake, force ind1<ind2
        ind1, ind2 = min(ind1, ind2), max(ind1, ind2)
        if (ind1 < 0 or ind1 >= self.count or ind2 < 0 or ind2 >= self.count):
            raise IndexError("Index out of range.")
        if ind1 == ind2:
            return
        ptr1=self.start
        ptr2=self.start
        for i in range(ind2):
            if i<ind1:
                ptr1=ptr1.next
            ptr2=ptr2.next
        # now ptr1 is equivalent to self[ind1] and ptr2 is equivalent to self[ind2]
        ptr2.previous.next=ptr1
        ptr1.next.previous=ptr2
        if ptr1.previous:
            ptr1.previous.next=ptr2
        else:
            self.start=ptr2
        if ptr2.next:
            #todo: have ptr2.next to ptr2
            ptr2.next.previous=ptr1
        else:
            self.end=ptr1
        # now actually swap ptr1 and ptr2 next/prev
        ptr1.previous, ptr2.previous = ptr2.previous, ptr1.previous
        ptr1.next, ptr2.next = ptr2.next, ptr1.next

    
    def sort(self, rev=False) -> None:
        if self.count < 2:
            # already sorted
            return
        for i in range(self.count-1):
            node_i=self.start
            for _ in range(i):
                node_i=node_i.next
            ptr=node_i
            extr_event=ptr
            extr_ind=i
            for j in range(i+1, self.count):
                ptr=ptr.next
                if (not rev and ptr < extr_event) or (rev and ptr > extr_event):
                    extr_event=ptr
                    extr_ind=j
            self.swap(i, extr_ind)
        # print(self)
    
    def add_sorted(self, e: event, rev=False) -> None:
        if not isinstance(e, event):
            raise ValueError("Can only add event objects to Timeline!")
        self.sort(rev=rev)
        if self.start is None:
            self.start = e
            self.end = e
        else:
            current = self.start
            while current:
                if (not rev and e < current) or (rev and e > current):
                    if current.previous:
                        current.previous.next = e
                        e.previous = current.previous
                    else:
                        self.start = e
                    e.next = current
                    current.previous = e
                    self.count += 1
                    return
                current = current.next
            # if we reach here, add to end
            self.end.next = e
            e.previous = self.end
            self.end = e
        self.count += 1