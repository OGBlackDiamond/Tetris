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
    /** Dimention variables for the board. */
    private int boardWidth, boardHeight;
    /** Contains the special characters that are used for the corners of the board. */
    private char left_upper_corner = '┌', left_lower_corner = '└', right_upper_corner = '┐', right_lower_corner = '┘';
    private Object[] blocks = {};
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
    public void gameloop () throws Exception {
        draw_board();
        update_board();
    }

    /** Handles all of the logic with the board. */
    private void update_board () {
        zeroBoard();
    }

    /** Draws the board */
    private void draw_board () {
        // prints the top layer
        out.print(left_upper_corner); for (int i = 0; i <= boardWidth - 2; i++) { out.print("--"); } out.println(right_upper_corner);
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
        out.print(left_lower_corner); for (int i = 0; i <= boardWidth - 2; i++) { out.print("--"); } out.println(right_lower_corner);
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

    /**
     * Handles all of the timing for how often the game should run
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


