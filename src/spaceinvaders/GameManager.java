package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public BufferedImage alienImg1;
    public BufferedImage alienImg2;
    public BufferedImage alienImg3;
    public BufferedImage barrierImg;
    public BufferedImage playerImg;
    public BufferedImage bulletImg;
    int playerX = 462; // Center player horizontally (1024/2 - 50)
    int playerY = 668; // Position player at the bottom (768 - 100)
    List<Alien> aliens;
    List<Bullet> bullets = new ArrayList<>();
    List<Barrier> barriers;
    private AlienFleet alienFleet;
    private final KeyEventHandler keyEventHandler;
    private final CollisionHandler collisionHandler;
    private final ScoreManager scoreManager;
    private GameObjectInitializer objectInitializer;
    private GameStateManager stateManager;
    private Canvas canvas;
    
    public GameManager() {
        keyEventHandler = new KeyEventHandler(this);
        scoreManager = new ScoreManager();
        ImageLoader imageLoader = new ImageLoader();
        loadImages(imageLoader);
        objectInitializer = new GameObjectInitializer(new BufferedImage[] {alienImg1, alienImg2, alienImg3});
        aliens = objectInitializer.initializeAliens();
        barriers = objectInitializer.initializeBarriers(barrierImg, playerY);
        alienFleet = new AlienFleet(aliens, 1024, playerY, new BufferedImage[] {alienImg1, alienImg2, alienImg3});
        collisionHandler = new CollisionHandler(bullets, aliens, barriers, scoreManager, alienImg1, alienImg2, alienImg3, playerY);
        stateManager = new GameStateManager(alienFleet, bullets, collisionHandler, canvas);
        startBulletTimer();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        stateManager = new GameStateManager(alienFleet, bullets, collisionHandler, canvas);
    }

    private void loadImages(ImageLoader loader) {
        alienImg1 = loader.loadImage("../images/alien1.png");
        alienImg2 = loader.loadImage("../images/alien10.png");
        alienImg3 = loader.loadImage("../images/alien12.png");
        playerImg = loader.loadImage("../images/player.png");
        bulletImg = loader.loadImage("../images/bullet.png");
        barrierImg = loader.loadImage("../images/barrier.png");
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

    public void updateGame() {
        stateManager.updateGame();
    }

    public boolean isGameOver() {
        return stateManager.isGameOver();
    }
}