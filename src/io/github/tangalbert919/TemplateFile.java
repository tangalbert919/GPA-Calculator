package io.github.tangalbert919;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class just generates the template file the first time the program is run.
 * Added in v1.40
 */
class TemplateFile {
    static void generateFile() {
        try {
            BufferedWriter template = new BufferedWriter(new FileWriter("template.txt"));
            template.write("7\n" +
                    "100 1.00 0\n" +
                    "42 1.17 1\n" +
                    "87 1.05 1\n" +
                    "74 1.12 2\n" +
                    "57 1.21 2\n" +
                    "64 1.09 1\n" +
                    "43 1.15 2\n");
            template.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
