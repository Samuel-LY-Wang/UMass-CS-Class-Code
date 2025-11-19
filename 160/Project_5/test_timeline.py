import unittest
import random
from timeline import timeline
from event import event

class TestTimeline(unittest.TestCase):
    def setUp(self):
        self.timeline = timeline()
        self.event1 = event("2023-01-01", "New Year's Day")
        self.event2 = event("2023-07-04", "Independence Day")
        self.event3 = event("2023-12-25", "Christmas Day")
        self.timeline.add(self.event1)
        self.timeline.add(self.event2)
        self.timeline.add(self.event3)

    def test_add_event(self):
        self.assertEqual(len(self.timeline), 3)
        self.assertEqual(self.timeline.start, self.event1)
        self.assertEqual(self.timeline.end, self.event3)

    def test_remove_event(self):
        self.timeline.remove(self.event2)
        self.assertEqual(len(self.timeline), 2)
        with self.assertRaises(ValueError):
            self.timeline.remove(self.event2)

    def test_sort_timeline(self):
        self.timeline.reverse()
        self.timeline.sort()
        self.assertEqual(self.timeline.start, self.event1)
        self.assertEqual(self.timeline.end, self.event3)
    
    def test_sort_empty(self):
        empty_timeline = timeline()
        empty_timeline.sort()  # Should not raise any error
        self.assertEqual(len(empty_timeline), 0)
    
    def test_sort_single_event(self):
        single_timeline = timeline()
        single_event = event("2024-01-01", "Single Event")
        single_timeline.add(single_event)
        single_timeline.sort()  # Should not raise any error
        self.assertEqual(len(single_timeline), 1)
        self.assertEqual(single_timeline.start, single_event)
    
    def test_sort_rev(self):
        self.timeline.sort(rev=True)
        self.assertEqual(self.timeline.start, self.event3)
        self.assertEqual(self.timeline.end, self.event1)
    
    def test_str(self):
        self.assertEqual(str(self.timeline), "2023-01-01→2023-07-04→2023-12-25")
    
    def test_add_sorted(self):
        new_event = event("2023-05-01", "Labor Day")
        self.timeline.add_sorted(new_event)
        self.assertEqual(len(self.timeline), 4)
        self.assertEqual(str(self.timeline), "2023-01-01→2023-05-01→2023-07-04→2023-12-25")
    
    def test_add_sorted_rev(self):
        new_event = event("2023-05-01", "Labor Day")
        self.timeline.add_sorted(new_event, rev=True)
        self.assertEqual(len(self.timeline), 4)
        self.assertEqual(str(self.timeline), "2023-12-25→2023-07-04→2023-05-01→2023-01-01")
    
    def test_random_list(self):
        dates = [f"2023-{str(month).zfill(2)}-{str(day).zfill(2)}" for month in range(1, 13) for day in range(1, 29)]
        random_events = [event(date, f"Event on {date}") for date in dates]
        random.shuffle(random_events)
        
        rand_timeline = timeline()
        for ev in random_events:
            rand_timeline.add(ev)
        
        rand_timeline.sort()
        
        sorted_dates = sorted(dates)
        current = rand_timeline.start
        for date in sorted_dates:
            self.assertEqual(current.date, date)
            current = current.next

if __name__ == '__main__':
    unittest.main()