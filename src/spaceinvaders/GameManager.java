package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private final BufferedImage alienImg1;
    private final BufferedImage alienImg2;
    private final BufferedImage alienImg3;
    private final BufferedImage barrierImg;
    private final BufferedImage playerImg;
    private final BufferedImage bulletImg;
    int playerX = GameConfig.PLAYER_X; 
    int playerY = GameConfig.PLAYER_Y;
    List<Alien> aliens;
    List<Bullet> bullets = new ArrayList<>();
    List<Barrier> barriers;
    private AlienFleet alienFleet;
    private final KeyEventHandler keyEventHandler;
    private final KeyEventProcessor keyEventProcessor;
    private final CollisionHandler collisionHandler;
    private final ScoreManager scoreManager;
    private final GameObjectInitializer objectInitializer;
    private GameStateManager stateManager;
    private Canvas canvas;

    public GameManager(ImageLoader imageLoader) {
        keyEventHandler = new KeyEventHandler(this);
        keyEventProcessor = new KeyEventProcessor(this);
        scoreManager = new ScoreManager();
        alienImg1 = imageLoader.getAlienImg1();
        alienImg2 = imageLoader.getAlienImg2();
        alienImg3 = imageLoader.getAlienImg3();
        barrierImg = imageLoader.getBarrierImg();
        playerImg = imageLoader.getPlayerImg();
        bulletImg = imageLoader.getBulletImg();
        
        GameObjectFactory factory = new SpaceInvadersFactory();
        objectInitializer = new GameObjectInitializer(factory, new BufferedImage[] {alienImg1, alienImg2, alienImg3});
        aliens = objectInitializer.initializeAliens();
        barriers = objectInitializer.initializeBarriers(barrierImg, playerY);
        alienFleet = new AlienFleet(aliens, 1024, playerY, new BufferedImage[] {alienImg1, alienImg2, alienImg3});
        collisionHandler = new CollisionHandler(bullets, aliens, barriers, scoreManager, alienImg1, alienImg2, alienImg3, playerY);
        stateManager = new GameStateManager(alienFleet, bullets, collisionHandler, canvas);

        // Set up collision chain
        collisionHandler.setNext(null);

        // Set up key event chain
        keyEventHandler.setNext(keyEventProcessor);
    }

    public BufferedImage getPlayerImg() {
        return playerImg;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
        stateManager = new GameStateManager(alienFleet, bullets, collisionHandler, canvas);
    }

    public void movePlayerLeft() {
        playerX -= 10;
    }

    public void movePlayerRight() {
        playerX += 10;
    }

    public void addBullet() {
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


