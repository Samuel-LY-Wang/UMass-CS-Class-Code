import pygame
import Vehicle

def main():
    pygame.init()
    canvas      = pygame.display.set_mode((1240, 820))
    car1        = Vehicle.Vehicle("Lab2_code/Images/orange_truck.png", width=186, height=76)
    tree        = Vehicle.Vehicle("Lab2_code/Images/tree.png", height=186, width=214, locx=1054)
    keepRunning = True
    hasCollided = False
    while (keepRunning):
        canvas.blit(car1.getImage(),car1.getPosition())
        canvas.blit(tree.getImage(),tree.getPosition())
        pygame.display.flip()
        if not hasCollided:
            pressedKeys =  pygame.key.get_pressed()
            if pressedKeys[pygame.K_d]:
                car1.setPosition(car1.getX() + 1, car1.getY())
                hasCollided=car1.isCollidingWith(tree)
            if hasCollided:
                print("Car and tree have collided!")
        for event in pygame.event.get():
            if (event.type == pygame.QUIT):
                keepRunning = False


if __name__ == "__main__":
    main()