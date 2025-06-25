package modul2.HamsterSim;
import java.util.*;

public class Plains {
    private int groesse;
    private List<Hamster> hamsters = new ArrayList<>();
    private List<Saetzling> saetzlinge = new ArrayList<>();
    private Random rand = new Random();

    public Plains(int groesse) {
        this.groesse = groesse;
    }

    public void spawnHamster() {
        int x = rand.nextInt(groesse);
        int y = rand.nextInt(groesse);
        hamsters.add(new Hamster(x, y, this));
    }

    public void spawnSaetzling() {
        int x = rand.nextInt(groesse);
        int y = rand.nextInt(groesse);
        if (getSaetzlingAt(x, y) == null) {
            saetzlinge.add(new Saetzling(x, y));
        }
    }

    public int getGroesse() {
        return groesse;
    }

    public void tick() {
        for (Hamster h : hamsters) {
            h.tick();
        }

        // Neue Sätzlinge wachsen lassen (max. 6 gleichzeitig)
        if (saetzlinge.size() < 6 && rand.nextDouble() < 0.3) {
            spawnSaetzling();
        }
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

    public void printFeld() {
        String[][] grid = new String[groesse][groesse];

        for (int y = 0; y < groesse; y++) {
            for (int x = 0; x < groesse; x++) {
                grid[y][x] = "🧱";
            }
        }

        for (Saetzling s : saetzlinge) {
            grid[s.getY()][s.getX()] = "🌱";
        }

        for (Hamster h : hamsters) {
            int x = h.getX();
            int y = h.getY();
            String symbol = h.istWuetend() ? "🤬" : "🐹";
            grid[y][x] = symbol;
        }

        for (int y = 0; y < groesse; y++) {
            for (int x = 0; x < groesse; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }

        System.out.println();
    }
}
