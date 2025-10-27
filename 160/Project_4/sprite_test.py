import unittest
from my_sprite import my_sprite
from sprite_collection import sprite_collection

class TestSprite(unittest.TestCase):
    def setUp(self):
        self.sprite1 = my_sprite("160/Project_4/images/vehicles/green_car.png", (10, 20))
        self.sprite1_copy = my_sprite("160/Project_4/images/vehicles/green_car.png", (10, 20))
        self.sprite2 = my_sprite("160/Project_4/images/vehicles/red_car.png", (10, 20))
        self.sprite2_copy = my_sprite("160/Project_4/images/vehicles/red_car.png", (10, 20))
        self.sprite2_copy.scale_img(2)
        self.sprite3 = my_sprite("160/Project_4/images/vehicles/green_car.png", (30, 40))
    
    def tearDown(self):
        del self.sprite1
        del self.sprite1_copy
        del self.sprite2
        del self.sprite2_copy
        del self.sprite3
    
    def test_equality_same_obj(self):
        self.assertEqual(self.sprite1, self.sprite1)
    
    def test_equality_same_image(self):
        self.assertEqual(self.sprite1, self.sprite1_copy)

    def test_equality_diff_images(self):
        self.assertEqual(self.sprite1, self.sprite2)
    
    def test_equality_diff_locs(self):
        self.assertNotEqual(self.sprite1, self.sprite3)
    
    def test_diff_sizes(self):
        self.assertNotEqual(self.sprite2, self.sprite2_copy)

class TestSpriteCollection(unittest.TestCase):
    def setUp(self):
        self.sprite1 = my_sprite("160/Project_4/images/vehicles/green_car.png", (10, 20))
        self.sprite2 = my_sprite("160/Project_4/images/vehicles/green_car.png", (10, 20))
        self.sprite3 = my_sprite("160/Project_4/images/vehicles/red_car.png", (10, 20))
        self.sprite4 = my_sprite("160/Project_4/images/vehicles/red_car.png", (10, 20))
        self.sprite4.scale_img(2)
        self.sprite5 = my_sprite("160/Project_4/images/vehicles/green_car.png", (30, 40))
        self.dup_sprite1 = self.sprite1
        self.blank_sprite = my_sprite("160/Project_4/images/decor/tree.png", (0,0))
        self.collection = sprite_collection()
        self.collection.add(self.sprite1)
        self.collection.add(self.sprite2)
        self.collection.add(self.sprite3)
        self.collection.add(self.sprite4)
        self.collection.add(self.sprite5)
        self.collection.add(self.dup_sprite1)
        self.blank_collection = sprite_collection()
        # print(self.collection)
    
    def tearDown(self):
        del self.sprite1
        del self.sprite2
        del self.sprite3
        del self.sprite4
        del self.sprite5
        del self.collection
        del self.blank_collection
    
    def test_invalid_add(self):
        with self.assertRaises(TypeError):
            self.collection.add("not a sprite")
            self.collection.add(123)
            self.collection.add(True)
    
    def test_invalid_get(self):
        with self.assertRaises(TypeError):
            self.collection["not an int"]
            self.collection[1.5]
            self.collection[None]
    
    def test_OOB_get(self):
        with self.assertRaises(IndexError):
            self.collection[6]
            self.collection[-7]
            self.collection[100]
            self.blank_collection[0]
            self.blank_collection[-1]
    
    def test_invalid_set(self):
        with self.assertRaises(TypeError):
            self.collection["not an int"] = self.sprite1
            self.collection[1.5] = self.sprite1
            self.collection[None] = self.sprite1
        with self.assertRaises(TypeError):
            self.collection[0] = "not a sprite"
            self.collection[0] = 123
            self.collection[0] = True
    
    def test_OOB_set(self):
        with self.assertRaises(IndexError):
            self.collection[6] = self.sprite1
            self.collection[-7] = self.sprite1
            self.collection[100] = self.sprite1
            self.blank_collection[0] = self.sprite1
            self.blank_collection[-1] = self.sprite1

    def test_adding(self):
        self.assertEqual(len(self.blank_collection), 0)
        self.assertEqual(len(self.collection), 6)
    
    def test_get(self):
        self.assertEqual(self.collection[0], self.sprite1)
        self.assertEqual(self.collection[4], self.sprite5)
    
    def test_set(self):
        self.collection[0] = self.blank_sprite
        self.assertEqual(self.collection[0], self.blank_sprite)
        self.collection[0] = self.sprite1 # reset to original state
    
    def test_search(self):
        results = self.collection.search(self.sprite1)
        self.assertEqual(len(results), 3)
        for sprite in results:
            self.assertEqual(sprite, self.sprite1)

        results = self.collection.search(self.sprite4)
        self.assertEqual(len(results), 1)
        self.assertEqual(results[0], self.sprite4)

        results = self.blank_collection.search(self.sprite1)
        self.assertEqual(len(results), 0)

        results = self.collection.search(my_sprite("160/Project_4/images/vehicles/yellow_convertible.png", (0,0)))
        self.assertEqual(len(results), 0)
    
    def test_invalid_search(self):
        with self.assertRaises(TypeError):
            self.collection.search("not a sprite")
            self.collection.search(123)
            self.collection.search(True)

if __name__=="__main__":
    unittest.main()