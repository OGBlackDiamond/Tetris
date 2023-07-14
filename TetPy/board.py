import random

from Blocks.line_block import Line_Block
from Blocks.lblock import Lblock
from Blocks.sblock import Sblock
from Blocks.square_block import Square_Block
from Blocks.tblock import Tblock

class Board:

    def __init__(self, width, height, block):
        # the board dimensions
        self.width = width
        self.height = height

        # populate the board with spaces
        self.board = []
        for i in range(height):
            self.board.append([])
            for j in range(width):
                self.board[i].append(Space())

        # keeps track of the player's score
        self.score = 0

        # special characters for the corners of the board
        self.left_upper_corner = '┌'
        self.left_lower_corner = '└'
        self.right_upper_corner = '┐'
        self.right_lower_corner = '┘'

        # variable to keep track of the block
        self.block = block

    # turns all of the spaces to not active, as long as they should be cleared
    def zero_board(self):
        for row in range(self.height):
            for column in range(self.width - 1):
                if self.board[row][column].should_clear:
                    self.board[row][column].is_active = False;

    # updates the positions of the block on the board
    def update_board(self):
        for row in range(self.height):
            for column in range(self.width - 1):
                self.map_pieces(row, column)

    # draws the board
    def draw_board(self):
        # print the player's score
        print(f"Score: {self.score}")
        # print the top layer
        print(f"{self.left_upper_corner}" + ("--" * (self.width - 1)) + f"{self.right_upper_corner}")
        # prints the middle layers
        for row in range(self.height):
            print("|", end="")
            spaces_filled = 0
            for column in range(self.width - 1):
                # if the selected space is occupied, draw a square, if it isn't, draw a space
                if self.board[row][column].is_active:
                    print("[]", end="")
                    spaces_filled += 1
                else:
                    print("  ", end="")
            print("|")
            # if a row is full, clear it and increment the score
            if spaces_filled == self.width - 1:
                self.clear_row(row)
                self.score += 1
        # print the top layer
        print(f"{self.left_lower_corner}" + ("--" * (self.width - 1)) + f"{self.right_lower_corner}")

    # maps the block to be drawn
    def map_pieces(self, row, column):
        # gets coordinates from the current block
        coords = self.block.get_coords()
        # checks if the block exists in the row or column
        if coords[0] == column and coords[1] == row:
            self.map_adjacent_pieces(coords, False)

    # maps the pieces that make up a block to be drawn
    def map_adjacent_pieces(self, block_coords, deactivate):
        # gets the coordinate for the relative position of the block
        part_coords = self.block.get_adjacent_coords()
        # loops through the coordinates on each piece
        for part in part_coords:
            # sets the spaces where pieces of a block reside to active
            self.board[part[1] + block_coords[1]][part[0] + block_coords[0]].is_active = True
            if deactivate:
                # sets the spaces where pieces of a block reside to not be cleared
                self.board[part[1] + block_coords[1]][part[0] + block_coords[0]].should_clear = False

    # turns the current block and creates a new one
    def deactivate_block(self):
        # sets all of the spaces where a block resides to not be cleared
        self.map_adjacent_pieces(self.block.get_coords(), True)
        # replaces the current block value with a new one
        self.block = self.generate_new_block()
        # returns the new block object to be used by main
        return self.block

    # returns a new random block object
    def generate_new_block(self):
        gen_block = random.randint(0, 4)

        if gen_block == 0:
            return Lblock(self.width, self.height)
        elif gen_block == 1:
            return Line_Block(self.width, self.height)
        elif gen_block == 2:
            return Sblock(self.width, self.height)
        elif gen_block == 3:
            return Square_Block(self.width, self.height)
        elif gen_block == 4:
            return Tblock(self.width, self.height)

    # returns true if the space at the given coordinates are active and not part of a block
    def get_space(self, xcoord, ycoord):
        return self.board[ycoord][xcoord].is_active and not self.board[ycoord][xcoord].should_clear

    # removes the indicated row
    def clear_row(self, row):
        # clears the row back to its default state
        for space in self.board[row]:
            space.is_active = False
            space.should_clear = True

        # saves the row, but takes it out of the array
        new_row = self.board.pop(row)
        self.board.insert(0, new_row)

    # all of the board-related function calls
    def board_loop(self):
        self.draw_board()
        self.zero_board()
        self.update_board()

# This class contains the two attribues required to make up an effective space
class Space:
    def __init__(self):
        self.is_active = False
        self.should_clear = True