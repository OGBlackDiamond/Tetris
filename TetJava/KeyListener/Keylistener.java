package TetJava.KeyListener;

import javax.swing.JFrame;

import TetJava.Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keylistener {
    public static int key = 0;
    public static void keylisten (Main tetris) throws Exception {
        JFrame myJFrame = new JFrame();
        myJFrame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                int keyCode = e.getKeyCode();
                key = 6;
                switch (keyCode) {
                    case KeyEvent.VK_W:
                        key = 0;
                        break;
                    case KeyEvent.VK_A:
                        key = 1;
                        break;
                    case KeyEvent.VK_S:
                        key = 2;
                        break;
                    case KeyEvent.VK_D:
                        key = 3;
                        break;
                    case KeyEvent.VK_LEFT:
                        key = 4;
                        break;
                    case KeyEvent.VK_RIGHT:
                        key = 5;
                        break;
                    default:
                        key = 6;
                        break;
                }
                getKey(key);
                tetris.gameloop();
            }


        });

        myJFrame.setVisible(true);
    }

    public static int getKey(int lkey) {
        key = lkey;
        return key;
    }

}
