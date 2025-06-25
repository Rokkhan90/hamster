package modul2.HamsterSim;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int anzahlHamster = 1 + new Random().nextInt(5);
        Plains plains = new Plains(6, anzahlHamster);

        for (int i = 0; i < 1 + new Random().nextInt(6); i++) {
            plains.spawnSaetzling();
        }

        for (int tick = 0; tick < 1000; tick++) {
            System.out.println("Tick " + tick);
            plains.tick();
            plains.printFeld();
            try { Thread.sleep(1000); } catch (InterruptedException e) { }
        }
    }
}
