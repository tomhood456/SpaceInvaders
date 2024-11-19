package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen {
    private JFrame frame = new JFrame();
    private Canvas canvas = new Canvas(); // we use inner class to paint on Screen
    private static final int GAME_WIDTH = 1024;
    private static final int GAME_HEIGHT = 768;
    private static Screen instance = new Screen();
    BufferedImage alienImg = null;
    BufferedImage playerImg = null;
    BufferedImage bulletImg = null;
    int playerX = GAME_WIDTH / 2 - 50; // Center player horizontally
    int playerY = GAME_HEIGHT - 100; // Position player at the bottom

    List<Alien> aliens = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();

    private final KeyEventHandler keyEventHandler;
    private final CollisionHandler collisionHandler;

    // Getter for singleton instance
    public static final Screen getInstance() {
        return instance;
    }

    // Constructor
    private Screen() {
        keyEventHandler = new KeyEventHandler(this);
        collisionHandler = new CollisionHandler(bullets, aliens);
        keyEventHandler.setNext(collisionHandler);

        initializeFrame();
        loadImages();
        createAliens();
        startBulletTimer();
    }

    // Initialize the game frame
    private void initializeFrame() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                keyEventHandler.handle(ke);
                return true;
            }
        });

        canvas.setSize(GAME_WIDTH, GAME_HEIGHT);
        frame.setSize(GAME_WIDTH, GAME_HEIGHT);
        frame.setResizable(false);
        frame.add(canvas);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
    }

    // Load images
    private void loadImages() {
        try {
            System.out.println("Attempting to load images...");
            alienImg = ImageIO.read(new File("../images/alien1.png"));
            playerImg = ImageIO.read(new File("../images/player.png"));
            bulletImg = ImageIO.read(new File("../images/bullet.png"));
            System.out.println("Images loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create aliens in multiple rows
    private void createAliens() {
        int numRows = 3;
        int numCols = 10;
        int alienSpacingX = 70;
        int alienSpacingY = 50;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                aliens.add(new Alien(alienImg, 100 + col * alienSpacingX, 50 + row * alienSpacingY));
            }
        }
    }

    // Start a timer to add bullets periodically
    private void startBulletTimer() {
        new javax.swing.Timer(500, e -> {
            addBullet();
            repaint();
        }).start();
    }

    // Move player left
    public void movePlayerLeft() {
        playerX -= 10;
    }

    // Move player right
    public void movePlayerRight() {
        playerX += 10;
    }

    // Add a new bullet
    private void addBullet() {
        bullets.add(new Bullet(bulletImg, playerX + playerImg.getWidth() / 2 - bulletImg.getWidth() / 2, playerY));
    }

    // Paint the game screen
    private class Canvas extends JPanel {
        @Override
        public final void paint(Graphics g) {
            clearScreen(g);
            drawAliens(g);
            collisionHandler.handle(g);
            drawPlayer(g);
        }

        // Clear the screen
        private void clearScreen(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        // Draw aliens
        private void drawAliens(Graphics g) {
            int newWidth = alienImg.getWidth() / 10;
            int newHeight = alienImg.getHeight() / 10;
            boolean edgeCollision = false;

            for (Alien alien : aliens) {
                if (alien.move(GAME_WIDTH)) {
                    edgeCollision = true;
                }
                g.drawImage(alien.image, alien.x, alien.y, newWidth, newHeight, null);
            }

            if (edgeCollision) {
                for (Alien alien : aliens) {
                    alien.reverseDirection();
                }
            }
        }

        // Draw the player
        private void drawPlayer(Graphics g) {
            g.drawImage(playerImg, playerX, playerY, null);
        }
    }

    public int getWidth() {
        return canvas.getWidth();
    }

    public int getHeight() {
        return canvas.getHeight();
    }

    public void repaint() {
        canvas.repaint();
    }

    public void setVisible(boolean b) {
        canvas.setVisible(b);
    }
}
