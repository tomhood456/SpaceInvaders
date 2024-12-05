package spaceinvaders;

public class Main extends Thread {
    private Screen screen;
    private GameManager gameManager;

    public Main(GameManager gameManager) {
        this.gameManager = gameManager;
        screen = Screen.getInstance(gameManager);
        screen.setVisible(true);
    }

    public void run() {
        while (true) {
            try {
                gameManager.updateGame(); // Update game state
                screen.repaint(); // Repaint the screen
                Thread.sleep(16); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        Main main = new Main(gameManager);
        main.start();
    }
}
