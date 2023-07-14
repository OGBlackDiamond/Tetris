# Tetris - in Java
This a command line interpretation of Tetris running on Java.<br />

It uses the KeyListener library to continuously get keyboard input while the game is running.<br />
YOU MUST HAVE THE Keylistener WINDOW AS YOUR ACTIVE TAB OR THE KEYBOARD INPUT WILL NOT WORK.

## How it works - Overview
Because Java is an object oriented language, I made heavy use of classes when writing this version. There was a main class that handled the gameloop and everything else as a whole. I used an abstract class to act as a template for all block classes to inherit from, this allowed me to use a range of different blocks, without having to change the variable type (the variable type is BlockABC, the abstract class. All classes that inherit from the abstract class automatically get applied this subtype, so they can all be assigned to a 'BlockABC' variable). The board class handles all of the action requiring the board, this includes drawing, updating, removing, shifting, etc. The runner simply starts the game.

## How it works - Deep Dive
### The board:
The board is represented as a 2D array, where the first dimension is the amount of rows on the board, and the second dimension is the amount of columns in each row. By stacking each array on top of eachother, we can end up with something that can be used as a board. Each space on the board can also be represented by a coordinate point, where the 'x' value is a position in the second dimension, and the 'y' value is a position in the first. Every space on the board is represented by a 'Space' class. A space class only has two attributes, both of which are boolean values that represent whether something should happen to the space. These attributes are 'isActive', whether the space is occupied by a block, and 'shouldClear', whether the space should be cleared in the next gameloop.
<br /> <br />
During each gameloop the board will look at the 'block' object that it was passed by the 'Main' class. It checks where the block exists on the board, and then marks each space where a piece of a block resides, as active. In order to draw this, the board class simply checks for whether a space is occupied or not (using the '.isActive' attribute). If the space is active, draw a box ([]) there. If it isn't, draw a space (  ) there. After it has been drawn, it will check if the block will hit another block in the next gameloop, or is touching the ground. If either of these are true, a block will be deactivated, meaning that it will no longer be moved. This is done by setting all of the spaces where the block resides to 'shouldClear = false;'. This means that those blocks will not be cleared, whenever the slate is wiped clean. (I had to do this because whenever a block is moving, the spaces where is was need to be cleared so that it does not leave a trail where it was. You can think of each space as a light switch. It can be turned on and off depending on what needs to happen. Whenever a gameloop is called, the 'zeroBoard()' method will come and flick all of the switches to off, and the board will flick certain ones back on where a block is. The 'shouldClear' attribute essentially acts as a little piece of tape holding the switch in the up position. When 'shouldClear' is true, the 'zeroBoard()' method will not turn that switch off.) By doing this, the pieces where the block is, will stay there, even if the block is no longer there. This allows me to destroy the current block object, and create a new one to progress the game. This method is also helpful because the parts of a block are no longer attached to eachother, or the block, in any way. This allows me to be able to remove layers very easily because I don't have to worry about slicing a block in half.
<br /> <br />
Whenever a row needs to be cleared, I use a simple loop and shift each layer (starting at the row that needs to me moved) down by one. This works simply by setting the row at the current index, to the value of the row preceeding the current index. Its quite a simple piece of code:
```
private void clearRow(int row) {
    // shifts the entire board down one level
    for (int i = 0; i < row; i++) {
        board[row - i] = board[row - i - 1];
    }
}
```
The method takes in an integer, representing the row to be cleared. A loop will work its way up to that number in increments of one. It will take the current row, minus the value of the loop control variable, and sets it equal to the current row minus the loop control varaible minus one. In this way, the code simply takes the input row, replaces it's value with that of the row above it. In the next loop run, it will do the same thing but with the row above the input row, then the one above that, and the one above that, and will repeat until it has reached the top.

### The blocks:
The block is represented by a singular set of coordinate points, which is the top left corner of the block, and an array of coordinate points that represent the positions of the individual pieces of the blocks that make up the block. For example:
```
{
    {0, 0},
    {1, 0},
    {2, 0},
    {0, 1}
}
```
Would equate to :
```
[][][]
[]
```
These coordinate points are defined in each blocks respective class. Everything else (the logic, movement, rotation, etc.) is handled in the abstract class from which each block class inherits. The abstract block class also handles collision detection, which was a little bit of a pain to have to code, because I need to check each piece of the block and see if it collides with something.
<br /> <br />
Each block has a maximum of 4 possible positions, one for each rotational state. All of these are stored in a 3 dimensional array defined in the constructor. The first dimension has 4 values, one containing each rotational state. The second dimension contains each coordinate point for each piece (like in the code snippet above) and the 3rd dimension is the x or y coordinate in the set of points. 