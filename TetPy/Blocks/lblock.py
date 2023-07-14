from Blocks.block_abc import Block_ABC

"""
Lblock can be printed in 4 ways:

[][][]
[]

[][]
  []
  []

    []
[][][]

[]
[]
[][]
"""

class Lblock(Block_ABC):

    def __init__(self, board_width, board_height):
        # invokes the constructor of the abstract class to define the board width and height
        super(Lblock, self).__init__(board_width, board_height)

        # defines the position of the block
        self.xpos = 3
        self.ypos = 0

        # defines all possible positions the block can be in
        self.orientations = [
            # [][][]
            # []
            [
                [0, 0],
                [1, 0],
                [2, 0],
                [0, 1]
            ],
            # [][]
            #   []
            #   []
            [
                [0, 0],
                [1, 0],
                [1, 1],
                [1, 2]
            ],
            #     []
            # [][][]
            [
                [2, 0],
                [0, 1],
                [1, 1],
                [2, 1]
            ],
            # []
            # []
            # [][]
            [
                [0, 0],
                [0, 1],
                [0, 2],
                [1, 2]

            ]
        ]

        # sets the default orientation
        self.part_coords = self.orientations[0]