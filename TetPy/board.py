import Blocks.block_abc
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
            for column in range(self.width - 1):
                # if the selected space is occupied, draw a square, if it isn't, draw a space
                if self.board[row][column].is_active:
                    print("[]", end="")
                else:
                    print("  ", end="")
            print("|")
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

    def board_loop(self):
        self.zero_board()
        self.update_board()
        self.draw_board()


class Space:
    def __init__(self):
        self.is_active = False
        self.should_clear = True