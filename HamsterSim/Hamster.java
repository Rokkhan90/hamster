package modul2.HamsterSim;

import java.util.ArrayList;
import java.util.List;

public class Hamster {
    private int x, y;       // aktuelle position des hamsters auf dem feld
    private int hunger;     // zaehler wie viele runden der hamster nichts gegessen hat
    private final List<Saetzling> backen = new ArrayList<>(); // gesammelte saetzlinge in den backen
    private final Plains plains; // referenz auf das feld
    private int gesamtGefressen = 0;

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
            if (backen.size() < 4) {  // hamster kann maximal 4 saetzlinge in den backen halten
                backen.add(s);
            }
                gesamtGefressen++;

            plains.removeSaetzling(s);  // entfernt setzling vom spielfeld
            hunger = 0; // hamster ist jetzt wieder satt
            return;
        }
        // bestimmt richtung: entweder aggressiv wenn hungrig zum naechsten saetzling oder zufaellig
        Richtung richtung = istWuetend()
                ? plains.getRichtungZuNaechstemSaetzling(x, y)
                : Richtung.zufaellig();

        bewege(richtung); // bewegt sich  in diese richtung wenn moeglich
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
    // gibt zurueck ob der hamster wuetend ist, wenn er laenger nichts gegessen hat oder die backen leer sind
    public boolean istWuetend() {
        return hunger > 5 && backen.isEmpty();
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getGesamtGefressen() {
        return gesamtGefressen;
    }
    // alter code der für die konsolenausgabe war
    //public String gibIcon() {
     //   return istWuetend() ? "🤬" : "🐹";
   // }
}
