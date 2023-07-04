package TetJava.Blocks;

/*

Tblock can be printed in 4 ways:

[]
[][]
[]

[][][]
  []

  []
[][][]

[]
[][]
[]

*/
public class TBlock extends BlockABC{

    // constructor
    public TBlock() {

        this.orientations = new int[][][] {
            /*
            []
            [][]
            []
            */
            {
                {0, 0},
                {0, 1},
                {1, 1},
                {0, 2}
            },
            /*
              []
            [][]
              []
             */
            {
                {1, 0},
                {1, 2},
                {1, 1},
                {0, 1}
            },
            /*
              []
            [][][]
             */
            {
                {1, 0},
                {0, 1},
                {1, 1},
                {2, 1}
            },
            /*
            [][][]
              []
             */
            {
                {0, 0},
                {1, 0},
                {2, 0},
                {1, 1}
            }
        };

        // places the default T shape
        this.partCoords = orientations[0];

        // sets the starting point for the block
        this.xpos = 4;
        this.ypos = -1;
    }
}
