package modul2.HamsterSim;

import java.util.*;

public class Hamster {
    private int x, y;
    private int hunger;
    private final List<Saetzling> backen = new ArrayList<>();
    private final Plains plains;

    public Hamster(int x, int y, Plains plains) {
        this.x = x;
        this.y = y;
        this.plains = plains;
        this.hunger = 0;
    }

    public void tick() {
        hunger++;

        Saetzling s = plains.getSaetzlingAt(x, y);
        if (s != null) {
            if (backen.size() < 4) {
                backen.add(s);
            }
            plains.removeSaetzling(s);
            hunger = 0;
            return;
        }

        Richtung richtung = istWuetend()
                ? plains.getRichtungZuNaechstemSaetzling(x, y)
                : Richtung.zufaellig();

        bewege(richtung);
    }

    private void bewege(Richtung r) {
        int neuesX = x + r.dx;
        int neuesY = y + r.dy;
        if (neuesX >= 0 && neuesX < plains.getGroesse()
                && neuesY >= 0 && neuesY < plains.getGroesse()) {
            x = neuesX;
            y = neuesY;
        }
    }

    public boolean istWuetend() {
        return hunger > 5 && backen.isEmpty();
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public String gibIcon() {
        return istWuetend() ? "🤬" : "🐹";
    }
}
