package io.github.tangalbert919;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class just generates the help file the first time the program is run.
 * Added in v1.40
 */
class HelpFile {
    static void generateFile() {
        try {
            BufferedWriter help = new BufferedWriter(new FileWriter("helpfile.txt"));
            help.write("In your file, you need to follow these guidelines so the program doesn't return errors: \n" + "\n" +
                    "1. In the first line of the file, specify how many classes you have. This should just be a number. \n" + "\n" +
                    "2. For the next few lines, those will represent the grade you have in each class. For each line, put in 3 numbers: \n" + "\n" +
                    "The first one is your current grade in your first class. The next number is the multiplier. The last number is if you\'re doing Honors or AP. \n" + "\n" +
                    "The numbers can have decimals (except for the last number). Put in 0 for regular, 1 for Honors, and 2 for AP/IB. \n" + "\n" +
                    "For the second number, do NOT put in \"x\" at the end or the beginning, or the program will fail. \n" + "\n" +
                    "Follow this format for all of your classes. Make sure there is an extra line at the end of the file or the program will fail. \n");
            help.close();
        } catch (IOException e) {
            System.out.println("This is not possible...");
        }
    }
}
