package spaceinvaders;

import javax.swing.JFrame;

public class Screen extends JFrame {
    private static Screen instance;
    private final Canvas canvas;

    private Screen(GameManager gameManager, ScoreManager scoreManager) {
        canvas = new Canvas(gameManager, scoreManager);
        gameManager.setCanvas(canvas); // Set canvas in GameManager
        initUI(gameManager);
    }

    public static Screen getInstance(GameManager gameManager, ScoreManager scoreManager) {
        if (instance == null) {
            instance = new Screen(gameManager, scoreManager);
        }
        return instance;
    }

    private void initUI(GameManager gameManager) {
        setTitle("Space Invaders");
        setSize(1024, 768);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(canvas);
        addKeyListener(gameManager.getKeyEventHandler());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            requestFocusInWindow();
        }
    }
}
