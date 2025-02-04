package pkg2dplatformer;

public class GameLoop extends Thread {

    GamePanel gp;
    private final int FPS = 120;

    GameLoop(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void run() {

        Long now = System.nanoTime();
        Long last = now;
        double dps = 1000000000 / FPS;
        double delta = 0;
        while (true) {
            now = System.nanoTime();
            delta += (now - last) / dps;
            last = now;
            if (delta >= 1) {
                gp.Update();
                gp.repaint();
                delta = 0;
            }
        }
    }
}
