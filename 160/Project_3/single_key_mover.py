import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"
import pygame
from moving_vehicle import moving_vehicle

class SingleKeyMover:
    def __init__(self, vehicle: moving_vehicle, key: str, velocity: tuple[int, int]):
        '''
        vehicle: the vehicle to be moved
        key: the key that moves the vehicle'''
        assert isinstance(key, str), "Key must be passed in as a string!"
        assert len(key)==1, "Key must be a single character long!"
        self.vehicle = vehicle
        self.v_x = velocity[0]
        self.v_y = velocity[1]
        self.key = ord(key)
    
    def move_by_key(self):
        '''
        Moves self by the specified velocity if self's key is pressed.
        '''
        pressedKeys =  pygame.key.get_pressed()
        if pressedKeys[self.key]:
            self.vehicle.set_location((self.vehicle.x + self.v_x, self.vehicle.y + self.v_y))