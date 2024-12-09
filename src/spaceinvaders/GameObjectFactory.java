package spaceinvaders;

import java.awt.image.BufferedImage;

public interface GameObjectFactory {
    Alien createAlien(BufferedImage img, int startX, int startY);
    Barrier createBarrier(BufferedImage img, int x, int y);
    Bullet createBullet(BufferedImage img, int startX, int startY);
}
