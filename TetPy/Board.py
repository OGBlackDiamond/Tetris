import Blocks.block
class Board:
    def __init__(self, width, height):
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
        self.block = Blocks.block.Block()

    def zero_board(self):
        for row in range(self.height):
            for column in range(self.width - 1):
                if self.board[row][column].should_clear:
                    self.board[row][column].is_active = False;


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





    



class Space:
    def __init__(self):
        self.is_active = False;
        self.should_clear = True;