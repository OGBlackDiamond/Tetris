package KeyListener;

import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keylistener {
    public static int key = 0;
    public static void keylisten () throws Exception {
        JFrame myJFrame = new JFrame();

        myJFrame.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {

                int keyCode = e.getKeyCode();
                key = 0;
                switch (keyCode) {
                    case KeyEvent.VK_W:
                        key = 1;
                        break;
                    case KeyEvent.VK_A:
                        key = 2;
                        break;
                    case KeyEvent.VK_S:
                        key = 3;
                        break;
                    case KeyEvent.VK_D:
                        key = 4;
                        break;
                    case KeyEvent.VK_LEFT:
                        key = 5;
                        break;
                    case KeyEvent.VK_RIGHT:
                        key = 6;
                        break;
                    default:
                        key = 0;
                        break;
                }
                get_key(key);
            }
        });

        myJFrame.setVisible(true);
    }

    public static int get_key(int lkey) {
        key = lkey;
        return key;
    }

}
