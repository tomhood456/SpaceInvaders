package spaceinvaders;

public class Main extends Thread {
    private Screen screen;
    private GameManager gameManager;

    public Main(GameManager gameManager) {
        this.gameManager = gameManager;
        screen = Screen.getInstance(gameManager, gameManager.getScoreManager());
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
        ImageLoader imageLoader = new ImageLoader(); // Create ImageLoader instance
        GameManager gameManager = new GameManager(imageLoader); // Pass ImageLoader to GameManager
        Main main = new Main(gameManager);
        main.start();
    }
}
