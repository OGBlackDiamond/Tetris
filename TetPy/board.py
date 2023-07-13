import Blocks

import random

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

    def zero_board(self):
        for row in range(self.height):
            for column in range(self.width - 1):
                if self.board[row][column].should_clear:
                    self.board[row][column].is_active = False;

    def update_board(self):
        for row in range(self.height):
            for column in range(self.width - 1):
                self.map_pieces(row, column)


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
            if spaces_filled == self.width - 1:
                self.clear_row(row)
                self.score += 1
        # print the top layer
        print(f"{self.left_lower_corner}" + ("--" * (self.width - 1)) + f"{self.right_lower_corner}")


    def map_pieces(self, row, column):
        # gets coordinates from the current block
        coords = self.block.get_coords()
        # checks if the block exists in the row or column
        if coords[0] == column and coords[1] == row:
            self.map_adjacent_pieces(coords, False)

    def map_adjacent_pieces(self, block_coords, deactivate):
        # gets the coordinate for the relative position of the block
        part_coords = self.block.get_adjacent_coords()
        # loops through the coordinates on each piece
        for part in part_coords:
            self.board[part[1] + block_coords[1]][part[0] + block_coords[0]].is_active = True
            if deactivate:
                self.board[part[1] + block_coords[1]][part[0] + block_coords[0]].should_clear = False

    def deactivate_block(self):
        self.map_adjacent_pieces(self.block.get_coords(), True)
        self.block = self.generate_new_block()
        return self.block

    def generate_new_block(self):
        gen_block = random.randint(0, 0)

        if gen_block == 0:
            return Blocks.lblock.Lblock(self.width, self.height)

    def get_space(self, xcoord, ycoord):
        return self.board[ycoord][xcoord].is_active and not self.board[ycoord][xcoord].should_clear
    
    def clear_row(self, row):
        for space in self.board[row]:
            space.is_active = False
            space.should_clear = True

        # saves the row, but takes it out of the array
        new_row = self.board.pop(row)
        self.board.insert(0, new_row)

    def board_loop(self):
        self.draw_board()
        self.zero_board()
        self.update_board()


class Space:
    def __init__(self):
        self.is_active = False
        self.should_clear = True