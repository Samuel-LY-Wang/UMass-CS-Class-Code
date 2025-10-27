import pygame
import Vehicle

class KeyboardMover:
    def __init__(self, vehicle: Vehicle.Vehicle, key: str):
        '''
        vehicle: the vehicle to be moved
        key: the key that moves the vehicle'''
        assert isinstance(vehicle, Vehicle.Vehicle), "Vehicle must use Vehicle class!"
        assert isinstance(key, str), "Key must be passed in as a string!"
        assert len(key)==1, "Key must be a single character long!"
        self.vehicle = vehicle
        self.key = ord(key)
    
    def processEvent(self):
        '''
        HORRIBLE WAY OF WRITING CODE\n
        '''
        pressedKeys =  pygame.key.get_pressed()
        if pressedKeys[self.key]:
            self.vehicle.setPosition(self.vehicle.getX() + 1, self.vehicle.getY())
        for event in pygame.event.get():
            if (event.type == pygame.QUIT):
                return False
        return True