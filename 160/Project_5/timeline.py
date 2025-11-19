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
    
    def reverse(self) -> None:
        # just reverses self
        current = self.start
        self.start, self.end = self.end, self.start
        while current:
            current.previous, current.next = current.next, current.previous
            current = current.previous  # because we swapped next and previous
    
    def sort(self, rev=False) -> None:
        if self.count < 2:
            # already sorted
            return

        current = self.start.next
        while current:
            ptr = current
            nxt = current.next # save prev and next since ptr may become detached
            prev = ptr.previous

            # if ptr is out of order, move it until it is
            if prev and ((not rev and ptr < prev) or (rev and ptr > prev)):
                # Detach ptr
                prev.next = ptr.next
                if ptr.next:
                    ptr.next.previous = prev
                else:
                    # ptr was the end
                    self.end = prev

                # Scan back to find insertion point
                scan = prev
                while scan and ((not rev and ptr < scan) or (rev and ptr > scan)):
                    scan = scan.previous

                # Insert after scan (None = add as self.start)
                if scan is None:
                    ptr.previous = None
                    ptr.next = self.start
                    self.start.previous = ptr
                    self.start = ptr
                else:
                    ptr.previous = scan
                    ptr.next = scan.next
                    scan.next = ptr
                    if ptr.next:
                        ptr.next.previous = ptr
                    else:
                        self.end = ptr

            current = nxt
    
    def add_sorted(self, e: event, rev=False) -> None:
        if not isinstance(e, event):
            raise NotImplementedError
        if e.next is not None or e.previous is not None:
            raise NotImplementedError
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