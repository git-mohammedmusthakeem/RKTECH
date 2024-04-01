import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGame extends JFrame implements KeyListener {
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int CELL_SIZE = 10;

    private int snakeLength = 3;
    private int[] x = new int[WIDTH * HEIGHT];
    private int[] y = new int[WIDTH * HEIGHT];
    private int foodX, foodY;
    private boolean up = false, down = false, left = false, right = true;
    private boolean running = true;

    public SnakeGame() {
        setTitle("Snake Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(this);

        generateFood();

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                checkCollision();
                repaint();
            }
        });
        timer.start();
    }

    private void generateFood() {
        foodX = (int) (Math.random() * (WIDTH / CELL_SIZE)) * CELL_SIZE;
        foodY = (int) (Math.random() * (HEIGHT / CELL_SIZE)) * CELL_SIZE;
    }

    private void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (up) y[0] -= CELL_SIZE;
        if (down) y[0] += CELL_SIZE;
        if (left) x[0] -= CELL_SIZE;
        if (right) x[0] += CELL_SIZE;

        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            generateFood();
        }
    }

    private void checkCollision() {
        for (int i = snakeLength - 1; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                break;
            }
        }

        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT)
            running = false;
    }

    @Override
    public void paint(Graphics g) {
        if (!running) {
            g.setColor(Color.RED);
            g.drawString("Game Over!", WIDTH / 2 - 30, HEIGHT / 2);
            return;
        }

        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.GREEN);
        g.fillRect(foodX, foodY, CELL_SIZE, CELL_SIZE);

        g.setColor(Color.BLACK);
        for (int i = 0; i < snakeLength; i++) {
            g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP && !down) {
            up = true;
            left = false;
            right = false;
        }
        if (key == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = false;
            right = false;
        }
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
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGame().setVisible(true);
            }
        });
    }
}
