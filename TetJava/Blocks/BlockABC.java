package TetJava.Blocks;

import TetJava.Board;
import TetJava.KeyListener.Keylistener;
/**
 * This class will be extended by multiple block classes and will act as a framework for all of the block types to run.
 * This does not have a constructor, as it is an abstract class.
 */
public class BlockABC {
    /** This variable contains a coordinate value for the block relative to the board */
    protected int xpos, ypos;

    /** This will contain the location of the parts reletive to the whole body of the block */
    protected int[][] partCoords;

    /** This will contain all possible coordinate values for the variable orientations of the block */
    protected int[][][] orientations;

    /** This variable will store the last key that was pressed on the keyboard */
    protected int keyPressed;

    /** This will contain the farthest right x coordinate reletive to the block */
    protected int farthestFromBlockx = 0;

    /** This will contain the farthest right y coordinate reletive to the block */
    protected int farthestFromBlocky = 0;

    /** This will hold the current board instance */
    protected Board board;

    public BlockABC(Board board) {
        this.board = board;
    }

    /** Returns the last key that was pressed */
    private void getKey() {
        keyPressed = Keylistener.key;
    }

    /** This will change the position of the current block */
    private void switchOrientation() {
        // changes the orientation
        partCoords = keyPressed < 4 ? orientations[keyPressed] : partCoords;
        updateFarthestFrom();
    }

    private void updateFarthestFrom() {
        // every time the orentation is changed, the farthest part from the block is calculated on the x axis
        farthestFromBlockx = 0;
        for (int[] part: partCoords) {
            if (part[1] > farthestFromBlockx) {
                farthestFromBlockx = part[1];
            }
        }

        // every time the orentation is changed, the farthest part from the block is calculated on the y axis
        farthestFromBlocky = 0;
        for (int[] part: partCoords) {
            if (part[0] > farthestFromBlocky) {
                farthestFromBlocky = part[0];
            }
        }
    }

    /**
     * Shifts the block in the specified direction (keyboard input)
     *
     * @param boardWidth - the width of the board
     */
    private void shift(int boardWidth) {
        // shifts the block in the specified direction
        switch (keyPressed) {
            case 4:
                xpos--;
                break;
            case 5:
                xpos++;
                break;
            case 6:
                System.out.println("Slammin!");
                slam();
        }

        // constantly shoves the piece back into the board if it is out of bounds
        while (xpos + farthestFromBlockx >= boardWidth - 1) {
            xpos--;
        }

        // constantly shoves the piece back into the board if it is out of bounds
        while (xpos < 0 ) {
            xpos++;
        }
    }

    /** 
     * Returns the current coordinate position of the top left position of the block
     *
     * @return coords - the coordinates of the block
     */
    public int[] getCoords() {
        return new int[] {ypos, xpos};
    }

    /**
     * Returns the coordinates of the adjacent pieces that make up the block reletive to the block's position
     *
     * @return partCoords - the array of coordinates for the specified orientation of the block
     */
    public int[][] getAdjacentCoords() {
        return partCoords;
    }

    /** Causes the block to fall */
    public Boolean fall() {
        // variable checking if the block has touched the bottom
        boolean isNotTouchingBottom = (ypos + farthestFromBlocky < board.boardHeight - 1);
        // variable checking if the block will touch a block in the next gameloop
        boolean willTouchBlock = false;

        // loops through each coordinate in the current orientation
        for (int[] part: partCoords) {
            // checks if there is a block under each part of the block
             willTouchBlock = board.getSpace(
                xpos + part[1], 
                // checks below the part if it is not at the bottom layer (it would throw an error if it tried to check for a spot that did not exist)
                (ypos + part[0] == 24 ? 24: ypos + part[0] + 1));
        }

        // if the part is not touching the bottom and will not touch a block in the next gameloop, allow it to move down
        if (isNotTouchingBottom && !willTouchBlock) {
            ypos++;
        } else {
            return false;
        }
        return true;
    }

    public void slam() {
        while (fall()) {
            updateFarthestFrom();
        };
    }

    /** The main blockloop where everything is run */
    public void blockLoop(int boardHeight, int boardWidth) {
        getKey();
        switchOrientation();
        shift(boardWidth);
        // set the key to a value that is not being use, so the block does not continue to repeat the same action
        Keylistener.key = 7;
    }
}
