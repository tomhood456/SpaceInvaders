package spaceinvaders;

import java.awt.image.BufferedImage;

public class Alien {
    BufferedImage image;
    int x, y, direction;

    public Alien(BufferedImage img, int startX, int startY) {
        image = img;
        x = startX;
        y = startY;
        direction = 10;
    }

    public boolean move(int gameWidth) {
        x += direction;
        // Check if the alien hits the left or right edge
        if (x < 0 || x + image.getWidth() / 10 > gameWidth) {
            return true; // Collision detected
        }
        return false;
    }

    public void reverseDirection() {
        direction = -direction;
    }
}

