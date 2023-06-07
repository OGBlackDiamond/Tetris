package Blocks;

import KeyListener.Keylistener;

public class BlockABC {
  private int width;
  private int height;
  private int orentation;
  private int relativex;
  private int relativey;
  private boolean active = true;
  // constructor
  public BlockABC() {
  }

  public boolean change_orentation () throws Exception {
    int key = Keylistener.key;
    if (key == 1) {
      orentation = 0;
      width = 2;
      height = 1;
    } else if (key == 2) {
      orentation = 1;
      width = 1;
      height = 2;
    } else if (key == 3) {
      orentation = 2;
      width = 2;
      height = 1;
    } else if (key == 4) {
      orentation = 3;
      width = 1;
      height = 2;
    } else if (key == 5) {
      relativex--;
    } else if (key == 6) {
      relativex++;
    } else {
      return false;
    }

    key = 0;
    Keylistener.key = 0;
    return true;
  }



  public int[][] get_coords () {
    int[][] coords1 = {{0 + relativex, 0 + relativey}, {1 + relativex, 0 + relativey}, {2 + relativex, 0 + relativey}, {0 + relativex, 1 + relativey}};
    int[][] coords2 = {{0 + relativex, 0 + relativey}, {1 + relativex, 0 + relativey}, {1 + relativex, 1 + relativey}, {1 + relativex, 2 + relativey}};
    int[][] coords3 = {{2 + relativex, 0 + relativey}, {2 + relativex, 1 + relativey}, {1 + relativex, 1 + relativey}, {0 + relativex, 1 + relativey}};
    int[][] coords4 = {{0 + relativex, 0 + relativey}, {0 + relativex, 1 + relativey}, {0 + relativex, 2 + relativey}, {1 + relativex, 2 + relativey}};
    if (orentation == 0) {
      return coords1;
    } else if (orentation == 1) {
      return coords2;
    } else if (orentation == 2) {
      return coords3;
    } else {
      return coords4;
    }
  }



  public void fall () {
    relativey++;
  }

  public boolean is_active () {
    return active;
  }

  public void deactivate () {
    active = false;
  }

  public int get_dimensions(char x_or_y) {
    if (x_or_y == 'x') {
      return width;
    } else if (x_or_y == 'y') {
      return height;
    } else {
      System.out.println(x_or_y);
      return 0;
    }
  }

}
