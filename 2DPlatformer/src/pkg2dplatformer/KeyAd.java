package pkg2dplatformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAd extends KeyAdapter {

    Player player;

    KeyAd(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_A:
                player.Right = false;
                player.Left = true;
                break;
            case KeyEvent.VK_D:
                player.Right = true;
                player.Left = false;
                break;
            case KeyEvent.VK_ENTER:
                player.atack = true;
                break;
            case KeyEvent.VK_J:
                if (player.jumpCount <= 0) {
                    return;
                }
                player.jumpCount--;
                player.gravity = 0.1;
                player.jumpPow = 7;
                player.jump = true;
                player.Jump();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_A:
                player.Left = false;
                break;
            case KeyEvent.VK_D:
                player.Right = false;
                break;
        }

    }

}
