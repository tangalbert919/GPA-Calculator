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
    static JTextArea textarea;
    GPACalcGUI() {
        super(new GridBagLayout());

        // We will just stick to typing the name of the file.
        text = new JTextField(15);
        text.addActionListener(this);

        // The user needs to know what exactly they need to do.
        JLabel label = new JLabel("Type the name of the file here. Then press Enter.");

        // The user needs an example, and where the output will be.
        JLabel label2 = new JLabel("Example: template.txt");
        JLabel label3 = new JLabel("Your results will be in \"GPA.txt\".");

        // We need a text area for output.
        textarea = new JTextArea(5, 15);
        JScrollPane scrollPane = new JScrollPane(textarea);

        // The stuff above needs to be displayed. For this, we use GridBagConstraints to get it done.
         GridBagConstraints c = new GridBagConstraints();
         c.gridwidth = GridBagConstraints.REMAINDER;

         c.fill = GridBagConstraints.HORIZONTAL;
         add(label, c);
         add(text, c);
         add(label2, c);
         add(label3, c);
         add(scrollPane, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = text.getText();
        switch (filename) {
            case "info":
                textarea.append("GPA Calculator v1.40");
                break;
            case "help":
                textarea.append("Type the name of the file within the text field above. Then press Enter.");
                textarea.append("Make sure you add \".txt\" at the end of the file name, or the program will output an error.");
                textarea.append("See the \"howto.txt\" file on how to make your file properly.");
                break;
            default:
                IOFile file = new IOFile(filename, "GUI");
                file.rwFile();
                textarea.append("GPA.txt file created.");
                break;
        }
    }
}
