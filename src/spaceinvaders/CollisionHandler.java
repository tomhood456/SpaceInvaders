package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

public final class CollisionHandler implements Handler {
    private final List<Bullet> bullets;
    private final List<Alien> aliens;
    private final List<Barrier> barriers;
    private final ScoreManager scoreManager;
    private final BufferedImage alienImg1;
    private final BufferedImage alienImg2;
    private final BufferedImage alienImg3;
    private final int playerY;
    private Handler next;

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
    public final void handle(Object request) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.move();
            if (bullet.isOffScreen()) {
                bulletIterator.remove();
            } else {
                if (CollisionUtil.checkBulletCollision(bullet, barriers, playerY)) {
                    bulletIterator.remove();
                    continue;
                }
                Iterator<Alien> alienIterator = aliens.iterator();
                while (alienIterator.hasNext()) {
                    Alien alien = alienIterator.next();
                    if (CollisionUtil.checkAlienCollision(bullet, alien)) {
                        alienIterator.remove();
                        bulletIterator.remove();
                        scoreManager.addScore(CollisionUtil.getAlienScore(alien.image, alienImg1, alienImg2, alienImg3));
                        break;
                    }
                }
            }
        }

        if (next != null) {
            next.handle(request);
        }
    }
}




