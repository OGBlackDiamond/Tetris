package TetJava;
/* 
Tetris game in console
author - Caden Feller
*/
import TetJava.Blocks.*;

/** The main class that acts as a crossroads for everything to intersect. */
public class Main {
    /** The current tick the game runs on. */
    private long tick = 0L;
    /** The rate at which the game will tick. */
    private long tickRate = 999999999L;
    /** The board class. */
    private Board board;
    /** The block currently in the level. */
    private BlockABC block;

    /** Starts the game with the board dimensions given. */
    public Main(int width, int height) {
        this.board = new Board(new int[] {width, height});
        this.block = new SBlock(board);
        board.setBlock(block);
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

    /** 
     * Handles all of the action that happens whenever the gameloop runs. 
     */
    public void gameloop() {
        // checks if there is a block currently loaded into the level
        if (block != null) {
            blocksLoop();
        }

        // runs all functions for the game to run
        board.drawBoard();
        board.zeroBoard(false);
        board.updateBoard();
    }

    /**
     * Handles the block falling.
     * Removes the old block from memory and loads in a new one if the block is no longer falling (i.e. touched the ground or another block).
     */
    public void fall() {
        // if the block is not allowed to fall anymore, kill it, and create a new block object
        if (!block.fall()) {
            block = null;
            block = board.deactivateBlock();
        }
    }

    /**
     * Runs the loop for all the blocks present
     */
    private void blocksLoop() {
        block.blockLoop(board.boardHeight, board.boardWidth);
    }

    /**
     * Handles all of the timing for how often the game should run
     * 
     * @return boolean - whether or not the gameloop should run
     */
    private boolean getTick() {
        if (tick == tickRate) {
            return true;
        } else if (tick > tickRate) {
            tick = 0;
        }
        return false;
    }
}
