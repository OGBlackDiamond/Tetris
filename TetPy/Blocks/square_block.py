from Blocks.block_abc import Block_ABC

class Square_Block(Block_ABC):
    
    def __init__(self, board_width, board_height):
        super(Square_Block, self).__init__(board_width, board_height)
        self.xpos = 3
        self.ypos = 0
        self.orientations = [
            # [][]
            # [][]
            [
                [0, 0],
                [1, 0],
                [0, 1],
                [1, 1]
            ],
        ]
        self.orientations.append(self.orientations[0])
        self.orientations.append(self.orientations[0])
        self.orientations.append(self.orientations[0])
        self.part_coords = self.orientations[0]