import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"
import pygame
from moving_vehicle import moving_vehicle
from single_key_mover import SingleKeyMover

class FourDirectionMover():
    def __init__(self, vehicle: moving_vehicle, up_key: str, down_key: str, left_key: str, right_key: str, speed: int):
        '''
        vehicle: the vehicle to be moved
        up_key: the key that moves the vehicle up
        down_key: the key that moves the vehicle down
        left_key: the key that moves the vehicle left
        right_key: the key that moves the vehicle right
        speed: the speed at which the vehicle moves in any direction'''
        self.up_mover = SingleKeyMover(vehicle, up_key, (0, -speed))
        self.down_mover = SingleKeyMover(vehicle, down_key, (0, speed))
        self.left_mover = SingleKeyMover(vehicle, left_key, (-speed, 0))
        self.right_mover = SingleKeyMover(vehicle, right_key, (speed, 0))

    def update(self):
        self.up_mover.move_by_key()
        self.down_mover.move_by_key()
        self.left_mover.move_by_key()
        self.right_mover.move_by_key()