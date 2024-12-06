package spaceinvaders;

import java.awt.image.BufferedImage;

public class Bullet {
    BufferedImage image;
    int x, y;
    int speed = 3;  // Bullet speed

    public Bullet(BufferedImage img, int startX, int startY) {
        image = img;
        x = startX;
        y = startY;
    }

    // Move the bullet upwards
    public void move() {
        y -= speed;
    }

    // Check if the bullet is off the screen
    public boolean isOffScreen() {
        return y < 0;
    }
}
