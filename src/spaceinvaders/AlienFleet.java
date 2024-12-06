package spaceinvaders;

import java.awt.image.BufferedImage; // Add this import
import java.util.List;
import java.util.ArrayList;

public class AlienFleet {
    private List<Alien> aliens;
    private int gameWidth;
    private int rowHeight = 50; // Define the row height
    private int playerY; // Player's y position
    private BufferedImage alienImg; // Add the alien image
    private AlienFactory alienFactory; // Add an instance of AlienFactory

    public AlienFleet(List<Alien> aliens, int gameWidth, int playerY, BufferedImage alienImg) {
        this.aliens = aliens;
        this.gameWidth = gameWidth;
        this.playerY = playerY;
        this.alienImg = alienImg;
        this.alienFactory = new AlienFactory(alienImg); // Initialize AlienFactory
    }

    public void moveAliens() {
        boolean collisionDetected = false;

        // Move each alien and check for collisions
        for (Alien alien : aliens) {
            alien.move();
            if (alien.isAtEdge(gameWidth)) {
                collisionDetected = true;
            }
        }

        // If any alien hits the edge, reverse direction of all aliens and move them down
        if (collisionDetected) {
            for (Alien alien : aliens) {
                alien.reverseDirection();
                alien.y += rowHeight; // Move down by one row
                alien.move(); // Move aliens in the new direction immediately
            }
            addNewRow(); // Add a new row of aliens
        }
    }

    private void addNewRow() {
        List<Alien> newAliens = alienFactory.createAliens(1, 10, 100, 50 - rowHeight, 70, 50); // Create a new row of aliens
        aliens.addAll(newAliens); // Add the new row to the fleet
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

