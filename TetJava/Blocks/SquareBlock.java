package TetJava.Blocks;

/*

SquareBlock can be printed in 1 way:

[][]
[][]

*/
public class SquareBlock extends BlockABC{

    // constructor
    public SquareBlock() {

        this.orientations = new int[][][] {
            /*
            [][]
            [][]
            */
            {
                {0, 0},
                {1, 0},
                {1, 1},
                {0, 1}
            },
            {},
            {},
            {}

        };

        orientations[1] = orientations[0];
        orientations[2] = orientations[0];
        orientations[3] = orientations[0];

        // places the default shape
        this.partCoords = orientations[0];

        // sets the starting point for the block
        this.xpos = 4;
        this.ypos = -1;
    }
}
