package spaceinvaders;

import java.util.List;

public class AlienFleet {
    private List<Alien> aliens;
    private int gameWidth;

    public AlienFleet(List<Alien> aliens, int gameWidth) {
        this.aliens = aliens;
        this.gameWidth = gameWidth;
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

        // If any alien hits the edge, reverse direction of all aliens
        if (collisionDetected) {
            for (Alien alien : aliens) {
                alien.reverseDirection();
                alien.move(); // Move aliens in the new direction immediately
            }
        }
    }

    public List<Alien> getAliens() {
        return aliens;
    }
}
