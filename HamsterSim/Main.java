package modul2.HamsterSim;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Plains plains = new Plains(10); // 10x10 Feld

        // Zufällige Anzahl Hamster (1–5)
        int anzahlHamster = 1 + new Random().nextInt(5);
        for (int i = 0; i < anzahlHamster; i++) {
            plains.spawnHamster();
        }

        // Zufällige Anzahl Sätzlinge (1–6)
        int anzahlSaetzlinge = 1 + new Random().nextInt(6);
        for (int i = 0; i < anzahlSaetzlinge; i++) {
            plains.spawnSaetzling();
        }

        // Simulation starten
        for (int tick = 0; tick < 1000; tick++) {
            System.out.println("Tick " + tick);
            plains.tick();
            plains.printFeld();
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
        }
    }
}
