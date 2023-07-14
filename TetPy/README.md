# Tetris - in Python
This a command line interpretation of Tetris running on Python.
<br /> <br />
To install all dependencies (at the time of writing this, there is only one), run the following command: <br />
```
pip install -r TetPy/dependencies.txt
```
This version uses the Pygame library to continuously get keyboard input while the game is running.

## How it works - Disclaimer
This version largely mirrors the original implementation (TetJava) in the way that it runs, making use of Python's Object Orientability to mirror the Java implimentation almost 1 for 1. The way I did it in Java makes the most sense to me, so the only differences you will see are minor conviniences that python allows for, (i.e. the list.pop() method).

## How it works - Overview
Because Java is an object oriented language, I made heavy use of classes when writing this version. There was a main class that handled the gameloop and everything else as a whole. I used a class to act as a template for all block classes to inherit from, this allowed me to use a range of different blocks without having to copy and pase the same methods into each class. The board class handles all of the action requiring the board, this includes drawing, updating, removing, shifting, etc.

## How it works - Deep Dive
### The board:
The board is represented as a 2D array, where the first dimension is the amount of rows on the board, and the second dimension is the amount of columns in each row. By stacking each array on top of eachother, we can end up with something that can be used as a board. Each space on the board can also be represented by a coordinate point, where the 'x' value is a position in the second dimension, and the 'y' value is a position in the first. Every space on the board is represented by a 'Space' class. A space class only has two attributes, both of which are boolean values that represent whether something should happen to the space. These attributes are 'is_active', whether the space is occupied by a block, and 'should_clear', whether the space should be cleared in the next gameloop.
<br /> <br />
During each gameloop the board will look at the 'block' object that it was passed by the 'Main' class. It checks where the block exists on the board, and then marks each space where a piece of a block resides, as active. In order to draw this, the board class simply checks for whether a space is occupied or not (using the '.is_active' attribute). If the space is active, draw a box ([]) there. If it isn't, draw a space (  ) there. After it has been drawn, it will check if the block will hit another block in the next gameloop, or is touching the ground. If either of these are true, a block will be deactivated, meaning that it will no longer be moved. This is done by setting all of the spaces where the block resides to 'should_clear = False'. This means that those blocks will not be cleared, whenever the slate is wiped clean. (I had to do this because whenever a block is moving, the spaces where is was need to be cleared so that it does not leave a trail where it was. You can think of each space as a light switch. It can be turned on and off depending on what needs to happen. Whenever a gameloop is called, the 'zero_board()' method will come and flick all of the switches to off, and the board will flick certain ones back on where a block is. The 'should_clear' attribute essentially acts as a little piece of tape holding the switch in the up position. When 'should_clear' is true, the 'zero_board()' method will not turn that switch off.) By doing this, the pieces where the block is, will stay there, even if the block is no longer there. This allows me to destroy the current block object, and create a new one to progress the game. This method is also helpful because the parts of a block are no longer attached to eachother, or the block, in any way. This allows me to be able to remove layers very easily because I don't have to worry about slicing a block in half.
<br /> <br />
Whenever a row needs to be cleared, I simply use Python's built in 'pop()' method to remove the given row from the board, clear it to it's default state, and insert it at the top of the board. Its quite a simple piece of code:
```
    def clear_row(self, row):
        # clears the row back to its default state
        for space in self.board[row]:
            space.is_active = False
            space.should_clear = True

        # saves the row, but takes it out of the array
        new_row = self.board.pop(row)
        self.board.insert(0, new_row)
```
The method takes in an integer, representing the row to be cleared. A loop goes through all of the spaces on that row, and set them to their defualt state. I then pop the row out of the list, and instert it at index 0, (the top layer on the board).

### The blocks:
The block is represented by a singular set of coordinate points, which is the top left corner of the block, and an array of coordinate points that represent the positions of the individual pieces of the blocks that make up the block. For example:
```
[
    [0, 0],
    [1, 0],
    [2, 0],
    [0, 1]
]
```
Would equate to :
```
[][][]
[]
```
These coordinate points are defined in each blocks respective class. Everything else (the logic, movement, rotation, etc.) is handled in the abstract class (Python doesn't have abstract classes so it's not actually abstract, it just can't be instantiated without breaking the game) from which each block class inherits. The abstract block class also handles collision detection, which was a little bit of a pain to have to code, because I need to check each piece of the block and see if it collides with something.
<br /> <br />
Each block has a maximum of 4 possible positions, one for each rotational state. All of these are stored in a 3 dimensional array defined in the constructor. The first dimension has 4 values, one containing each rotational state. The second dimension contains each coordinate point for each piece (like in the code snippet above) and the 3rd dimension is the x or y coordinate in the set of points. 