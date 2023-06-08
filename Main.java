/* 
Tetris game in console
author - Caden Feller
*/
import static java.lang.System.*;

import Blocks.*;
import Sub.*;

public class Main {
    private long tick = 0L;
    private int boardWidth; 
    private int boardHeight;
    private char left_upper_corner = '┌';
    private char left_lower_corner = '└';
    private char right_upper_corner = '┐';
    private char right_lower_corner = '┘';
    private Object[] blocks = {};
    private int[][] board;

    public Main(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.blocks = new int[height][width];
        zeroBoard();
    }

    private void update_board () {
        zeroBoard();
    }

    // board is 10 by 25
    private void draw_board () {
        // prints the top layer
        out.print(left_upper_corner); for (int i = 0; i <= boardWidth - 2; i++) { out.print("--"); } out.println(right_upper_corner);
        // prints middle layers
        for (int rows = 0; rows < boardHeight; rows++) {
            out.print("|");
            int[] line = board[rows];
            for (int columns = 0; columns < line.length - 1; columns++) {
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


    public void gameloop () throws Exception {
        draw_board();
        update_board();
    }

    /* Returns the board to a state of zero */
    private void zeroBoard() {
        // clears the board
        for (int rows = 0; rows < boardHeight; rows++) {
            for (int columns = 0; columns < boardWidth; columns++) {
                out.print(board);
                board[columns][rows] = 0;
            }
        }
    }

    /*
     * 
     */
    private boolean getTick() {
        if (tick == 1999999999L) {
            return true;
        } else if (tick > 1999999999L) {
            tick = 0;
        }
        return false;
    }

    public boolean tick() {
        tick++;
        return getTick();
    }
}


