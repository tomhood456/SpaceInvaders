package spaceinvaders;

public class Main extends Thread {
    Screen screen = null;

    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    wait(1000);
                }
                screen.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Main() {
        screen = Screen.getInstance();
        screen.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
