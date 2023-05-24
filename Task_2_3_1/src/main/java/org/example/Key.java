package org.example;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * snake control class.
 */
public class Key extends KeyAdapter {

    /**
     * left.
     */
    private static boolean left;
    /**
     * right.
     */
    private static boolean right;
    /**
     * up.
     */
    private static boolean up;
    /**
     * down.
     */
    private static boolean down;

    /**
     * getter left.
     *
     * @return left.
     */

    public static boolean getLeft() {
        return left;
    }

    /**
     * getter right.
     *
     * @return right.
     */

    public static boolean getRight() {
        return right;
    }
    /**
     * getter up.
     *
     * @return up.
     */

    public static boolean getUp() {
        return up;
    }

    /**
     * getter down.
     *
     * @return down.
     */

    public static boolean getDown() {
        return down;
    }

    /**
     * the buttons are pressed.
     *
     * @param e the event to be processed.
     */

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_UP && !down) {
            up = true;
            right = false;
            left = false;
        }
        if (key == KeyEvent.VK_DOWN && !up) {
            down = true;
            right = false;
            left = false;
        }

    }

}
