package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    private final GameManager gameManager;
    private final Renderer renderer;

    public Canvas(GameManager gameManager) {
        this.gameManager = gameManager;
        this.renderer = new Renderer(gameManager.bullets);
    }

    @Override
    public final void paint(Graphics g) {
        clearScreen(g);
        drawAliens(g);
        renderer.render(g); // Render bullets
        drawPlayer(g);
    }

    private void clearScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void drawAliens(Graphics g) {
        int newWidth = gameManager.alienImg.getWidth() / 10;
        int newHeight = gameManager.alienImg.getHeight() / 10;
        boolean edgeCollision = false;

        for (Alien alien : gameManager.aliens) {
            if (alien.move(getWidth())) {
                edgeCollision = true;
            }
            g.drawImage(alien.image, alien.x, alien.y, newWidth, newHeight, null);
        }

        if (edgeCollision) {
            for (Alien alien : gameManager.aliens) {
                alien.reverseDirection();
            }
        }
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(gameManager.playerImg, gameManager.playerX, gameManager.playerY, null);
    }
}