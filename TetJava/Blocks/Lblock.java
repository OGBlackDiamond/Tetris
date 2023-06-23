package TetJava.Blocks;

/*

L block can be printed in 4 ways:

// default //
[][][]
[]
////////////

[][]
  []
  []

    []
[][][]

[]
[]
[][]

*/
public class Lblock extends BlockABC{


    // constructor
    public Lblock() {

        this.orientations = new int[][][] {
            /* default orientation
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
        this.xpos = 0;
        this.ypos = 0;
    }
}
