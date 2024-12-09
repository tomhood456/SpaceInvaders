package spaceinvaders;

import java.util.List;

public class GameStateManager {
    private AlienFleet alienFleet;
    private List<Bullet> bullets;
    private CollisionHandler collisionHandler;
    private Canvas canvas;
    private boolean gameOver = false;

    public GameStateManager(AlienFleet alienFleet, List<Bullet> bullets, CollisionHandler collisionHandler, Canvas canvas) {
        this.alienFleet = alienFleet;
        this.bullets = bullets;
        this.collisionHandler = collisionHandler;
        this.canvas = canvas;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateGame() {
        if (!gameOver) {
            alienFleet.moveAliens();
            for (Bullet bullet : bullets) {
                bullet.move();
            }
            collisionHandler.handle(null); // Handle collisions
            checkGameOver(); // Check if the game is over
            canvas.render(); // Render the canvas
        }
    }

    private void checkGameOver() {
        if (alienFleet.hasReachedBottom()) {
            gameOver = true;
            System.out.println("Game Over!");
            canvas.setGameOver(true); // Notify Canvas directly
        }
    }
}


