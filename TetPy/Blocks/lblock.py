from Blocks.block_abc import Block_ABC

class Lblock(Block_ABC):
    
    def __init__(self, board_width, board_height):
        super(Lblock, self).__init__(board_width, board_height)
        self.xpos = 3
        self.ypos = 0
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
        self.part_coords = self.orientations[0]