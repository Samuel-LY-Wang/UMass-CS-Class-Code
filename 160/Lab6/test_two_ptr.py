import unittest
import random
from merge_sort_two_pointer import mergesort as two_pointer_mergesort

class TestMergeSortTwoPointer(unittest.TestCase):
    def test_empty_array(self):
        self.assertEqual(two_pointer_mergesort([]), [])

    def test_single_element(self):
        self.assertEqual(two_pointer_mergesort([1]), [1])

    def test_sorted_array(self):
        self.assertEqual(two_pointer_mergesort([1, 2, 3, 4, 5]), [1, 2, 3, 4, 5])

    def test_reverse_sorted_array(self):
        self.assertEqual(two_pointer_mergesort([5, 4, 3, 2, 1]), [1, 2, 3, 4, 5])

    def test_unsorted_array(self):
        self.assertEqual(two_pointer_mergesort([3, 1, 4, 5, 2]), [1, 2, 3, 4, 5])

    def test_array_with_duplicates(self):
        self.assertEqual(two_pointer_mergesort([3, 1, 2, 3, 4, 2]), [1, 2, 2, 3, 3, 4])
    
    def test_large_array(self):
        arr=list(range(1000))
        for _ in range(10):
            random.shuffle(arr)
            self.assertEqual(two_pointer_mergesort(arr), list(range(1000)))

if __name__ == "__main__":
    unittest.main()