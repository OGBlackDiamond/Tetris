class Block:

    def __init__(self):
        self.xpos = 0
        self.ypos = 0
        self.part_coords = [[]]
        self.orientations = [[[]]]

    def get_coords(self):
        return [self.xpos, self.ypos]
    
    def get_adjacent_coords(self):
        return self.part_coords