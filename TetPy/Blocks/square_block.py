from Blocks.block_abc import Block_ABC

"""
 SquareBlock can be printed in 1 way:

 [][]
 [][]
"""

class Square_Block(Block_ABC):

    def __init__(self, board_width, board_height):
        # invokes the constructor of the abstract class to define the board width and height
        super(Square_Block, self).__init__(board_width, board_height)

        # defines the position of the block
        self.xpos = 3
        self.ypos = 0

        # defines all possible positions the block can be in
        self.orientations = [
            # [][]
            # [][]
            [
                [0, 0],
                [1, 0],
                [0, 1],
                [1, 1]
            ]
        ]

        # makes 3 duplicate values so an 'out of bounds' error is not caused if the user pressed a key to switch the position of the block
        self.orientations.append(self.orientations[0])
        self.orientations.append(self.orientations[0])
        self.orientations.append(self.orientations[0])

        # sets the default (and only) orientation
        self.part_coords = self.orientations[0]