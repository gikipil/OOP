package org.example;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;


/**
 * game.
 */

public class Game extends JPanel implements ActionListener {
    /**
     * field size.
     */
    private final int size = 640;
    /**
     * cell size.
     */
    private final int section = 32;
    /**
     * number of cells.
     */
    private final int allSection = 400;
    /**
     * snake images.
     */
    private Image snake;
    /**
     * food images.
     */
    private Image food;
    /**
     * body images.
     */
    private Image body;
    /**
     * x food coordinate.
     */
    private int foodX;
    /**
     * y food coordinate.
     */
    private int foodY;

    /**
     * x snake coordinate.
     */
    private int[] x = new int[allSection];
    /**
     * y snake coordinate.
     */
    private int[] y = new int[allSection];

    /**
     * snake length.
     */
    private int snakeLength;
    /**
     * timer.
     */
    private Timer timer;
    /**
     * death or no.
     */
    private  boolean death;

    /**
     * game.
     */

    public  Game() {
        setBackground(Color.GRAY);
        setImageSnake();
        setImageFood();
        gameIni();
        addKeyListener(new Key());
        setFocusable(true);
    }

    /**
     * image installation snake.
     */

    public void  setImageSnake() {
        ImageIcon temp = new ImageIcon("snake.png");
        snake = temp.getImage();
        temp = new ImageIcon("snakebody.png");
        body = temp.getImage();
    }

    /**
     * image installation food.
     */
    public void  setImageFood() {
        ImageIcon temp = new ImageIcon("food.png");
        food = temp.getImage();
    }

    /**
     * initializing the game.
     */

    public void gameIni() {
        snakeLength = 1;
        for (int i = 0; i < snakeLength; i++) {
            x[i] = 0 - i * section;
            y[i] = 0;
        }
        timer = new Timer(150, this);
        timer.start();
        createFood();
    }

    /**
     * creating food in a random place.
     */
    public void createFood() {
        foodX = new Random().nextInt(20) * section;
        foodY = new Random().nextInt(20) * section;
    }

    /**
     * changing the position of the snake.
     * from the direction of movement.
     */
    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (Key.getLeft()) {
            x[0] -= section;
        }
        if (Key.getRight()) {
            x[0] += section;
        }
        if (Key.getUp()) {
            y[0] -= section;
        }
        if (Key.getDown()) {
            y[0] += section;
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!death) {
            checkFood();
            checkDeath();
            checkWindow();
            move();
        }
        repaint();
    }

    /**
     * scene renderer.
     *
     * @param g the <code>Graphics</code> object to protect.
     */

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!death) {
            g.drawImage(food, foodX, foodY, this);
            g.drawImage(snake, x[0], y[0], this);
            for (int i = 1; i < snakeLength; i++) {
                g.drawImage(body, x[i], y[i], this);
            }
        } else {
            String gameOver = "Game Over";
            g.setColor(Color.RED);
            g.drawString(gameOver, 300, size / 2);
        }
    }

    /**
     * checking for ingestion of food.
     */
    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            createFood();
        }
    }

    /**
     * death check.
     */
    public void checkDeath() {
        for (int i = snakeLength; i > 0; i--) {
            if (snakeLength > 4 && x[0] == x[i] && y[0] == y[i]) {
                death = true;
            }
        }
    }

    /**
     * checking for getting behind the screen.
     */
    public void checkWindow() {
        if (x[0] == size - section && Key.getRight()) {
            x[0] = 0 - section;
        }
        if (x[0] == 0 && Key.getLeft()) {
            x[0] = size;
        }
        if (y[0] == size - section && Key.getDown()) {
            y[0] = 0 - section;
        }
        if (y[0] == 0 && Key.getUp()) {
            y[0] = size;
        }
    }
}
