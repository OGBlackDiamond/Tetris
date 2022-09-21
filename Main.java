/* 
Tetris game in console
author - Caden Feller
*/
import static java.lang.System.*;

import KeyListener.*;
import Blocks.*;
import Sub.*;

public class Main {
    public static long tick = 0L;
    public static int width = 10; 
    public static int height = 25;
    public static char left_upper_corner = '┌';
    public static char left_lower_corner = '└';
    public static char right_upper_corner = '┐';
    public static char right_lower_corner = '┘';
    public static Object[] blocks = {new Lblock()};
    public static int[][] board = {
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 4
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 9
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 10
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 11
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 12
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 13
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 14
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 15
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 16
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 17
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 18
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 19
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // 20
                                };
    
    public static void main (String [] args) throws Exception {
        Keylistener.keylisten();
        while (true) {
            tick++;
            if (Lblock.change_orentation()) {
                draw_board(10, 20);
                update_board();
            }
            if (tick == 1999999999L) {
                gameloop();
            } else if (tick > 1999999999L) {
                tick = 0;
            }
        }
    }

    public static void update_board () {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        for (int i = 0; i < blocks.length; i++) {
            int[][] coords = ((Lblock) blocks[i]).get_coords();
            for (int j = 0; j < 4; j++) {
                int[] set = coords[j];
                board[set[1]][set[0]] = 1;
            }
        }
    }

    // board is 10 by 25
    public static void draw_board (int w, int layers) {
        // prints the top layer
        out.print(left_upper_corner); for (int i = 0; i <= w - 2; i++) { out.print("--"); } out.println(right_upper_corner);
        // prints middle layers
        for (int i = 0; i < layers; i++) {
            out.print("|");
            int[] line = board[i];
            for (int k = 0; k < line.length - 1; k++) {
                if (line[k] == 0) {
                    System.out.print("  ");
                } else if (line[k] == 1) {
                    System.out.print("[]");
                }
            }
            out.println("|");
        }
        // prints bottom layer
        out.print(left_lower_corner); for (int i = 0; i <= w - 2; i++) { out.print("--"); } out.println(right_lower_corner);
    }


    public static void gameloop () throws Exception {
        draw_board(10, 20);
        update_board();
        for (int i = 0; i < blocks.length; i++) {
            if (((Lblock) blocks[i]).relativey >= (18 - 2)) {
                out.println(((Lblock) blocks[i]).get_dimensions('y'));
                ((Lblock) blocks[i]).deactivate();
            }
            if (((Lblock) blocks[i]).is_active() == true) {
                ((Lblock) blocks[i]).fall(); 
            }
        }
    }

   
}


