package pkg2dplatformer;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevelHolder {

    private static ArrayList<ArrayList<Obstycle>> levels = new ArrayList<>();

    private static ArrayList<String> paths = new ArrayList<>();

    public static int lev = 0;

    public static void addPath(String Path) {
        paths.add(Path);
    }

    public static void createLevels() {
        for (String path : paths) {
            AddLevel(path);
        }
    }

    private static void AddLevel(String filePath) {
        ArrayList<Obstycle> level = new ArrayList<Obstycle>();
        File file = new File(filePath);
        try (FileReader fr = new FileReader(file)) {
            int ch = fr.read();
            int x = 0;
            int y = 0;
            while (ch != -1) {
                ch = fr.read();
                if ((char) ch == 'b') {
                    int startx = x - 2;
                    while ((char) ch == 'b') {
                        x += 2;
                        ch = fr.read();
                    }
                    level.add(new Obstycle(startx * GamePanel.tileSize, y - 2 * GamePanel.tileSize, (x - startx) * GamePanel.tileSize, GamePanel.tileSize * 3, false));
                }
                if ((char) ch == 'E') {
                    int startx = x - 2;
                    while ((char) ch == 'E') {
                        x += 2;
                        ch = fr.read();
                    }
                    level.add(new Obstycle(startx * GamePanel.tileSize, y - 2 * GamePanel.tileSize, (x - startx) * GamePanel.tileSize, GamePanel.tileSize * 3, true));
                }
                if ((char) ch == '\n') {
                    y += 2 * GamePanel.tileSize;
                    x = 0;
                }
                x += 2;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Hata : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Hata : " + ex.getMessage());
        }
        levels.add(level);
    }

    public static void Draw(Graphics2D g2d) {
        ArrayList<Obstycle> level = levels.get(lev);
        for (Obstycle ob : level) {
            if (!(ob.Enemy && ob.healt <= 0)) {
                ob.Draw(g2d);
            }
        }
    }

    public static ArrayList<Obstycle> GetLev() {
        if (levels.size() > 0) {
            return levels.get(lev);
        }
        return null;
    }

}
