package Blocks;

import KeyListener.Keylistener;
/**
 * This class will be extended by multiple block classes and will act as a framework for all of the block types to run.
 * This does not have a constructor, as it is an abstract class.
 */
public abstract class BlockABC {
    /** This variable contains a coordinate value for the block relative to the board */
    protected int xpos, ypos;
    
    /** This will contain the location of the parts reletive to the whole body of the block */
    protected int[][] partCoords = {{0, 0}, {0 ,1}, {1, 0}, {1, 1}};

    /** This variable will store the last key that was pressed on the keyboard */
    protected int keyPressed;

    /** 
     * This will change the position of the current block.
     * Method is abstract because the position to swtich to depends on the current block.
    */
    public abstract void switchPosition();

    /** Causes the block to fall */
    public void fall() {
        ypos--;
    }

    /** Gets the current key that was pressed */
    protected void setKey() {
        keyPressed = Keylistener.key;
    }

    /** Returns the last key that was pressed */
    protected int getKey() {
        return keyPressed;
    }
}
