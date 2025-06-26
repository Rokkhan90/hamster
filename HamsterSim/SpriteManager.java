package modul2.HamsterSim;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteManager {
    private static BufferedImage sheet;
    private static final int SPRITE_WIDTH = 512;
    private static final int SPRITE_HEIGHT = 512;


    static {
        try {
            sheet = ImageIO.read(SpriteManager.class.getResource("/modul2/HamsterSim/ressourcen/spritesheet.png"));  // ladet spritesheet aus dem ressourcen ordner

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage getSprite(int spalte, int zeile) {
        return sheet.getSubimage(
                spalte * SPRITE_WIDTH,
                zeile * SPRITE_HEIGHT,
                SPRITE_WIDTH,
                SPRITE_HEIGHT
        );
    }

}

