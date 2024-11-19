package spaceinvaders;

import java.awt.event.KeyEvent;

public class KeyEventHandler implements Handler {
    private Screen screen;
    private Handler next;

    public KeyEventHandler(Screen screen) {
        this.screen = screen;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Object request) {
        KeyEvent ke = (KeyEvent) request;
        if (ke.getID() == KeyEvent.KEY_PRESSED) {
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                screen.movePlayerLeft();
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                screen.movePlayerRight();
            }
        } else {
            if (next != null) {
                next.handle(request);
            }
        }
    }
}
