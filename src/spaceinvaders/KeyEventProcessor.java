package spaceinvaders;

import java.awt.event.KeyEvent;

public final class KeyEventProcessor implements Handler {
    private final GameManager gameManager;
    private Handler next;

    public KeyEventProcessor(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public final void handle(Object request) {
        if (request instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent) request;
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                gameManager.addBullet(); // Add bullet when space bar is pressed
            }
        }

        if (next != null) {
            next.handle(request);
        }
    }
}

