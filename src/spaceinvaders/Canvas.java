package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    private final GameManager gameManager;
    private final Renderer renderer;
    private final ScoreManager scoreManager;
    private boolean gameOver = false;
    private static final int ALIEN_WIDTH = 30; 
    private static final int ALIEN_HEIGHT = 20; 

    public Canvas(GameManager gameManager, ScoreManager scoreManager) {
        this.gameManager = gameManager;
        this.renderer = new Renderer(gameManager.bullets);
        this.scoreManager = scoreManager;
    }

    @Override
    public final void paint(Graphics g) {
        clearScreen(g);
        drawAliens(g);
        renderer.render(g); 
        drawPlayer(g);
        drawScore(g); 
        drawBarriers(g); 
        if (gameOver) {
            drawGameOver(g); 
        }
    }

    private void clearScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void drawAliens(Graphics g) {
        for (Alien alien : gameManager.aliens) {
            Image scaledImage = alien.image.getScaledInstance(ALIEN_WIDTH, ALIEN_HEIGHT, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, alien.x, alien.y, ALIEN_WIDTH, ALIEN_HEIGHT, null);
        }
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(gameManager.getPlayerImg(), gameManager.playerX, gameManager.playerY, null);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + scoreManager.getScore(), 10, 20);
    }

    private void drawBarriers(Graphics g) {
        for (Barrier barrier : gameManager.barriers) {
            Image scaledImage = barrier.image.getScaledInstance(GameConfig.BARRIER_WIDTH, GameConfig.BARRIER_HEIGHT, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, barrier.x, barrier.y, GameConfig.BARRIER_WIDTH, GameConfig.BARRIER_HEIGHT, null);
        }
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", this.getWidth() / 2 - 150, this.getHeight() / 2);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void render() {
        repaint();
    }
}
