package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AlienFactory {
    private BufferedImage alienImg;

    public AlienFactory(BufferedImage alienImg) {
        this.alienImg = alienImg;
    }

    public List<Alien> createAliens(int numRows, int numCols, int startX, int startY, int spacingX, int spacingY) {
        List<Alien> aliens = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                aliens.add(new Alien(alienImg, startX + col * spacingX, startY + row * spacingY));
            }
        }
        return aliens;
    }
}

