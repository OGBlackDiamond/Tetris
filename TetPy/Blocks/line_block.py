from Blocks.block_abc import Block_ABC

class Line_Block(Block_ABC):
    
    def __init__(self, board_width, board_height):
        super(Line_Block, self).__init__(board_width, board_height)
        self.xpos = 3
        self.ypos = 0
        self.orientations = [
            # [][][][]
            [
                [0, 0],
                [1, 0],
                [2, 0],
                [3, 0]
            ],
            # []
            # []
            # []
            # []
            [
                [0, 0],
                [0, 1],
                [0, 2],
                [0, 3]

            ]
        ]
        self.orientations.append(self.orientations[0])
        self.orientations.append(self.orientations[1])
        self.part_coords = self.orientations[0]