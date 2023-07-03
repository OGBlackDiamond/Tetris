package TetJava;

import static java.lang.System.*;

import TetJava.Blocks.BlockABC;
import TetJava.Blocks.Lblock;

public class Board {
    /** Dimension variables for the board. */
    public int boardWidth, boardHeight;
    /** Contains the special characters that are used for the corners of the board. */
    private char leftUpperCorner = '┌', leftLowerCorner = '└', rightUpperCorner = '┐', rightLowerCorner = '┘';
    /** The block currently on the screen */
    private BlockABC block;
    /** The arry that represents the board. */
    private Space[][] board;

    /**
     * Makes a new board with the given paramers
     *
     * @param blocks - the array of blocks to be drawn
     * @param dimensions - the size of the board, {height, width}
     */
    public Board(BlockABC block, int[] dimensions) {
        this.block = block;
        this.boardWidth = dimensions[0];
        this.boardHeight = dimensions[1];
        this.board = new Space[boardHeight][boardWidth];
        zeroBoard(true);
    }

    /**
     * Returns the board to a state of zero.
     * If startup is true, it will fill each space with a new Space object.
     * 
     * @param isStartup - whether or not the array needs to be filled with new Space objects
     */
    public void zeroBoard(boolean isStartup) {
        // clears the board
        for (int rows = 0; rows < boardHeight; rows++) {
            for (int columns = 0; columns < boardWidth; columns++) {
                // will initialize the array with the Space objects
                if (isStartup) {
                    board[rows][columns] = new Space();
                } else {
                    // checks if the current space should be cleared or not
                    if (board[rows][columns].shouldClear) {
                        board[rows][columns].isActive = false;
                    }
                }
            }
        }
    }

    /** Handles all of the logic with the board. */
    public void updateBoard () {
        // loop through each individual space on the board
        for (int rows = 0; rows < boardHeight; rows++) {
            for (int columns = 0; columns < boardWidth; columns++) {
                // map each part of the board
                mapPieces(rows, columns);
            }
        }
    }

    /** Draws the board */
    public void drawBoard () {
        // prints the top layer
        out.print(leftUpperCorner); for (int i = 0; i <= boardWidth - 2; i++) { out.print("--"); } out.println(rightUpperCorner);
        // prints middle layers
        for (int rows = 0; rows < boardHeight; rows++) {
            out.print("|");
            // variable to keep track of how many filled spaces on the board exist
            int spacesFilled = 0;
            for (int columns = 0; columns < boardWidth - 1; columns++) {
                // checks to see if the space is occupied, if it is, place a '[]' in the space
                if (board[rows][columns].isActive) {
                    System.out.print("[]");
                    spacesFilled++;
                } else {
                    System.out.print("  ");
                }
            }
            out.println("|");
            // checks if a row has been completely filled, if it has, clear the row
            if (spacesFilled == 9) {
                clearRow(rows);
            }
        }
        // prints bottom layer
        out.print(leftLowerCorner); for (int i = 0; i <= boardWidth - 2; i++) { out.print("--"); } out.println(rightLowerCorner);
    }

    /** 
    * Checks if there is a piece of a block in the current space, if there is, mark the appropriate spaces as populated.
    *
    * @param row - the row to check
    * @param column - the column to check
    */
    public void mapPieces(int row, int column) {
        // gets the coordinates of the current block
        int[] coords = block.getCoords();
        // checks if the block exists in the row or column
        if (coords[0] == row && coords[1] == column) {
            mapAdjacentPieces(block, coords, false);
        }
    }

    /**
     * Maps the pieces adjacent to the specified block at its current position.
     * 
     * @param block - the block to map
     * @param blockCoords - the array of coordinates for the specified block
     */
    public void mapAdjacentPieces(BlockABC block, int[] blockCoords, boolean deactivate) {
        // gets the coordinates for the relative position of the block
        int[][] partCoords = block.getAdjacentCoords();
        // loops through the coordinates of each piece
        for (int[] part: partCoords) {
            // sets the coordinate position of each piece to true, so they will be drawn next frame
            board[blockCoords[0] + part[0]][blockCoords[1] + part[1]].isActive = true;
            if (deactivate) {
                // sets the 'shouldClear' value to true, so that the pieces will continue to be drawn, even after the block has been removed from memory
                board[blockCoords[0] + part[0]][blockCoords[1] + part[1]].shouldClear = false;
            }
        }
    }

    /**
     * Returns true if the space at the coordinate points selected are active and should not be cleared
     * 
     * @param xCoord - the x coordinate of the point to be checked
     * @param yCoord - the y coordinate of the point to be checked
     * @return boolean - whether or not the conditions of the selected point have been met
     */
    public boolean getSpace(int xCoord, int yCoord) {
        return board[yCoord][xCoord].isActive && !board[yCoord][xCoord].shouldClear;
    }

    /**
     * Deactivates the current block and replaces it with a new block.
     * This also ensures that the block pattern is left behind where the block was deactivated.
     * 
     * @return block - the new block
     */
    public BlockABC deactivateBlock() {
        mapAdjacentPieces(block, block.getCoords(), true);
        block = new Lblock();
        return block;
    }

    /**
     * Removes the indicated row and shifts the whole board down to fill the gap.
     * 
     * @param row - the row to be cleared
     */
    private void clearRow(int row) {
        // shifts the entire board down one level
        for (int i = 0; i < row; i++) {
            board[row - i] = board[row - i - 1];
        }

        // replaces the top layer with a new array of spaces
        board[0] = new Space[boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            board[0][i] = new Space();
        }
    }
}

/**
 * This class contains two variables that are used to represent a space on the board.
 */
class Space {
    /** Defines if the space is occupied. */
    public boolean isActive;
    /** Defines if the space should be cleared by the 'zeroBoard()' method. */
    public boolean shouldClear;

    /** Creates a new blank space. */
    public Space () {
        this(false, true);
    }

    /**
     * Creates a new space with specific values.
     * 
     * @param isActive - if the space should be drawn
     * @param shouldClear - if the space should be cleared
     */
    public Space (boolean isActive, boolean shouldClear) {
        this.isActive = isActive;
        this.shouldClear = shouldClear;
    }
}