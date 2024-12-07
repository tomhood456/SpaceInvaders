package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

public class AlienFleet {
    private List<Alien> aliens;
    private int gameWidth;
    private int rowHeight = 50; // Define the row height
    private int playerY; // Player's y position
    private AlienFactory alienFactory; // Add an instance of AlienFactory
    private boolean movingRight = true; // Direction control
    private int moveSpeed = 2; // Adjust movement speed

    public AlienFleet(List<Alien> aliens, int gameWidth, int playerY, BufferedImage[] alienImages) {
        this.aliens = aliens;
        this.gameWidth = gameWidth;
        this.playerY = playerY;
        this.alienFactory = new AlienFactory(alienImages); // Initialize AlienFactory
    }

    public void moveAliens() {
        boolean collisionDetected = false;

        // Check if any alien is at the edge
        for (Alien alien : aliens) {
            if (movingRight && alien.x + alien.image.getWidth() / 10 >= gameWidth) {
                collisionDetected = true;
            } else if (!movingRight && alien.x <= 0) {
                collisionDetected = true;
            }
        }

        // Reverse direction and move down if a collision is detected
        if (collisionDetected) {
            movingRight = !movingRight; // Reverse direction
            for (Alien alien : aliens) {
                alien.y += rowHeight; // Move down by one row
            }
            addNewRow(); // Add a new row of aliens
        } else {
            // Move all aliens uniformly in the current direction
            for (Alien alien : aliens) {
                if (movingRight) {
                    alien.x += moveSpeed; // Move right
                } else {
                    alien.x -= moveSpeed; // Move left
                }
            }
        }
    }

    private void addNewRow() {
        int currentX = aliens.get(0).x; // Get the current x position of the first alien
        int newRowY = aliens.get(0).y - rowHeight; // New row y position
        List<Alien> newAliens = alienFactory.createSingleRow(10, currentX, newRowY, 70); // Create a new single row of aliens
        aliens.addAll(0, newAliens); // Add the new row to the top of the fleet
    }

    public boolean hasReachedBottom() {
        for (Alien alien : aliens) {
            if (alien.y + alien.image.getHeight() / 10 >= playerY) {
                return true;
            }
        }
        return false;
    }

    public List<Alien> getAliens() {
        return aliens;
    }
}

