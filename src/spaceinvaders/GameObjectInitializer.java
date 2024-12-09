package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameObjectInitializer {
    private final GameObjectFactory factory;
    private final BufferedImage[] alienImages;

    public GameObjectInitializer(GameObjectFactory factory, BufferedImage[] alienImages) {
        this.factory = factory;
        this.alienImages = alienImages;
    }

    public List<Alien> initializeAliens() {
        List<Alien> aliens = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 10; col++) {
                BufferedImage img;
                if (row == 1) {
                    img = alienImages[0]; 
                } else if (row == 2) { 
                    img = alienImages[0];
                } else if (row == 0) {
                    img = alienImages[1]; }
                else {
                    img = alienImages[2]; }
                aliens.add(factory.createAlien(img, 100 + col * 70, 50 + row * 50));
            }
        }
        return aliens;
    }

    public List<Barrier> initializeBarriers(BufferedImage barrierImage, int playerY) {
        int barrierY = playerY - 80; // Position the barriers above the player
        List<Barrier> barriers = new ArrayList<>();
        barriers.add(factory.createBarrier(barrierImage, 300, barrierY));
        barriers.add(factory.createBarrier(barrierImage, 724, barrierY));
        return barriers;
    }
}
