package spaceinvaders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class KeyEventHandler extends KeyAdapter implements Handler {
    private final GameManager gameManager;
    private Handler next;

    public KeyEventHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public final void handle(Object request) {
        if (next != null) {
            next.handle(request);
        }
    }

    @Override
    public final void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            gameManager.movePlayerLeft();
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameManager.movePlayerRight();
        }

        handle(ke); // Pass the event down the chain
    }
}

