package modul2.HamsterSim;

import javax.swing.*;
// zuständig für das externe fenster
public class Window extends JFrame {
    private SpielfeldPanel panel;
    public Window(Plains plains) {
        setTitle("Hamster Sim 🐹"); // titel des externen fensters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // was passiert wenn man das fenster schließt -> standard stop funktion für das programm
        setResizable(false);  // bestimmt ob man das fenster manuell vergrößern kann -> standard false

        panel = new SpielfeldPanel(plains);  // ruft den konstruktor plains auf
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Timer für Ticks
        new Timer(300, e -> {  // die geschwindigkeit mit der sich alles bewegt -> z.b. 100 = sehr schnell, 1000 = langsam usw
            plains.tick();
            panel.repaint();
        }).start();
    }
}
