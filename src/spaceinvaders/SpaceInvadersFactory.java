package spaceinvaders;

import java.awt.image.BufferedImage;

public final class SpaceInvadersFactory implements GameObjectFactory {
    @Override
    public final Alien createAlien(BufferedImage img, int startX, int startY) {
        return new Alien(img, startX, startY);
    }

    @Override
    public final Barrier createBarrier(BufferedImage img, int x, int y) {
        return new Barrier(img, x, y);
    }

    @Override
    public final Bullet createBullet(BufferedImage img, int startX, int startY) {
        return new Bullet(img, startX, startY);
    }
}

