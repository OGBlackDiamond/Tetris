class Board:
    def __init__(self, width, height):
        # populate the board with spaces
        self.board = []
        for i in range(height):
            for j in range(width):
                self.board[i][j] = Space()


class Space:
    def __init__(self):
        self.is_active = False;
        self.should_clear = True;