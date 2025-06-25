package modul2.HamsterSim;

public class Saetzling {
    private final int x, y;

    public Saetzling(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public String gibIcon() {
        return "🌱";
    }
}
