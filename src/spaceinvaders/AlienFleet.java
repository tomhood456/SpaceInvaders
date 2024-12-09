package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.List;

public class AlienFleet {
    private List<Alien> aliens;
    private int gameWidth;
    private int playerY;
    private AlienFactory alienFactory;
    private boolean movingRight = true;
    private int moveSpeed = GameConfig.MOVE_SPEED;

    public AlienFleet(List<Alien> aliens, int gameWidth, int playerY, BufferedImage[] alienImages) {
        this.aliens = aliens;
        this.gameWidth = gameWidth;
        this.playerY = playerY;
        this.alienFactory = new AlienFactory(alienImages);
    }

    public void moveAliens() {
        boolean collisionDetected = false;

        for (Alien alien : aliens) {
            if (movingRight && alien.x + alien.image.getWidth() / 10 >= gameWidth) {
                collisionDetected = true;
            } else if (!movingRight && alien.x <= 0) {
                collisionDetected = true;
            }
        }

        if (collisionDetected) {
            movingRight = !movingRight;
            for (Alien alien : aliens) {
                alien.y += GameConfig.ROW_HEIGHT;
            }
            addNewRow();
        } else {
            for (Alien alien : aliens) {
                if (movingRight) {
                    alien.x += moveSpeed;
                } else {
                    alien.x -= moveSpeed;
                }
            }
        }
    }

    private int alienRowCounter = 0;

    private void addNewRow() {
        int currentX = aliens.get(0).x;
        int newRowY = aliens.get(0).y - GameConfig.ROW_HEIGHT;

        int alienTypeIndex;
        if (alienRowCounter < 2) {
            alienTypeIndex = 0;
        } else if (alienRowCounter < 3) {
            alienTypeIndex = 2;
        } else if (alienRowCounter < 5) {
            alienTypeIndex = 0;
        } else {
            alienTypeIndex = 1;
        }

        alienRowCounter = (alienRowCounter + 1) % 6;

        // Include the missing `GameConfig.SPACING_X` parameter
        List<Alien> newAliens = alienFactory.createSingleRow(10, currentX, newRowY, GameConfig.SPACING_X);
        BufferedImage[] alienImages = alienFactory.getAlienImages();
        for (Alien alien : newAliens) {
            alien.image = alienImages[alienTypeIndex];
        }

        aliens.addAll(0, newAliens);
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