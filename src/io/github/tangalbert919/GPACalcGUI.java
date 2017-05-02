package io.github.tangalbert919;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Please edit this class to modify the GUI. Requires a bit more advanced Java knowledge to perform.
 */
public class GPACalcGUI extends JPanel implements ActionListener {
    private JTextField text;
    GPACalcGUI() {
        super(new GridBagLayout());

        // We will just stick to typing the name of the file.
        text = new JTextField(15);
        text.addActionListener(this);

        // The user needs to know what exactly they need to do.
        JLabel label = new JLabel("Type the name of the file here. Then press Enter.");

        // The user needs an example.
        JLabel label2 = new JLabel("Example: template.txt");
        JLabel label3 = new JLabel("Your results will be in \"GPA.txt\".");

        // The stuff above needs to be displayed. For this, we use JPanel to get it done.
         GridBagConstraints c = new GridBagConstraints();
         c.gridwidth = GridBagConstraints.REMAINDER;

         c.fill = GridBagConstraints.HORIZONTAL;
         add(label, c);
         add(text, c);
         add(label2, c);
         add(label3, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = text.getText();
        IOFile file = new IOFile(filename);
        file.rwFile();
    }
}
