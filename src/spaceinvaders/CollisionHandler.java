package spaceinvaders;

import java.util.Iterator;
import java.util.List;

public class CollisionHandler implements Handler {
    private final List<Bullet> bullets;
    private final List<Alien> aliens;
    private Handler next;

    public CollisionHandler(List<Bullet> bullets, List<Alien> aliens) {
        this.bullets = bullets;
        this.aliens = aliens;
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
                    if (bullet.x < alien.x + alien.image.getWidth() / 10 &&
                        bullet.x + bullet.image.getWidth() / 2 > alien.x &&
                        bullet.y < alien.y + alien.image.getHeight() / 10 &&
                        bullet.y + bullet.image.getHeight() / 2 > alien.y) {
                        alienIterator.remove();  // Remove alien if hit
                        bulletIterator.remove();  // Remove bullet on collision
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
