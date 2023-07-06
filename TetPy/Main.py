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
        self.block = Lblock()
        self.board = board.Board(10, 25, self.block)

        self.timer = 0
        self.ticker = False


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



    def get_key_pressed(self):
        # doing all of the player input handling
        keys_pressed = pygame.key.get_pressed()
        if keys_pressed[pygame.K_LEFT]:
            self.block.xpos -= 1
            self.board.board_loop()
            self.ticker = True
        if keys_pressed[pygame.K_RIGHT]:
            self.block.xpos += 1
            self.board.board_loop()
            self.ticker = True
        if keys_pressed[pygame.K_w]:
            pass
        elif keys_pressed[pygame.K_a]:
            pass
        elif keys_pressed[pygame.K_s]:
            pass
        elif keys_pressed[pygame.K_d]:
            pass
        else:
            return


balls = Main()
while running:
    balls.main()