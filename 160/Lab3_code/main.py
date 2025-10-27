import pygame
from Vehicle import Vehicle
from KeyboardMover import KeyboardMover

def main():
    pygame.init()
    canvas      = pygame.display.set_mode((1240, 820))
    car1        = Vehicle("Lab3_code/Images/orange_truck.png")
    tree        = Vehicle("Lab3_code/Images/tree.png", locx=1000)
    keyd        = KeyboardMover(car1, "d")
    keyl        = KeyboardMover(car1, "l")
    keepRunning = True
    while (keepRunning):
        canvas.blit(car1.getImage(),car1.getPosition())
        canvas.blit(tree.getImage(),tree.getPosition())
        pygame.display.flip()
        if car1.isCollidingWith(tree):
            print("the car and the tree have collided!!!")
        d_key_continue=keyd.processEvent()
        l_key_continue=keyl.processEvent()
        keepRunning = d_key_continue and l_key_continue


if __name__ == "__main__":
    main()