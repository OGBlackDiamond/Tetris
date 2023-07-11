import board
from Blocks.lblock import Lblock

import pygame

# gameloop variables
TPS = 60
running = True

# pygame window
WIN = pygame.display.set_mode((1, 1))
pygame.display.set_caption("Tetris Window")

class Main:
    def __init__(self):
        # makes a new block
        self.block = Lblock()
        # makes the board
        self.board = board.Board(10, 25, self.block)

        # current timer variable
        self.timer = 0

        # the last key that was pressed
        self.key_pressed = ""


    def main(self):
        # game loop, with another counter
        clock = pygame.time.Clock()
        clock.tick(TPS)
        self.timer += 1

        self.always_loop()

        if self.timer == 60:
            self.tick_loop()
            self.timer = 0

        # handles exiting the game
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                return

    # will always be run 
    def always_loop(self):
        self.get_key_pressed()

    # will only run when the game ticks
    def tick_loop(self):
        # stuff to be run every tick
        self.board.board_loop()
        self.block.take_action(self.key_pressed)
        self.key_pressed = ""


    def get_key_pressed(self):
        # doing all of the player input handling
        keys_pressed = pygame.key.get_pressed()

        # handles all of the different keypresses
        if keys_pressed[pygame.K_LEFT]:
            self.key_pressed = "left"

        if keys_pressed[pygame.K_RIGHT]:
            self.key_pressed = "right"

        if keys_pressed[pygame.K_w]:
            self.key_pressed = "w"

        elif keys_pressed[pygame.K_a]:
            self.key_pressed = "a"

        elif keys_pressed[pygame.K_s]:
            self.key_pressed = "s"

        elif keys_pressed[pygame.K_d]:
            self.key_pressed = "d"

        else:
            return

        self.board.board_loop()


balls = Main()
while running:
    balls.main()