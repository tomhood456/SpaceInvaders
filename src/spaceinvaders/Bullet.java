package spaceinvaders;

import java.awt.image.BufferedImage;

public class Bullet {
    BufferedImage image;
    int x, y;
    int speed = 30;

    public Bullet(BufferedImage img, int startX, int startY) {
        image = img;
        x = startX;
        y = startY;
    }

    public void move() {
        y -= speed;
    }

    public boolean isOffScreen() {
        return y < 0;
    }
}

