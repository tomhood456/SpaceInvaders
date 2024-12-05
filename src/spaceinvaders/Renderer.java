package spaceinvaders;

import java.awt.Graphics;
import java.util.List;

public class Renderer {
    private final List<Bullet> bullets;

    public Renderer(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void render(Graphics g) {
        for (Bullet bullet : bullets) {
            g.drawImage(bullet.image, bullet.x, bullet.y, bullet.image.getWidth() / 2, bullet.image.getHeight() / 2, null);
        }
    }
}
