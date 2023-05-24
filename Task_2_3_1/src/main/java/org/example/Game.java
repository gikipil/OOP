package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
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
    private int[] positionX = new int[allSection];
    /**
     * y snake coordinate.
     */
    private int[] positionY = new int[allSection];

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
            positionX[i] = 0 - i * section;
            positionY[i] = 0;
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
            positionX[i] = positionX[i - 1];
            positionY[i] = positionY[i - 1];
        }
        if (Key.getLeft()) {
            positionX[0] -= section;
        }
        if (Key.getRight()) {
            positionX[0] += section;
        }
        if (Key.getUp()) {
            positionY[0] -= section;
        }
        if (Key.getDown()) {
            positionY[0] += section;
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
            g.drawImage(snake, positionX[0], positionY[0], this);
            for (int i = 1; i < snakeLength; i++) {
                g.drawImage(body, positionX[i], positionY[i], this);
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
        if (positionX[0] == foodX && positionY[0] == foodY) {
            snakeLength++;
            createFood();
        }
    }

    /**
     * death check.
     */
    public void checkDeath() {
        for (int i = snakeLength; i > 0; i--) {
            if (snakeLength > 4 && positionX[0] == positionX[i] && positionY[0] == positionY[i]) {
                death = true;
            }
        }
    }

    /**
     * checking for getting behind the screen.
     */
    public void checkWindow() {
        if (positionX[0] == size - section && Key.getRight()) {
            positionX[0] = 0 - section;
        }
        if (positionX[0] == 0 && Key.getLeft()) {
            positionX[0] = size;
        }
        if (positionY[0] == size - section && Key.getDown()) {
            positionY[0] = 0 - section;
        }
        if (positionY[0] == 0 && Key.getUp()) {
            positionY[0] = size;
        }
    }
}
