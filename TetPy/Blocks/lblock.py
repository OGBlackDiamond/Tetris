from Blocks.block import Block

class Lblock(Block):
    
    def __init__(self):
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