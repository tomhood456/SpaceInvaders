package spaceinvaders;

import javax.swing.JFrame;

public class Screen extends JFrame {
    private static Screen instance;
    private final GameManager gameManager;

    private Screen(GameManager gameManager) {
        this.gameManager = gameManager;
        initUI();
    }

    public static Screen getInstance(GameManager gameManager) {
        if (instance == null) {
            instance = new Screen(gameManager);
        }
        return instance;
    }

    private void initUI() {
        setTitle("Space Invaders");
        setSize(1024, 768);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Canvas canvas = new Canvas(gameManager);
        add(canvas);
        addKeyListener(gameManager.getKeyEventHandler());
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            requestFocusInWindow();
        }
    }
}
