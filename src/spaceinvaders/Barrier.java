package spaceinvaders;

import java.awt.image.BufferedImage;

public class Barrier {
    public BufferedImage image;
    public int x, y;
    private static final int BARRIER_WIDTH = 100; // Updated barrier width
    private static final int BARRIER_HEIGHT = 10; // Updated barrier height

    public Barrier(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public boolean isHit(Bullet bullet) {
        return bullet.x < x + BARRIER_WIDTH && bullet.x + bullet.image.getWidth() > x &&
               bullet.y < y + BARRIER_HEIGHT && bullet.y + bullet.image.getHeight() > y;
    }
}




