package spaceinvaders;

import java.awt.image.BufferedImage;

public class Barrier {
    public BufferedImage image;
    public int x, y;

    public Barrier(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public boolean isHit(Bullet bullet) {
        return bullet.x < x + GameConfig.BARRIER_WIDTH && bullet.x + bullet.image.getWidth() > x &&
               bullet.y < y + GameConfig.BARRIER_HEIGHT && bullet.y + bullet.image.getHeight() > y;
    }
}



