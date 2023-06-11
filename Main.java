/* 
Tetris game in console
author - Caden Feller
*/
import static java.lang.System.*;

import Blocks.*;
import Sub.*;

public class Main {
    /** The current tick the game runs on. */
    private long tick = 0L;
    /** Dimension variables for the board. */
    private int boardWidth, boardHeight;
    /** Contains the special characters that are used for the corners of the board. */
    private char leftUpperCorner = '┌', leftLowerCorner = '└', rightUpperCorner = '┐', rightLowerCorner = '┘';
    private BlockABC[] blocks = {new Lblock()};
    private int[][] board;

    /** Starts the game with the board dimensions given. */
    public Main(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.board = new int[height][width];
        zeroBoard();
    }

    /** Default constructor to start the game with a board 10 x 25. */
    public Main(){
        this(10, 25);
    }

    /** Handles the speed at which the game runs. */
    public boolean tick() {
        tick++;
        return getTick();
    }

    /** Handles all of the action that happens whenever the gameloop runs. */
    public void gameloop() {
        blocksLoop();
        drawBoard();
        zeroBoard();
        updateBoard();
    }

    /* Returns the board to a state of zero */
    private void zeroBoard() {
        // clears the board
        for (int rows = 0; rows < boardHeight; rows++) {
            for (int columns = 0; columns < boardWidth; columns++) {
                board[rows][columns] = 0;
            }
        }
    }

    /** Handles all of the logic with the board. */
    private void updateBoard () {
        for (int rows = 0; rows < boardHeight; rows++) {
            for (int columns = 0; columns < boardWidth; columns++) {
                mapPieces(rows, columns);
            }
        }
    }

    /** Draws the board */
    private void drawBoard () {
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
     * Runs the loop for all the blocks present
     */
    private void blocksLoop() {
        for (BlockABC block: blocks) {
            block.blockLoop();
        }
    }

    /** 
    * Checks if there is a piece of a block in the current space, if there is, mark the appropriate spaces as populated.
    *
    * @param row - the row to check
    * @param column - the column to check
    */
    private void mapPieces(int row, int column) {
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
    private void mapAdjacentPieces(BlockABC block, int[] blockCoords) {
        int[][] partCoords = block.getAdjacentCoords();
        for (int[] part: partCoords) {
            board[blockCoords[0] + part[0]][blockCoords[1] + part[1]] = 1;
        }
    }

    /**
     * Handles all of the timing for how often the game should run
     * 
     * @return boolean - whether or not the gameloop should run
     */
    private boolean getTick() {
        if (tick == 1999999999L) {
            return true;
        } else if (tick > 1999999999L) {
            tick = 0;
        }
        return false;
    }
}
