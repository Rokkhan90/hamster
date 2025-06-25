package modul2.HamsterSim;

import java.util.*;

public enum Richtung {
    OBEN(0, -1), UNTEN(0, 1), LINKS(-1, 0), RECHTS(1, 0);

    public final int dx, dy;

    Richtung(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Richtung zufaellig() {
        Richtung[] werte = values();
        return werte[new Random().nextInt(werte.length)];
    }

    public static Richtung ausDelta(int dx, int dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? RECHTS : LINKS;
        } else {
            return dy > 0 ? UNTEN : OBEN;
        }
    }
}
