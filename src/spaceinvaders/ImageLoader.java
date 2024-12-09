package spaceinvaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
    private BufferedImage alienImg1;
    private BufferedImage alienImg2;
    private BufferedImage alienImg3;
    private BufferedImage barrierImg;
    private BufferedImage playerImg;
    private BufferedImage bulletImg;

    public ImageLoader() {
        loadImages();
    }

    private void loadImages() {
        alienImg1 = loadImage("../images/alien1.png");
        alienImg2 = loadImage("../images/alien10.png");
        alienImg3 = loadImage("../images/alien12.png");
        barrierImg = loadImage("../images/barrier.png");
        playerImg = loadImage("../images/player.png");
        bulletImg = loadImage("../images/bullet.png");
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BufferedImage getAlienImg1() { return alienImg1; }
    public BufferedImage getAlienImg2() { return alienImg2; }
    public BufferedImage getAlienImg3() { return alienImg3; }
    public BufferedImage getBarrierImg() { return barrierImg; }
    public BufferedImage getPlayerImg() { return playerImg; }
    public BufferedImage getBulletImg() { return bulletImg; }
}
