import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"
import pygame

class my_sprite():
    def __init__(self, image_fname: str, loc: tuple[int, int]=(0,0)):
        self.image_fname = image_fname
        self.image = pygame.image.load(image_fname)
        self.width = self.image.get_width()
        self.height = self.image.get_height()
        self.loc = loc
        self.x = loc[0]
        self.y = loc[1]
    def get_image(self):
        return self.image
    def get_width(self):
        return self.width
    def get_height(self):
        return self.height
    def show_on(self, canvas: pygame.Surface):
        canvas.blit(self.image, (self.x, self.y))
    def scale_img(self, scale_amt: float):
        self.image = pygame.transform.scale(self.image, (int(self.width*scale_amt), int(self.height*scale_amt)))
        self.width = self.image.get_width()
        self.height = self.image.get_height()
    def rotate_img(self, angle: float):
        '''
        Note that angle is measured in degrees (counterclockwise)
        '''
        self.image = pygame.transform.rotate(self.image, angle)
        self.width = self.image.get_width()
        self.height = self.image.get_height()
    def __eq__(self, other):
        return self.width==other.width and self.height==other.height and self.loc==other.loc
    def __str__(self):
        return f"Image from file {self.image_fname} with dimensions {self.width}x{self.height} at {self.loc}"
    def __repr__(self):
        return f"my_sprite(image_fname={self.image_fname}, height={self.height}, width={self.width}, loc={self.loc})"

if __name__ == "__main__":
    test_sprite = my_sprite("Project_4/images/vehicles/green_car.png", (10, 20))
    print(test_sprite)