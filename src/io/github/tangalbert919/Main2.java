package io.github.tangalbert919;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI Main class. Use this if we want to compile a GUI version that EVERYONE will use.
 * Developers, please help me out.
 */

public class Main2 {

    public static void main(String args[]) {
        GPACalcGUI gui = new GPACalcGUI();
        gui.setSize(400,200);
        gui.pack();
        gui.setVisible(true);
    }
}
class GPACalcGUI extends JFrame implements ActionListener {
    JTextField text;
    public GPACalcGUI() {
        super("GPA Calculator v1.30 alpha 2");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // We will just stick to typing the name of the file.
        text = new JTextField(15);
        text.addActionListener(this);

        // The user needs to know what exactly they need to do.
        JLabel label = new JLabel("Type the name of the file here.");

        // The user needs an example.
        JLabel label2 = new JLabel("Example: template.txt");

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(text);
        panel.add(label2);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = text.getText();
        IOFile file = new IOFile(filename);
        file.rwFile();
    }
}
