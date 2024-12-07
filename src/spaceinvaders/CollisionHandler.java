package spaceinvaders;

import java.util.Iterator;
import java.util.List;

public class CollisionHandler implements Handler {
    private final List<Bullet> bullets;
    private final List<Alien> aliens;
    private final ScoreManager scoreManager;
    private Handler next;
    private static final int ALIEN_WIDTH = 70; // Desired alien width
    private static final int ALIEN_HEIGHT = 50; // Desired alien height

    public CollisionHandler(List<Bullet> bullets, List<Alien> aliens, ScoreManager scoreManager) {
        this.bullets = bullets;
        this.aliens = aliens;
        this.scoreManager = scoreManager;
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
                Iterator<Alien> alienIterator = aliens.iterator();
                while (alienIterator.hasNext()) {
                    Alien alien = alienIterator.next();
                    if (bullet.x < alien.x + ALIEN_WIDTH &&
                        bullet.x + bullet.image.getWidth() / 2 > alien.x &&
                        bullet.y < alien.y + ALIEN_HEIGHT &&
                        bullet.y + bullet.image.getHeight() / 2 > alien.y) {
                        alienIterator.remove();  // Remove alien if hit
                        bulletIterator.remove();  // Remove bullet on collision
                        scoreManager.increaseScore(10);  // Increase score by 10
                        break;
                    }
                }
            }
        }

        if (next != null) {
            next.handle(request);  // Pass to the next handler if exists
        }
    }
}

