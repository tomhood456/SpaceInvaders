package spaceinvaders;

public class Main extends Thread {
    private Screen screen;
    private GameManager gameManager;
    private ScoreManager scoreManager;

    public Main(GameManager gameManager, ScoreManager scoreManager) {
        this.gameManager = gameManager;
        this.scoreManager = scoreManager;
        screen = Screen.getInstance(gameManager, scoreManager);
        screen.setVisible(true);
    }

    public void run() {
        while (true) {
            try {
                if (!gameManager.isGameOver()) {
                    gameManager.updateGame(); // Update game state
                    screen.repaint(); // Repaint the screen
                } else {
                    screen.getCanvas().setGameOver(true); // Display game over text
                    screen.repaint(); // Repaint the screen
                }
                Thread.sleep(16); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager(); // Initialize GameManager first
        ScoreManager scoreManager = gameManager.getScoreManager();
        Main main = new Main(gameManager, scoreManager);
        main.start();
    }
}

