package io.github.tangalbert919;

import javax.swing.*;
import java.awt.*;

/**
 * The GUI main class. This uses the GPACalcGUI class that I created.
 * It is inadvisable to edit this class to modify the GUI.
 */

public class Main2 {

    public static void main(String args[]) {
        JFrame gui = new JFrame("GPA Calculator v1.30 alpha 3");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gui.add(new GPACalcGUI());
        gui.setSize(new Dimension(400,200));
        gui.setVisible(true);
    }
}
