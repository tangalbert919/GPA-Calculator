package io.github.tangalbert919;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * All of the calculations are handled in this class.
 */
public class IOFile {
	
	private static String file;
	private static String instance;
	private double GPA;
	private double GPA2;
	private double Credits;
	private double Credits2;
	private int lines;
	private DecimalFormat format = new DecimalFormat("0.00");
	private DecimalFormat format2 = new DecimalFormat("0.000");
	
	public IOFile() { // Default constructor
		file = "";
	}
	public IOFile(String string) { // Parameter constructor (< v1.30 alpha 2)
		file = string;
	}
	// v1.40
	public IOFile(String string, String stringtwo) {
	    file = string;
	    instance = stringtwo;
    }
	void rwFile() {
		try {
		    // Input and output streams are built with the Scanner and BufferedWriter classes.
            Scanner input = new Scanner(new File(file));
            BufferedWriter output = new BufferedWriter(new FileWriter("GPA.txt"));

            // We need to figure out how many classes there are from the first line of the text file.
            lines = input.nextInt();
            input.nextLine();

            // Define these variables. They are assigned a value in the "while" loop.
            double grade;
            double multiplier;
            int honors;

            // Run this loop until we have gone through the number of defined lines, starting at 0.
            while (input.hasNext())
                for (int i = 0; i < lines; i++) {
                    // Assign the variables now.
                    grade = input.nextDouble();
                    multiplier = input.nextDouble();
                    honors = input.nextInt();
                    // Calculate and add the total credits and percentage grades.
                    calculateCredits(grade, honors);
                    calculateGPA(grade,multiplier);

                    // Integer "j" is used to tell the user which class they have what grade in.
                    int j = i + 1;
                    String k = format.format(grade * multiplier);

                    // This method is only executed for debugging reasons. Everything else, comment it.
                    // System.out.println(grade + " " + multiplier);

                    // Display the grade to the user and write it to the text file.
                    if (instance.equals("Command"))
                        System.out.println("Grade in class " + j + " is: " + k + " (" + grade + " without multiplier).");
                    else
                        GPACalcGUI.textarea.append("Grade in class " + j + " is: " + k + " (" + grade + " without multiplier). \n");

                    output.write("Grade in class " + j +  " is " + k + " (" + grade + " without multiplier).");

                    // Create a new line for the output file, and go to the next file for the input file. Not doing this causes errors.
                    output.newLine();
                    input.nextLine();
                }

            // Calculate the average weighted, unweighted, and credit GPA.
            calculateAverage();

            // Display the results to the user, and then write it to the text file afterwards.
            if (instance.equals("Command")) {
                System.out.println("Average weighted GPA is: " + format.format(GPA));
                System.out.println("Average unweighted GPA is: " + format.format(GPA2));
                System.out.println("Average weighted credit GPA is: " + format.format(Credits));
                System.out.println("Average unweighted credit GPA is: " + format.format(Credits2));
            }
            else {
                GPACalcGUI.textarea.append("Average weighted GPA is: " + format.format(GPA) + "\n");
                GPACalcGUI.textarea.append("Average unweighted GPA is: " + format.format(GPA2) + "\n");
                GPACalcGUI.textarea.append("Average weighted credit GPA is: " + format.format(Credits) + "\n");
                GPACalcGUI.textarea.append("Average unweighted credit GPA is: " + format.format(Credits2) + "\n");
            }
            output.write("Average weighted GPA is: " + format.format(GPA) + "\n" +
                    "Average unweighted GPA is: " + format.format(GPA2) + "\n" +
                    "Average weighted credit GPA is: " + format.format(Credits) + "\n" +
                    "Average unweighted credit GPA is: " + format.format(Credits2) + "\n");

            // Close the input and output streams to prevent resource leak.
            input.close();
            output.close();

            // Compare the results, and then write them to the text file afterwards.
            compareLast(GPA, GPA2, Credits, Credits2);
            writeResults(format.format(GPA), format.format(GPA2), format.format(Credits), format.format(Credits2));
		} catch (IOException e) {
		    if (instance.equals("Command")) {
                errorFile();
                System.out.println("Sorry, but we can't find the file specified.");
                System.out.println("Perhaps you misspelled the file or forgot the \".txt\" ending.");
                System.out.println("Please re-run this program and try again.");
            }
		    else {
		        GPACalcGUI.textarea.append("Sorry, but we can't find the file specified.");
		        GPACalcGUI.textarea.append("Perhaps you misspelled the file or forgot the \".txt\" ending.");
		        GPACalcGUI.textarea.append("Check for typos and try again.");
            }
        }
	}
	private void errorFile() {
	    try {
            BufferedWriter output = new BufferedWriter(new FileWriter("GPA.txt"));
            output.write("Sorry, but we can't find the file specified. \n");
            output.write("Perhaps you misspelled the file or forgot the \".txt\" ending. \n");
            output.write("Please re-run this program and try again.");
            output.close();
        } catch (IOException e) {
	        System.out.println("This is just impossible.");
        }

    }
	private void writeResults(String i, String j, String k, String l) throws IOException { // Write results to new file.
	    BufferedWriter output = new BufferedWriter(new FileWriter("LastTime.txt"));
	    output.write(i + " " + j + " " + k + " " + l);
	    output.close();
    }
    private void compareLast(double i, double j, double k, double l) { // Compare current results to results from last run
	    try {
	        /*
	         * Using decimal format turns number into String. Using parseDouble() should convert these
	         * numbers back to numbers.
	         */
	        i = Double.parseDouble(format.format(i));
	        j = Double.parseDouble(format.format(j));
	        // i and j are for percentage GPA
	        double i3; // Holds percentage gain/loss from weighted GPA
	        double j3; // Holds percentage gain/loss from unweighted GPA
            double i4; // Holds point gain/loss from weighted GPA
            double j4; // Holds point gain/loss from unweighted GPA
            // k and l are for credit GPA
            double k3; // Holds percentage gain/loss from weighted GPA
            double l3; // Holds percentage gain/loss from unweighted GPA
            double k4; // Holds point gain/loss from weighted GPA
            double l4; // Holds point gain/loss from unweighted GPA
            Scanner input = new Scanner(new File("LastTime.txt"));
            double i2 = input.nextDouble();
            double j2 = input.nextDouble();
            double k2 = input.nextDouble();
            double l2 = input.nextDouble();
            // Percentage GPA
            if (i > i2) { // Current weighted GPA is better than the last one.
                i3 = ((i / i2) * 100) - 100;
                i4 = i - i2;
                System.out.println("Your weighted GPA improved by " + format2.format(i3) + "% (" + format.format(i4) + " points).");
            }
            else if (i < i2) { // Current weighted GPA is worse than the last one.
                i3 = ((i2 / i) *100) - 100;
                i4 = i2 - i;
                System.out.println("Your weighted GPA dropped by " + format2.format(i3) + "% (" + format.format(i4) + " points).");
            }
            else if (i == i2) // Current weighted GPA is the same as the last one.
                System.out.println("Your weighted GPA did not improve nor drop.");

            if (j > j2) { // Current unweighted GPA is better than the last one.
                j3 = ((j / j2) * 100) - 100;
                j4 = j - j2;
                System.out.println("Your unweighted GPA improved by " + format2.format(j3) + "% (" + format.format(j4) + " points).");
            }
            else if (j < j2) { // Current unweighted GPA is worse than the last one.
                j3 = ((j2 / j) * 100) - 100;
                j4 = j2 - j;
                System.out.println("Your unweighted GPA dropped by " + format2.format(j3) + "% (" + format.format(j4) + " points).");
            }
            else if (j == j2) // Current unweighted GPA is the same as the last one.
                System.out.println("Your unweighted GPA did not improve nor drop.");

            // Credit GPA
            if (k > k2) { // Current weighted GPA is better than the last one.
                k3 = ((k / k2) * 100) - 100;
                k4 = k - k2;
                System.out.println("Your weighted credit GPA improved by " + format.format(k3) + "% (" + format.format(k4) + " points).");
            }
            else if (k < k2) { // Current weighted GPA is worse than the last one.
                k3 = ((k2 / k) * 100) - 100;
                k4 = k2 - k;
                System.out.println("Your weighted credit GPA dropped by " + format.format(k3) + "% (" + format.format(k4) + " points).");
            }
            else if (k == k2) // Current weighted GPA is the same as the last one.
                System.out.println("Your weighted credit GPA did not improve nor drop.");

            if (l > l2) { // Current unweighted GPA is better than the last one.
                l3 = ((l / l2) * 100) - 100;
                l4 = l - l2;
                System.out.println("Your unweighted credit GPA improved by " + format.format(l3) + "% (" + format.format(l4) + " points).");
            }
            else if (l < l2) { // Current unweighted GPA is worse than the last one.
                l3 = ((l2 / l) * 100) - 100;
                l4 = l2 - l;
                System.out.println("Your unweighted credit GPA dropped by " + format.format(l3) + "% (" + format.format(l4) + " points).");
            }
            else if (l == l2) // Current unweighted GPA is the same as the last one.
                System.out.println("Your unweighted credit GPA did not improve nor drop.");
            input.close();
        } catch (FileNotFoundException e) { // Run this if we can't find "LastTime.txt".
	        System.out.println("This seems to be your first test run!");
	        System.out.println("Your last results will be in \"LastTime.txt\".");
        }
    }
	private void calculateGPA(double grade, double multiplier) { // Add up the grades for both weighted and unweighted GPA
		double amount = grade * multiplier;
		GPA += amount;
		GPA2 += grade;
	}
	private void calculateAverage() { // Calculate the average weighted and unweighted GPA
		GPA = GPA / lines;
		GPA2 = GPA2 / lines;
		Credits = Credits / lines;
		Credits2 = Credits2 / lines;
	}
	// This method is overly complex. We need to simplify this.
    private void calculateCredits(double grade, double honors) { // Add credits for each class.
	    if (grade <= 100 && grade >= 94) { // A+ or A
            Credits += 4.0;
            Credits2 += 4.0;
        }
        else if (grade < 94 && grade >= 90) { // A-
            Credits += 3.7;
            Credits2 += 3.7;
        }
        else if (grade < 90 && grade >= 87) { // B+
            Credits += 3.3;
            Credits2 += 3.3;
        }
        else if (grade < 87 && grade >= 84) { // B
            Credits += 3.0;
            Credits2 += 3.0;
        }
        else if (grade < 84 && grade >= 80) { // B-
            Credits += 2.7;
            Credits2 += 2.7;
        }
        else if (grade < 80 && grade >= 77) { // C+
            Credits += 2.3;
            Credits2 += 2.3;
        }
        else if (grade < 77 && grade >= 74) { // C
            Credits += 2.0;
            Credits2 += 2.0;
        }
        else if (grade < 74 && grade >= 70) { // C-
            Credits += 1.7;
            Credits2 += 1.7;
        }
        else if (grade < 70 && grade >= 67) { // D+
            Credits += 1.3;
            Credits2 += 1.3;
        }
        else if (grade < 67 && grade >= 64) { // D
            Credits += 1.0;
            Credits2 += 1.0;
        }
        else if (grade < 64 && grade >= 60) { // D-
            Credits += 0.7;
            Credits2 += 0.7;
        }
        else if (grade < 60) { // F
            Credits += 0;
            Credits2 += 0;
        }

        if (honors == 1)
            Credits += 0.5;
        if (honors == 2)
            Credits += 1.0;
    }
}
