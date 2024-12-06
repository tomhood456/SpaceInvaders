package spaceinvaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class GameManager {
    BufferedImage alienImg = null;
    BufferedImage playerImg = null;
    BufferedImage bulletImg = null;
    int playerX = 512 - 50; // Center player horizontally
    int playerY = 768 - 100; // Position player at the bottom
    List<Alien> aliens = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    private AlienFleet alienFleet;
    private final KeyEventHandler keyEventHandler;
    private final CollisionHandler collisionHandler;

    public GameManager() {
        keyEventHandler = new KeyEventHandler(this);
        collisionHandler = new CollisionHandler(bullets, aliens);
        keyEventHandler.setNext(collisionHandler);
        loadImages();
        createAliens();
        alienFleet = new AlienFleet(aliens, 1024); // Assuming gameWidth is 1024
        startBulletTimer();
    }

    private void loadImages() {
        try {
            alienImg = ImageIO.read(new File("../images/alien1.png"));
            playerImg = ImageIO.read(new File("../images/player.png"));
            bulletImg = ImageIO.read(new File("../images/bullet.png"));
            Logger.log("Images loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            Logger.log("Error loading images: " + e.getMessage());
        }
    }

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

    private void startBulletTimer() {
        new javax.swing.Timer(500, e -> addBullet()).start();
    }

    public void movePlayerLeft() {
        playerX -= 10;
    }

    public void movePlayerRight() {
        playerX += 10;
    }

    private void addBullet() {
        bullets.add(new Bullet(bulletImg, playerX + playerImg.getWidth() / 2 - bulletImg.getWidth() / 2, playerY));
    }

    public KeyEventHandler getKeyEventHandler() {
        return keyEventHandler;
    }

    public void updateGame() {
        alienFleet.moveAliens();
        for (Bullet bullet : bullets) {
            bullet.move();
        }
        collisionHandler.handle(null); // Handle collisions (pass null as Graphics is not needed here)
    }
}
