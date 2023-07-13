class Block_ABC:

    def __init__(self, board_width, board_height):
        self.xpos = 0
        self.ypos = 0
        self.part_coords = [[]]
        self.orientations = [[[]]]

        self.board_width = board_width
        self.board_height = board_height

        self.farthest_from_block_x = 0
        self.farthest_from_block_y = 0

    def get_coords(self):
        return [self.xpos, self.ypos]

    def get_adjacent_coords(self):
        return self.part_coords

    def take_action(self, action):
        # takes the action based on the input
        if action == "left":
            self.move_left()

        elif action == "right":
            self.move_right()

        elif action == "w":
            self.part_coords = self.orientations[0]

        elif action == "a":
            self.part_coords = self.orientations[1]

        elif action == "s":
            self.part_coords = self.orientations[2]

        elif action == "d":
            self.part_coords = self.orientations[3]

        # every time the orentation is changed, the farthest part from the block is calculated on the x axis
        self.farthest_from_block_x = 0;
        for part in self. part_coords:
            if part[0] > self.farthest_from_block_x:
                self.farthest_from_block_x = part[0]

        # every time the orentation is changed, the farthest part from the block is calculated on the y axis
        self.farthest_from_block_y = 0;
        for part in self.part_coords:
            if part[1] > self.farthest_from_block_y:
                self.farthest_from_block_y = part[1];

    def fall(self, board):
        # variable checking if the block has touched the bottom
        is_not_touching_bottom = (self.ypos + self.farthest_from_block_y < self.board_height - 1);
        # variable to check if the block will touch another block in the next loop
        #will_touch_block = False

        # loops through each coordinate in the current orientation
        for part in self.part_coords:

            y_space = 0
            # checks below the part if it is not at the bottom layer (it would throw an error if it tried to check for a spot that did not exist)
            if self.ypos + part[1] == 24:
                y_space = 24
            else:
                y_space = self.ypos + part[1] + 1


            # checks if there is a block under each part of the block
            will_touch_block = board.get_space(
                self.xpos + part[0],
                y_space
            )

        # if the part is not touching the bottom and will not touch a block in the next gameloop, allow it to move down
        if is_not_touching_bottom and not will_touch_block:
            self.ypos += 1
            return True;
        else:
            return False

    def move_left(self):
        self.xpos -= 1

        if self.xpos < 0:
            self.xpos += 1

    def move_right(self):
        self.xpos += 1

        for piece in self.part_coords:
            if (self.xpos + piece[0]) >= self.board_width - 1:
                self.xpos -= 1
