package pkg2dplatformer;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    GamePanel gp = new GamePanel();
    GameLoop gl = new GameLoop(gp);

    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.add(gp);
        gl.start();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
