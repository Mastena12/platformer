package pkg2dplatformer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    public static int tileSize = 10;
    private int tileX = 120;
    private int tileY = 65;
    public int Width = tileX * tileSize;
    private int Hight = tileY * tileSize;
    Player player;
    KeyAd keyAdap;

    GamePanel() {
        LevelHolder.addPath("src/level1.txt");
        LevelHolder.createLevels();
        player = new Player(20, Hight / 2, Width);
        keyAdap = new KeyAd(player);
        this.addKeyListener(keyAdap);
        this.setPreferredSize(new Dimension(Width, Hight));
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Width, Hight);
        Graphics2D g2d = (Graphics2D) g;
        Draw(g2d);
    }

    public void Update() {
        player.Move();
    }

    public void Draw(Graphics2D g2d) {
        player.draw(g2d);
        LevelHolder.Draw(g2d);

    }

}
