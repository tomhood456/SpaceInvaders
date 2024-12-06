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
    private final ScoreManager scoreManager;
    private Canvas canvas; // Removed final keyword
    private boolean gameOver = false;

    public GameManager() { // Removed Canvas from constructor
        keyEventHandler = new KeyEventHandler(this);
        scoreManager = new ScoreManager();
        loadImages();
        AlienFactory alienFactory = new AlienFactory(alienImg);
        aliens = alienFactory.createAliens(3, 10, 100, 50, 70, 50); // Create a fleet of aliens
        alienFleet = new AlienFleet(aliens, 1024, playerY, alienImg); // Initialize AlienFleet with alienImg
        collisionHandler = new CollisionHandler(bullets, aliens, scoreManager);
        startBulletTimer();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
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

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateGame() {
        if (!gameOver) {
            alienFleet.moveAliens();
            for (Bullet bullet : bullets) {
                bullet.move();
            }
            collisionHandler.handle(null); // Handle collisions
            checkGameOver(); // Check if the game is over
        }
    }

    private void checkGameOver() {
        if (alienFleet.hasReachedBottom()) {
            gameOver = true;
            System.out.println("Game Over!");
            canvas.setGameOver(true); // Notify Canvas directly
        }
    }
}
