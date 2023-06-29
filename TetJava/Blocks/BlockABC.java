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

    /** This will contain the farthest right x coordinate reletive to the block */
    protected int farthestFromBlock = 0;

    /** Returns the last key that was pressed */
    private void getKey() {
        keyPressed = Keylistener.key;
    }

    /** 
     * This will change the position of the current block.
     * Method is abstract because the position to swtich to depends on the current block.
    */
    private void switchOrientation() {
        // changes the orientation
        partCoords = keyPressed < 4 ? orientations[keyPressed] : partCoords;

        // every time the orentation is changed, the farthest part from the block is calculated
        farthestFromBlock = 0;
        for (int[] part: partCoords) {
            if (part[1] > farthestFromBlock) {
                farthestFromBlock = part[1];
            }
        }
    }

    private void shift(int boardWidth) {
        switch (keyPressed) {
            case 4:
                if (xpos > 0) {
                    xpos--;
                }
                break;
            case 5:
                xpos++;
                break;
        }

        while (xpos + farthestFromBlock >= boardWidth - 1) {
            xpos--;
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

    public void blockLoop(int boardHeight, int boardWidth) {
        getKey();
        switchOrientation();
        shift(boardWidth);
        Keylistener.key = 6;
    }
}
