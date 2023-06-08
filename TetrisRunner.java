import KeyListener.*;

/** Starts the game and handles the gameloop */
public class TetrisRunner {
    public static void main (String [] args) throws Exception {
        Main tetris = new Main(10, 25);
        Keylistener.keylisten();
        while (true) {
            if (tetris.tick()) {
                tetris.gameloop();
            }
        }
    }
}
