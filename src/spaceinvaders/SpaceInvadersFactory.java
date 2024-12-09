package spaceinvaders;

import java.awt.image.BufferedImage;

public class SpaceInvadersFactory implements GameObjectFactory {
    @Override
    public Alien createAlien(BufferedImage img, int startX, int startY) {
        return new Alien(img, startX, startY);
    }

    @Override
    public Barrier createBarrier(BufferedImage img, int x, int y) {
        return new Barrier(img, x, y);
    }

    @Override
    public Bullet createBullet(BufferedImage img, int startX, int startY) {
        return new Bullet(img, startX, startY);
    }
}
