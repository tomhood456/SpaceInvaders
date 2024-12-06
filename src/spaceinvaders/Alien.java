package spaceinvaders;

import java.awt.image.BufferedImage;

public class Alien {
    BufferedImage image;
    int x, y;
    private int direction;
    private int moveCounter = 0; // Counter to control move frequency

    public Alien(BufferedImage img, int startX, int startY) {
        image = img;
        x = startX;
        y = startY;
        direction = 3;
    }

    public void move() {
        moveCounter++;
        if (moveCounter % 2 == 0) { // Decrease this value to make movement more frequent
            x += direction;
        }
    }

    public void reverseDirection() {
        direction = -direction;
    }

    public boolean isAtEdge(int gameWidth) {
        return x < 0 || x + image.getWidth() / 10 > gameWidth;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
