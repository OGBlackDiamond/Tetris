import KeyListener.*;

/** Starts the game and handles the gameloop */
public class TetrisRunner {
    public static int lastKey;
    public static void main (String [] args) throws Exception {
        Main tetris = new Main();
        Keylistener.keylisten();
        while (true) {
            if (lastKey != Keylistener.key) {
                lastKey = Keylistener.key;
                tetris.gameloop();
            }
            if (tetris.tick()) {
                tetris.gameloop();
            }
        }
    }
}
