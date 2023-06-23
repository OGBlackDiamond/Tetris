package TetJava.Blocks;

import TetJava.KeyListener.Keylistener;
/**
 * This class will be extended by multiple block classes and will act as a framework for all of the block types to run.
 * This does not have a constructor, as it is an abstract class.
 */
public abstract class BlockABC {
    /** This variable contains a coordinate value for the block relative to the board */
    protected int xpos, ypos;

    /** This will contain the location of the parts reletive to the whole body of the block */
    protected int[][] partCoords;

    /** This will contain all possible coordinate values for the variable orientations of the block */
    protected int[][][] orientations;

    /** This variable will store the last key that was pressed on the keyboard */
    protected int keyPressed;

    /** Returns the last key that was pressed */
    private void getKey() {
        keyPressed = Keylistener.key;
    }

    /** 
     * This will change the position of the current block.
     * Method is abstract because the position to swtich to depends on the current block.
    */
    private void switchOrientation() {
        partCoords = keyPressed < 4 ? orientations[keyPressed] : partCoords;
    }

    private void shift() {
        switch (keyPressed) {
            case 4:
                xpos--;
                break;
            case 5:
                xpos++;
                break;
        }
    }

    /** 
     * Returns the current coordinate position of the top left position of the block
     *
     * @return coords - the coordinates of the block
     */
    public int[] getCoords() {
        return new int[] {ypos, xpos};
    }

    /**
     * Returns the coordinates of the adjacent pieces that make up the block reletive to the block's position
     *
     * @return partCoords - the array of coordinates for the specified orientation of the block
     */
    public int[][] getAdjacentCoords() {
        return partCoords;
    }

    /** Causes the block to fall */
    public void fall() {
        ypos++;
    }

    public void blockLoop() {
        getKey();
        switchOrientation();
        shift();
        Keylistener.key = 6;
    }
}
