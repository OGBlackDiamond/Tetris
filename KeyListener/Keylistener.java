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
                if (keyCode == KeyEvent.VK_W) {
                    key = 1;
                } else if (keyCode == KeyEvent.VK_A) {
                    key = 2;
                }else if (keyCode == KeyEvent.VK_S) {
                    key = 3;
                }else if (keyCode == KeyEvent.VK_D) {
                    key = 4;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    key = 5;
                } else if (keyCode == KeyEvent.VK_RIGHT) { 
                    key = 6;
                }
                get_key(key);
            } 
        });

        myJFrame.setVisible(true);
    }

    public static int get_key (int lkey) {
        key = lkey;
        return key;
    }

}
