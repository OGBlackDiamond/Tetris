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

    /* Returns the board to a state of zero */
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
        for (int rows = 0; rows < boardHeight; rows++) {
            for (int columns = 0; columns < boardWidth; columns++) {
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
            for (int columns = 0; columns < boardWidth - 1; columns++) {
                // checks to see if the space is occupied, if it is, place a '[]' in the space
                if (board[rows][columns].isActive) {
                    System.out.print("[]");
                } else {
                    System.out.print("  ");
                }
            }
            out.println("|");
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
        int[][] partCoords = block.getAdjacentCoords();
        for (int[] part: partCoords) {
            board[blockCoords[0] + part[0]][blockCoords[1] + part[1]].isActive = true;
            if (deactivate) {
                board[blockCoords[0] + part[0]][blockCoords[1] + part[1]].shouldClear = false;
            }
        }
    }

    public BlockABC deactivateBlock() {
        mapAdjacentPieces(block, block.getCoords(), true);
        block = new Lblock();
        return block;
    }
}


class Space {
    public boolean isActive;
    public boolean shouldClear;

    public Space () {
        this(false, true);
    }

    public Space (boolean isActive, boolean shouldClear) {
        this.isActive = isActive;
        this.shouldClear = shouldClear;
    }
}