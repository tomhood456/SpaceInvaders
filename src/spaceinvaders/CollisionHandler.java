package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

public class CollisionHandler implements Handler {
    private final List<Bullet> bullets;
    private final List<Alien> aliens;
    private final List<Barrier> barriers; // Add barriers to the handler
    private final ScoreManager scoreManager;
    private final BufferedImage alienImg1;
    private final BufferedImage alienImg2;
    private final BufferedImage alienImg3;
    private final int playerY; // Store player Y position
    private Handler next;
    private static final int ALIEN_WIDTH = 70; // Desired alien width
    private static final int ALIEN_HEIGHT = 50; // Desired alien height
    private static final int BULLET_Y_THRESHOLD = 10; // Adjust threshold to ensure bullets move away from player

    public CollisionHandler(List<Bullet> bullets, List<Alien> aliens, List<Barrier> barriers, ScoreManager scoreManager, BufferedImage alienImg1, BufferedImage alienImg2, BufferedImage alienImg3, int playerY) {
        this.bullets = bullets;
        this.aliens = aliens;
        this.barriers = barriers;
        this.scoreManager = scoreManager;
        this.alienImg1 = alienImg1;
        this.alienImg2 = alienImg2;
        this.alienImg3 = alienImg3;
        this.playerY = playerY;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Object request) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.move();
            if (bullet.isOffScreen()) {
                bulletIterator.remove();  // Remove bullet if it's off-screen
            } else {
                for (Barrier barrier : barriers) {
                    if (bullet.y < playerY - BULLET_Y_THRESHOLD) { // Ensure bullet is above the player before checking barrier collision
                        if (barrier.isHit(bullet)) {
                            bulletIterator.remove(); // Remove bullet if it hits a barrier
                            break;
                        }
                    }
                }
                Iterator<Alien> alienIterator = aliens.iterator();
                while (alienIterator.hasNext()) {
                    Alien alien = alienIterator.next();
                    if (bullet.x < alien.x + ALIEN_WIDTH &&
                        bullet.x + bullet.image.getWidth() / 2 > alien.x &&
                        bullet.y < alien.y + ALIEN_HEIGHT &&
                        bullet.y + bullet.image.getHeight() / 2 > alien.y) {
                        alienIterator.remove();  // Remove alien if hit
                        bulletIterator.remove();  // Remove bullet on collision
                        handleAlienHit(alien);  // Handle scoring based on alien type
                        break;
                    }
                }
            }
        }

        if (next != null) {
            next.handle(request);  // Pass to the next handler if exists
        }
    }

    private void handleAlienHit(Alien alien) {
        BufferedImage alienImage = alien.image;
        if (alienImage.equals(alienImg1)) {
            scoreManager.addScore(10); // 10 points for alien1
        } else if (alienImage.equals(alienImg2)) {
            scoreManager.addScore(20); // 20 points for alien10
        } else if (alienImage.equals(alienImg3)) {
            scoreManager.addScore(30); // 30 points for alien12
        }
    }
}

