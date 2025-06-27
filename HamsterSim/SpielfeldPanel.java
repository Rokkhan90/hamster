package modul2.HamsterSim;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;



public class SpielfeldPanel extends JPanel {
    private final Plains plains;
    private final int tileSize = 64; // groesse der Tiles

    // Sprites (je 512x512, im Code aber skaliert auf 64x64)
    private final BufferedImage feld;
    private final BufferedImage saetzling;
    private final BufferedImage hamsterNormal;
    private final BufferedImage hamsterWuetend;

    public SpielfeldPanel(Plains plains) {
        this.plains = plains;

        // Sprite-Koordinaten im 2x2-Raster des SpriteSheets:
        feld           = SpriteManager.getSprite(0, 0); // oben links
        saetzling      = SpriteManager.getSprite(1, 0); // oben rechts
        hamsterNormal  = SpriteManager.getSprite(0, 1); // unten links
        hamsterWuetend = SpriteManager.getSprite(1, 1); // unten rechts

        int groesse = plains.getGroesse();
        setPreferredSize(new Dimension(groesse * tileSize, groesse * tileSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int groesse = plains.getGroesse();

        // Hintergrund-Felder zeichnen
        for (int y = 0; y < groesse; y++) {
            for (int x = 0; x < groesse; x++) {
                g.drawImage(feld, x * tileSize, y * tileSize, tileSize, tileSize, null);
            }
        }

        // Saetzlinge zeichnen
        for (Saetzling s : plains.getSaetzlinge()) {
            g.drawImage(saetzling, s.getX() * tileSize, s.getY() * tileSize, tileSize, tileSize, null);
        }

        // Hamster zeichnen (wuetend oder normal)
        for (Hamster h : plains.getHamsters()) {
            BufferedImage bild = h.istWuetend() ? hamsterWuetend : hamsterNormal;
            g.drawImage(bild, h.getX() * tileSize, h.getY() * tileSize, tileSize, tileSize, null);
            int anzahl = h.getGesamtGefressen();
            if (anzahl > 0) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 14));

                // Text mittig über dem Hamster platzieren
                String text = String.valueOf(anzahl);
                int textX = h.getX() * tileSize + tileSize / 2 - 5;
                int textY = h.getY() * tileSize - 4;

                g.drawString(text, textX, textY);
            }

        }

    }
}
