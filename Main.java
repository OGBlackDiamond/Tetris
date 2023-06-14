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
    /** The array of blocks in the level */
    private BlockABC[] blocks = {new Lblock()};
    /** The board class */
    private Board board;

    /** Starts the game with the board dimensions given. */
    public Main(int width, int height) {
        this.board = new Board(blocks, new int[] {width, height});
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
        board.drawBoard();
        board.zeroBoard();
        board.updateBoard();
    }

    public void fall() {
        blocks[0].fall();
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
     * Handles all of the timing for how often the game should run
     * 
     * @return boolean - whether or not the gameloop should run
     */
    private boolean getTick() {
        if (tick == 999999999L) {
            return true;
        } else if (tick > 999999999L) {
            tick = 0;
        }
        return false;
    }
}
