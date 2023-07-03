package TetJava;

import TetJava.KeyListener.Keylistener;

/** Starts the game and handles the gameloop */
public class TetrisRunner {
    // main method
    public static void main (String [] args) throws Exception {
        Main tetris = new Main();
        Keylistener.keylisten(tetris);
        while (true) {
            // if the game calls for a tick, run the gameloop and allow a block to fall
            if (tetris.tick()) {
                tetris.fall();
                tetris.gameloop();
            }
        }
    }
}
