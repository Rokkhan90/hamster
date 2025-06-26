package modul2.HamsterSim;

import java.util.ArrayList;
import java.util.List;

public class Hamster {
    private int x, y;       // aktuelle position des hamsters auf dem feld
    private int hunger;     // zähler wie viele runden der hamster nichts gegessen hat
    private final List<Saetzling> backen = new ArrayList<>(); // gesammelte sätzlinge in den backen
    private final Plains plains; // referenz auf das feld

    // erstellt einen hamster an gegebener position des felds und setzt hunger auf 0
    public Hamster(int x, int y, Plains plains) {
        this.x = x;
        this.y = y;
        this.plains = plains;
        this.hunger = 0;
    }

    //  wird in jeder runde aufgerufen und macht den hamster hungrig
    public void tick() {
        hunger++;

        Saetzling s = plains.getSaetzlingAt(x, y);
        if (s != null) {
            if (backen.size() < 4) {  // hamster kann maximal 4 sätzlinge in den backen halten
                backen.add(s);
            }
            plains.removeSaetzling(s);  // entfernt setzling vom spielfeld
            hunger = 0; // hamster ist jetzt wieder satt
            return;
        }
        // bestimmt richtung: entweder aggressiv wenn hungrig zum nächsten sätzling oder zufällig
        Richtung richtung = istWuetend()
                ? plains.getRichtungZuNaechstemSaetzling(x, y)
                : Richtung.zufaellig();

        bewege(richtung); // bewegt sich  in diese richtung wenn möglich
    }
// versucht den hamster in die angegebene richtung zu bewegen sofern es sich in der feldgrenze liegt
    private void bewege(Richtung r) {
        int neuesX = x + r.dx;
        int neuesY = y + r.dy;
        if (neuesX >= 0 && neuesX < plains.getGroesse()
                && neuesY >= 0 && neuesY < plains.getGroesse()) {
            x = neuesX;
            y = neuesY;
        }
    }
    // gibt zurück ob der hamster wütend ist, wenn er länger nichts gegessen hat oder die backen leer sind wird er wütend
    public boolean istWuetend() {
        return hunger > 5 && backen.isEmpty();
    }

    public int getX() { return x; }
    public int getY() { return y; }

    // alter code der für die konsolenausgabe war
    //public String gibIcon() {
     //   return istWuetend() ? "🤬" : "🐹";
   // }
}
