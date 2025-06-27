package modul2.HamsterSim;

import java.util.Random;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        int anzahlHamster = 1 + new Random().nextInt(5); //bestimmt die maximale anzahl der hamster
        Plains plains = new Plains(10, anzahlHamster); // bestimmt die größe des feldes

        for (int i = 0; i < 1 + new Random().nextInt(6); i++) { // legt fest wie viele saetzlinge(1-6) zufaellig spawnen
            plains.spawnSaetzling();
        }

        SwingUtilities.invokeLater(() -> new Window(plains));
    }
}
