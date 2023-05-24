package org.example;

import javax.swing.*;

public class Window extends JFrame {
    /**
     * configuring the graphic window.
     */
    public Window() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(655, 680);
        setLocation(400, 400);
        add(new Game());
        setVisible(true);
    }

    /**
     * start.
     */
    public static void main(String[] args) {
        Window w = new Window();
    }
}
