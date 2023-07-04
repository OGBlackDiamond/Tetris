import board
class Main:
    def __init__(self):
        self.board = board.Board(10, 25)
        self.board.draw_board()

balls = Main()