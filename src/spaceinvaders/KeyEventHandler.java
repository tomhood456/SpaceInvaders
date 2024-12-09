package spaceinvaders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

final class KeyEventHandler extends KeyAdapter implements Handler {
    private final GameManager gameManager;
    private Handler next;

    public KeyEventHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Object request) {
        // This method is not used for key events
        if (next != null) {
            next.handle(request);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            gameManager.movePlayerLeft();
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameManager.movePlayerRight();
        }
    }
}