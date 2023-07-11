class Block_ABC:

    def __init__(self):
        self.xpos = 0
        self.ypos = 0
        self.part_coords = [[]]
        self.orientations = [[[]]]

    def get_coords(self):
        return [self.xpos, self.ypos]

    def get_adjacent_coords(self):
        return self.part_coords

    def fall(self):
        self.ypos += 1

    def take_action(self, action):
        # takes the action based on the input
        if action == "left":
            self.xpos -= 1

        elif action == "right":
            self.xpos += 1

        elif action == "w":
            self.part_coords = self.orientations[0]

        elif action == "a":
            self.part_coords = self.orientations[1]

        elif action == "s":
            self.part_coords = self.orientations[2]

        elif action == "d":
            self.part_coords = self.orientations[3]
