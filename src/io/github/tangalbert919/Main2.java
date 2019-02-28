package io.github.tangalbert919;

import javax.swing.*;
import java.awt.*;

/**
 * The GUI main class. This uses the GPACalcGUI class that I created.
 * It is inadvisable to edit this class to modify the GUI.
 */

public class Main2 {

    public static void main(String args[]) {
        // We need to create a JFrame for this.
        JFrame gui = new JFrame("GPA Calculator v1.40");
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        HelpFile.generateFile();
        TemplateFile.generateFile();

        // The JPanel needs to be added, and the GUI needs a size.
        gui.add(new GPACalcGUI());
        gui.setSize(new Dimension(400,200));
        gui.setResizable(false);
        gui.setVisible(true);
    }
}
