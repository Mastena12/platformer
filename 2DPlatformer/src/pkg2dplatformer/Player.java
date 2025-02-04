package pkg2dplatformer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player {

    int sayac = 0;
    double BoardWidth;
    int x;
    double y;
    int velX = 2;
    double velY;
    int wid = 20;
    int hig = 20;
    double jumpPow = 7;
    double gravity = 0.1;
    double g = 0.1;
    boolean Left = false;
    boolean Right = false;
    int jumpCount = 2;
    boolean jump = false;
    boolean atack = false;
    Obstycle ButtomBlock;
    ArrayList<Obstycle> level;
    Random rand = new Random();
    ImageIcon[] img = new ImageIcon[5];
    int ImgC;
    int secCount;
    int drawInterval = 20;

    public void SetImages() {
        for (int i = 1; i <= 5; i++) {
            img[i - 1] = new ImageIcon("C:\\Users\\HP\\OneDrive\\Desktop\\mx" + i + ".png");
        }
    }

    Player(int x, int y, double BoardWidth) {
        this.BoardWidth = BoardWidth;
        this.x = x;
        this.y = y;
        this.level = LevelHolder.GetLev();
        SetImages();
        wid = img[0].getIconWidth() / 2;
        hig = img[0].getIconHeight();
        FindButtomBlock();
    }

    public void Move() {
        if (Left) {
            x -= velX;
        } else if (Right) {
            x += velX;
        }
        if (Left || Right || jump) {
            FindButtomBlock();
        }
        if (jump) {
            Jump();
        }
        x = Math.min(x, (int) (BoardWidth * 0.9));
        x = Math.max(x, (int) (BoardWidth * 0.02));
        gravity += g;
        gravity = Math.min(10, gravity);
        y += velY;
        y += gravity;
        if (ButtomBlock != null) {
            y = Math.min(y, ButtomBlock.getY() - hig);
            if (y + hig == ButtomBlock.getY()) {
                jumpCount = 2;
                gravity = 0.1;
            }
        }
    }

    public void Jump() {
        y -= jumpPow;
        jumpPow -= g;
        if (jumpPow <= 0) {
            jumpPow = 7;
            jump = false;
        }
    }

    public void FindButtomBlock() {
        if (level == null) {
            return;
        }
        ButtomBlock = null;
        int Ymin = Integer.MAX_VALUE;
        for (Obstycle ob : level) {
            if (!ob.Enemy) {
                if ((x + wid >= ob.getX() && x <= ob.getX() + ob.getWid())
                        && (y + hig <= ob.getY())) {
                    if (Math.abs(y - ob.getY()) < Ymin) {
                        Ymin = (int) Math.abs(y - ob.getY());
                        ButtomBlock = ob;
                        if (sayac % 60 == 0) {
                            ob.color = new Color(rand.nextInt(0, 254), rand.nextInt(0, 254), rand.nextInt(0, 254));
                            sayac = 0;
                        }
                    }
                }
            }
            if (Right && x >= (int) BoardWidth * 0.7) {
                ob.MoveLeft();
            } else if (Left && x <= (int) (BoardWidth * 0.02)) {
                ob.MoveRight();
            }
        }
        sayac++;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(img[ImgC].getImage(), x, (int) y, null);
        if (atack) {
            Attack();
            secCount++;
            if (ImgC == 2) {
                drawInterval = 40;
            } else {
                drawInterval = 20;
            }
            if (secCount % drawInterval == 0) {
                ImgC += 1;
                if (ImgC == 5) {
                    for (Obstycle ob : level) {
                        ob.hit = false;
                    }
                    ImgC = 0;
                    atack = false;
                }
                secCount = 0;
            }
        }
        /*
        g2d.setColor(Color.RED);
        g2d.fillRect(x, (int) y, wid, hig);
         */
    }

    public void Attack() {
        for (Obstycle ob : level) {
            if (!ob.Enemy) {
                continue;
            }
            if ((ob.getX() < x + img[0].getIconWidth() && ob.getX() > x)
                    && (ob.getY() + ob.getHig() > y && ob.getY() < y + img[0].getIconHeight())) {
                if (!ob.hit) {
                    ob.healt -= 20;
                    ob.hit = true;
                }
            }
        }

    }

}
