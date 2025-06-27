package modul2.HamsterSim;

import java.util.*;

public class Plains {
    private final int groesse;
    private final int maxHamster;
    private final List<Hamster> hamsters = new ArrayList<>();
    private final List<Saetzling> saetzlinge = new ArrayList<>();
    private final Random rand = new Random();

    public Plains(int groesse, int maxHamster) {
        this.groesse = groesse;
        this.maxHamster = maxHamster;
        for (int i = 0; i < maxHamster; i++) {
            spawnHamster();
        }
    }

    public int getGroesse() {
        return groesse;
    }

    public List<Hamster> getHamsters() {
        return hamsters;
    }

    public List<Saetzling> getSaetzlinge() {
        return saetzlinge;
    }

    public void spawnHamster() {
        int x = rand.nextInt(groesse);
        int y = rand.nextInt(groesse);
        if (getHamsterAt(x, y) == null && getSaetzlingAt(x, y) == null) {
            hamsters.add(new Hamster(x, y, this));
        }
    }

    public void spawnSaetzling() {
        int x = rand.nextInt(groesse);
        int y = rand.nextInt(groesse);
        if (getHamsterAt(x, y) == null && getSaetzlingAt(x, y) == null) {
            saetzlinge.add(new Saetzling(x, y));
        }
    }

    public void tick() {
        for (Hamster h : hamsters) {
            h.tick();
        }

        if (saetzlinge.size() < 6 && rand.nextDouble() < 0.3) {  //  geschwindigkeit der saetzlinge (spawnrate 30%)
            spawnSaetzling();                                                           // wenn weniger als 6 auf dem feld sind etc
        }
    }

    public Hamster getHamsterAt(int x, int y) {
        for (Hamster h : hamsters) {
            if (h.getX() == x && h.getY() == y) return h;
        }
        return null;
    }

    public Saetzling getSaetzlingAt(int x, int y) {
        for (Saetzling s : saetzlinge) {
            if (s.getX() == x && s.getY() == y) return s;
        }
        return null;
    }

    public void removeSaetzling(Saetzling s) {
        saetzlinge.remove(s);
    }

    public Richtung getRichtungZuNaechstemSaetzling(int x, int y) {
        if (saetzlinge.isEmpty()) return Richtung.zufaellig();
        Saetzling ziel = saetzlinge.get(0);
        int dx = ziel.getX() - x;
        int dy = ziel.getY() - y;
        return Richtung.ausDelta(dx, dy);
    }
}
