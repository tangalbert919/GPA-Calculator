package io.github.tangalbert919;

import javax.swing.*;
import java.awt.*;

/**
 * GUI Main class. Use this if we want to compile a GUI version that EVERYONE will use.
 * Developers, please help me out.
 */

public class Main2 {

    public static void main(String args[]) {
        JFrame frame = new JFrame("GPA Calculator v1.30 alpha 1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel("This is a test GUI.");
        label.setPreferredSize(new Dimension(800,600));
        frame.getContentPane().add(label, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
