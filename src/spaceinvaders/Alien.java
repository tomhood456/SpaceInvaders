package spaceinvaders;

import java.awt.image.BufferedImage;

public class Alien {
    BufferedImage image;
    int x, y, direction;
    private int moveCounter = 0; // Counter to control move frequency

    public Alien(BufferedImage img, int startX, int startY) {
        image = img;
        x = startX;
        y = startY;
        direction = 10; // Increase this value to make aliens move faster
    }

    public boolean move(int gameWidth) {
        moveCounter++;
        if (moveCounter % 2 == 0) { // Decrease this value to make movement more frequent
            x += direction;
            // Check if the alien hits the left or right edge
            if (x < 0 || x + image.getWidth() / 10 > gameWidth) {
                return true; // Collision detected
            }
        }
        return false;
    }

    public void reverseDirection() {
        direction = -direction;
    }
}
