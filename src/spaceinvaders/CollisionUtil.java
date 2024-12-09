package spaceinvaders;

import java.awt.image.BufferedImage;
import java.util.List;

public class CollisionUtil {

    public static boolean checkBulletCollision(Bullet bullet, List<Barrier> barriers, int playerY) {
        for (Barrier barrier : barriers) {
            if (bullet.y < playerY - GameConfig.BULLET_Y_THRESHOLD && barrier.isHit(bullet)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkAlienCollision(Bullet bullet, Alien alien) {
        return bullet.x < alien.x + GameConfig.ALIEN_WIDTH &&
               bullet.x + bullet.image.getWidth() / 2 > alien.x &&
               bullet.y < alien.y + GameConfig.ALIEN_HEIGHT &&
               bullet.y + bullet.image.getHeight() / 2 > alien.y;
    }

    public static int getAlienScore(BufferedImage alienImage, BufferedImage alienImg1, BufferedImage alienImg2, BufferedImage alienImg3) {
        if (alienImage.equals(alienImg1)) {
            return 10; 
        } else if (alienImage.equals(alienImg2)) {
            return 20; 
        } else if (alienImage.equals(alienImg3)) {
            return 30; 
        }
        return 0;
    }
}

