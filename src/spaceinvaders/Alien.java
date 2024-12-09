package spaceinvaders;

import java.awt.image.BufferedImage;

public class Alien {
    BufferedImage image;
    int x, y;
    private int direction = GameConfig.ALIEN_DIRECTION;

    public Alien(BufferedImage img, int startX, int startY) {
        image = img;
        x = startX;
        y = startY;
    }

    public void move() {
        x += direction;
    }

    public void reverseDirection() {
        direction = -direction;
    }

    public boolean isAtEdge(int gameWidth) {
        return x < 0 || x + image.getWidth() / 10 > gameWidth;
    }
}