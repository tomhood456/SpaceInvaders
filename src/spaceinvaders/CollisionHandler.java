package spaceinvaders;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

public class CollisionHandler implements Handler {
    private List<Bullet> bullets;
    private List<Alien> aliens;
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
        Graphics g = (Graphics) request;

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.move();
            if (bullet.isOffScreen()) {
                bulletIterator.remove();
                continue;
            }

            boolean hit = false;
            Iterator<Alien> alienIterator = aliens.iterator();
            while (alienIterator.hasNext()) {
                Alien alien = alienIterator.next();
                if (bullet.x < alien.x + alien.image.getWidth() / 10 &&
                    bullet.x + bullet.image.getWidth() / 2 > alien.x &&
                    bullet.y < alien.y + alien.image.getHeight() / 10 &&
                    bullet.y + bullet.image.getHeight() / 2 > alien.y) {
                    alienIterator.remove();
                    hit = true;
                    break;
                }
            }

            if (hit) {
                bulletIterator.remove();
            } else {
                g.drawImage(bullet.image, bullet.x, bullet.y, bullet.image.getWidth() / 2, bullet.image.getHeight() / 2, null);
            }
        }

        if (next != null) {
            next.handle(request);
        }
    }
}
