package TetJava;

import TetJava.KeyListener.Keylistener;

/** Starts the game and handles the gameloop */
public class TetrisRunner {
    public static void main (String [] args) throws Exception {
        Main tetris = new Main();
        Keylistener.keylisten(tetris);
        while (true) {
            if (tetris.tick()) {
                tetris.gameloop();
                tetris.fall();
            }
        }
    }
}
