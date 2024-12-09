package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameObjectInitializer {
    private BufferedImage[] alienImages;

    public GameObjectInitializer(BufferedImage[] alienImages) {
        this.alienImages = alienImages;
    }

    public List<Alien> initializeAliens() {
        AlienFactory alienFactory = new AlienFactory(alienImages);
        return alienFactory.createAliens(3, 10, 100, 50, 70, 50);
    }

    public List<Barrier> initializeBarriers(BufferedImage barrierImage, int playerY) {
        int barrierY = playerY - 80; // Position the barriers above the player
        List<Barrier> barriers = new ArrayList<>();
        barriers.add(new Barrier(barrierImage, 300, barrierY));
        barriers.add(new Barrier(barrierImage, 724, barrierY));
        return barriers;
    }
}

