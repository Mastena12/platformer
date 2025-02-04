package pkg2dplatformer;

import java.awt.Color;
import java.util.*;
import java.awt.Graphics2D;

public class Obstycle {

    public int healt;
    boolean Enemy;
    private double x;
    private int y;
    private int wid;
    private int hig;
    boolean hit = false;
    int sayac = 0;
    double velX = 1;
    Random rand = new Random();
    public Color color = Color.GREEN.darker();

    public Obstycle(int x, int y, int wid, int hig, boolean enemy) {
        Enemy = enemy;
        setX(x);
        setY(y);
        setWid(wid);
        setHig(hig);
        if (enemy) {
            healt = 100;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getWid() {
        return wid;
    }

    public void setHig(int hig) {
        this.hig = hig;
    }

    public int getHig() {
        return hig;
    }

    public void MoveLeft() {
        x -= velX;
    }

    public void MoveRight() {
        x += velX;
    }

    public void Draw(Graphics2D g2d) {
        /*
        if(sayac%100==0){
            g2d.setColor(new Color(rand.nextInt(0,250) , rand.nextInt(0 , 250), rand.nextInt(0 , 250)));
            sayac =0;
        }
         */

        g2d.setColor(color);
        if (Enemy) {
            g2d.setColor(Color.RED.darker());
            g2d.fillRect((int) x, y - 10, healt, 3);
        }
        if (Enemy) {
            g2d.setColor(Color.BLUE.darker());
            g2d.fillRect((int) x, y, wid, hig);
        } else {
            g2d.fillRect((int) x, y, wid, hig);
        }
    }
}
