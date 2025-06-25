package modul2.HamsterSim;
import java.util.*;

public class Hamster {
    private int x, y;
    private int hunger;
    private List<Saetzling> backen = new ArrayList<>();
    private Plains plains;

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

        if (istWuetend()) {
            Richtung richtung = plains.getRichtungZuNaechstemSaetzling(x, y);
            bewege(richtung);
        } else {
            Richtung r = Richtung.zufaellig();
            bewege(r);
        }
    }

    private void bewege(Richtung r) {
        x += r.dx;
        y += r.dy;
        x = Math.max(0, Math.min(plains.getGroesse() - 1, x));
        y = Math.max(0, Math.min(plains.getGroesse() - 1, y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean istWuetend() {
        return hunger > 5 && backen.isEmpty();
    }
}
