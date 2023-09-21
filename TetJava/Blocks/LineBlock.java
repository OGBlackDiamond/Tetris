package TetJava.Blocks;
import TetJava.Board;
/*

LineBlock can be printed in 2 ways:

[]
[]
[]
[]

[][][][]

*/
public class LineBlock extends BlockABC{

    // constructor
    public LineBlock(Board board) {
        super(board);
        this.orientations = new int[][][] {
            /*
            [][][][]
            */
            {
                {0, 0},
                {1, 0},
                {2, 0},
                {3, 0}
            },
            /*
            []
            []
            []
            []
             */
            {
                {0, 0},
                {0, 1},
                {0, 2},
                {0, 3}
            },
            {},
            {}
        };

        orientations[2] = orientations[0];
        orientations[3] = orientations[1];

        // places the default shape
        this.partCoords = orientations[0];

        // sets the starting point for the block
        this.xpos = 4;
        this.ypos = -1;
    }
}
