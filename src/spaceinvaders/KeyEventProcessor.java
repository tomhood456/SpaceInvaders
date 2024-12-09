package spaceinvaders;

import java.awt.event.KeyEvent;

public class KeyEventProcessor implements Handler {
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(Object request) {
        if (request instanceof KeyEvent) {
            KeyEvent ke = (KeyEvent) request;
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                // Handle space key press, e.g., shooting a bullet
                System.out.println("Space key pressed!");
            }
        }

        if (next != null) {
            next.handle(request);
        }
    }
}

