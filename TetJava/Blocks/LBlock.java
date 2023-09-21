package TetJava.Blocks;
import TetJava.Board;


/*

Lblock can be printed in 4 ways:

[][][]
[]

[][]
  []
  []

    []
[][][]

[]
[]
[][]

*/
public class LBlock extends BlockABC{

    // constructor
    public LBlock(Board board) {
        super(board);
        this.orientations = new int[][][] {
            /*
            [][][]
            []
            */
            {
                {0, 0},
                {1, 0},
                {2, 0},
                {0, 1}
            },
            /*
            [][]
              []
              []
             */
            {
                {0, 0},
                {1, 0},
                {1, 1},
                {1, 2}
            },
            /*
                []
            [][][]
             */
            {
                {2, 0},
                {2, 1},
                {0, 1},
                {1, 1}
            },
            /*
            []
            []
            [][]
             */
            {
                {0, 0},
                {0, 1},
                {0, 2},
                {1, 2}
            }
        };

        // places the default L shape
        this.partCoords = orientations[0];

        // sets the starting point for the block
        this.xpos = 4;
        this.ypos = -1;
    }
}
