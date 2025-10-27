import os
os.environ['PYGAME_HIDE_SUPPORT_PROMPT'] = "hide"
import pygame
from my_sprite import my_sprite
from colliding_object import colliding_object
from moving_vehicle import moving_vehicle
from four_direction_mover import FourDirectionMover

def main():
    pygame.init()
    screen = pygame.display.set_mode((700, 800))
    pygame.display.set_caption("Very janky racing game")
    clock = pygame.time.Clock()

    real_tree_grid_locs = set([(0,2), (0,3), (3,2), (3,3)])
    
    # initialize static non-colliding objects
    start_line = my_sprite("160/Project_3/images/decor/Start.png", (0, 0))
    start_line.scale_img(0.5)
    finish_line = my_sprite("160/Project_3/images/decor/Finish.png", (0, 600))
    finish_line.scale_img(0.5)
    # fake trees because I feel like it haha
    fake_trees = [my_sprite("160/Project_3/images/decor/Tree.png", (200*i, 200*j)) for i in range(4) for j in range(1,4) if (i,j) not in real_tree_grid_locs]
    for fake_tree in fake_trees:
        fake_tree.scale_img(0.5)
        fake_tree.rotate_img(180)

    # initialize obstacles as trees
    trees = [colliding_object("160/Project_3/images/decor/Tree.png", (200*i, 200*j)) for (i,j) in real_tree_grid_locs]
    for tree in trees:
        tree.scale_img(0.5)

    # initialize movable vehicles
    vehicle_1 = moving_vehicle("160/Project_3/images/vehicles/green_car.png", (0, 0))
    vehicle_2 = moving_vehicle("160/Project_3/images/vehicles/red_car.png", (600, 0))
    vehicles = [vehicle_1, vehicle_2]
    for vehicle in vehicles:
        vehicle.rotate_img(270)

    #initialize movers
    vehicle_1_mover = FourDirectionMover(vehicle_1, up_key="w", down_key="s", left_key="a", right_key="d", speed=1)
    vehicle_2_mover = FourDirectionMover(vehicle_2, up_key="i", down_key="k", left_key="j", right_key="l", speed=1)
    movers = [vehicle_1_mover, vehicle_2_mover]
    vehicle_mover_pairing = {vehicle_1_mover: vehicle_1, vehicle_2_mover: vehicle_2}

    running = True
    while running:
        screen.fill((255, 255, 255)) # white bg
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        start_line.show_on(screen)
        finish_line.show_on(screen)
        for mover in movers:
            vehicle=vehicle_mover_pairing[mover]
            is_colliding=False
            # collision check
            for tree in trees:
                if vehicle.is_colliding_with(tree):
                    # print(f"Colliding with tree at {tree.x}, {tree.y}")
                    is_colliding=True
            if not is_colliding:
                mover.update()
        for tree in trees:
            tree.show_on(screen)
        for fake_tree in fake_trees:
            fake_tree.show_on(screen)
        for vehicle in vehicles:
            vehicle.show_on(screen)

        pygame.display.flip()
        clock.tick(60)

if __name__ == "__main__":
    main()