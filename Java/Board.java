import static java.lang.System.*;

import Blocks.BlockABC;

public class Board {
    /** Dimension variables for the board. */
    private int boardWidth, boardHeight;
    /** Contains the special characters that are used for the corners of the board. */
    private char leftUpperCorner = '┌', leftLowerCorner = '└', rightUpperCorner = '┐', rightLowerCorner = '┘';
    private int[][] board;
    /** Array for the blocks currently on the screen */
    private BlockABC[] blocks;

    /**
     * Makes a new board with the given paramers
     *
     * @param blocks - the array of blocks to be drawn
     * @param dimensions - the size of the board, {height, width}
     */
    public Board(BlockABC[] blocks, int[] dimensions) {
        this.blocks = blocks;
        this.boardWidth = dimensions[0];
        this.boardHeight = dimensions[1];
        this.board = new int[boardHeight][boardWidth];
        zeroBoard();
    }

    /* Returns the board to a state of zero */
    public void zeroBoard() {
        // clears the board
        for (int rows = 0; rows < boardHeight; rows++) {
            for (int columns = 0; columns < boardWidth; columns++) {
                board[rows][columns] = 0;
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
            int[] line = board[rows];
            for (int columns = 0; columns < line.length - 1; columns++) {
                // checks to see if the space is occupied, if it is, place a '[]' in the space
                if (line[columns] == 0) {
                    System.out.print("  ");
                } else if (line[columns] == 1) {
                    System.out.print("[]");
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
        // loops through the array of blocks
        for (BlockABC block : blocks) {
            // gets the coordinates of the current block
            int[] coords = block.getCoords();
            // checks if the block exists in the row or column
            if (coords[0] == row && coords[1] == column) {
                mapAdjacentPieces(block, coords);
            }
        }
    }

    /**
     * Maps the pieces adjacent to the specified block at its current position.
     * 
     * @param block - the block to map
     * @param blockCoords - the array of coordinates for the specified block
     */
    public void mapAdjacentPieces(BlockABC block, int[] blockCoords) {
        int[][] partCoords = block.getAdjacentCoords();
        for (int[] part: partCoords) {
            board[blockCoords[0] + part[0]][blockCoords[1] + part[1]] = 1;
        }
    }
}
