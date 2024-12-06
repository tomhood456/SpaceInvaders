package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    private final GameManager gameManager;
    private final Renderer renderer;
    private final ScoreManager scoreManager;
    private boolean gameOver = false;

    public Canvas(GameManager gameManager, ScoreManager scoreManager) {
        this.gameManager = gameManager;
        this.renderer = new Renderer(gameManager.bullets);
        this.scoreManager = scoreManager;
    }

    @Override
    public final void paint(Graphics g) {
        clearScreen(g);
        drawAliens(g);
        renderer.render(g); // Render bullets
        drawPlayer(g);
        drawScore(g); // Draw the score
        if (gameOver) {
            drawGameOver(g); // Draw "Game Over" text
        }
    }

    private void clearScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void drawAliens(Graphics g) {
        int newWidth = gameManager.alienImg.getWidth() / 10;
        int newHeight = gameManager.alienImg.getHeight() / 10;
        for (Alien alien : gameManager.aliens) {
            g.drawImage(alien.image, alien.x, alien.y, newWidth, newHeight, null);
        }
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(gameManager.playerImg, gameManager.playerX, gameManager.playerY, null);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + scoreManager.getScore(), 10, 20);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", this.getWidth() / 2 - 150, this.getHeight() / 2);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
