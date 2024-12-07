package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AlienFactory {
    private BufferedImage[] alienImages;
    private int nextRowIndex = 0; // Track the next row index for new rows

    public AlienFactory(BufferedImage[] alienImages) {
        this.alienImages = alienImages;
    }

    public List<Alien> createAliens(int numRows, int numCols, int startX, int startY, int spacingX, int spacingY) {
        List<Alien> aliens = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                BufferedImage img = alienImages[(nextRowIndex + row) % alienImages.length]; // Alternate images by row
                aliens.add(new Alien(img, startX + col * spacingX, startY + row * spacingY));
            }
        }
        nextRowIndex += numRows; // Update the row index
        return aliens;
    }

    public List<Alien> createSingleRow(int numCols, int startX, int startY, int spacingX) {
        List<Alien> aliens = new ArrayList<>();
        for (int col = 0; col < numCols; col++) {
            BufferedImage img = alienImages[nextRowIndex % alienImages.length]; // Alternate images by row
            aliens.add(new Alien(img, startX + col * spacingX, startY));
        }
        nextRowIndex++; // Update the row index
        return aliens;
    }
}




