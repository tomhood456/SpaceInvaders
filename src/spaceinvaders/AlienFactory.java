package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AlienFactory {
    private BufferedImage[] alienImages;

    public AlienFactory(BufferedImage[] alienImages) {
        this.alienImages = alienImages;
    }

    public BufferedImage[] getAlienImages() {
        return alienImages;
    }

    public List<Alien> createAliens(int numRows, int numCols, int startX, int startY, int spacingX, int spacingY) {
        List<Alien> aliens = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                BufferedImage img;
                if (row == 1) { // Bottom two rows are alien1
                    img = alienImages[0];
                } else if (row == 2) { // Third row from the bottom is alien10
                    img = alienImages[0];
                } else if (row == 0) { // Fourth and fifth row from the bottom are alien1 again
                    img = alienImages[1];
                } else { // Sixth row from the bottom is alien12
                    img = alienImages[2];
                }
                aliens.add(new Alien(img, startX + col * spacingX, startY + row * spacingY));
            }
        }
        return aliens;
    }

    public List<Alien> createSingleRow(int numCols, int startX, int startY, int spacingX) {
        List<Alien> aliens = new ArrayList<>();
        BufferedImage img = alienImages[0]; // Default to alien1 for simplicity
        for (int col = 0; col < numCols; col++) {
            aliens.add(new Alien(img, startX + col * spacingX, startY));
        }
        return aliens;
    }
}
