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
        self.block = Lblock(10, 25)
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

        # runs 60 times every second
        self.always_loop()

        # ticks game every 1 second
        if self.timer == 60:
            self.tick_loop()
            self.timer = 0

        events = pygame.event.get()

        # handles the game events
        for event in events:
            # if a key is pressed, update the blocks position and allow it to move
            if event.type == pygame.KEYDOWN:
                self.key_press_loop()

            # kills the program if the pygame window closes
            if event.type == pygame.QUIT:
                return


    # will always be run 
    def always_loop(self):
        self.get_key_pressed()
 
    # will only run when the game ticks
    def tick_loop(self):
        # stuff to be run every tick
        self.board.board_loop()
        self.fall()

    # will run when a key is pressed
    def key_press_loop(self):
        # gets the current keypress
        self.get_key_pressed()
        # allows the block to take an action
        self.block.take_action(self.key_pressed)
        # runs a board loop to update the graphics with the new action
        self.board.board_loop()
        # clears the current keypress
        self.key_pressed = ""

    # get the key that has been pressed
    def get_key_pressed(self):
        # doing all of the player input handling
        keys_pressed = pygame.key.get_pressed()

        # handles all of the different keypresses
        if keys_pressed[pygame.K_LEFT]:
            self.key_pressed = "left"

        elif keys_pressed[pygame.K_RIGHT]:
            self.key_pressed = "right"

        if keys_pressed[pygame.K_w]:
            self.key_pressed = "w"

        elif keys_pressed[pygame.K_a]:
            self.key_pressed = "a"

        elif keys_pressed[pygame.K_s]:
            self.key_pressed = "s"

        elif keys_pressed[pygame.K_d]:
            self.key_pressed = "d"

    # checks if the block is allowed to fall, if it isn't, stop it and spawn a new one
    def fall(self):
        if (not self.block.fall(self.board)):
            self.block = self.board.deactivate_block()

balls = Main()
while running:
    balls.main()