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
	private double GPA;
	private double GPA2;
	private double Credits;
	private double Credits2;
	private int lines;
	private DecimalFormat format = new DecimalFormat("0.00");
	private DecimalFormat format2 = new DecimalFormat("0.000");
	
	public IOFile() {
		file = "";
	}
	public IOFile(String string) {
		file = string;
	}
	public void rwFile() {
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

                    // This method is only executed for debugging reasons. Everything else, comment it.
                    // System.out.println(grade + " " + multiplier);

                    // Display the grade to the user and write it to the text file.
                    System.out.println("Grade in class " + j + " is: " + format.format(grade * multiplier) + " (" + grade + " without multiplier).");
                    output.write("Grade in class " + j +  " is " + format.format(grade * multiplier) + " (" + grade + " without multiplier).");

                    // Create a new line for the output file, and go to the next file for the input file. Not doing this causes errors.
                    output.newLine();
                    input.nextLine();
                }

            // Calculate the average weighted, unweighted, and credit GPA.
            calculateAverage();
            calculateCredits2();

            // Display the results to the user, and then write it to the text file afterwards.
            System.out.println("Average weighted GPA is: " + format.format(GPA));
            System.out.println("Average unweighted GPA is: " + format.format(GPA2));
            System.out.println("Average weighted credit GPA is: " + format.format(Credits));
            System.out.println("Average unweighted credit GPA is: " + format.format(Credits2));
            output.write("Average weighted GPA is: " + format.format(GPA));
            output.newLine();
            output.write("Average unweighted GPA is: " + format.format(GPA2));
            output.newLine();
            output.write("Average weighted credit GPA is: " + format.format(Credits));
            output.newLine();
            output.write("Average unweighted credit GPA is: " + format.format(Credits2));
            output.newLine();

            // Close the input and output streams to prevent resource leak.
            input.close();
            output.close();

            // Compare the results, and then write them to the text file afterwards.
            compareLast(GPA, GPA2);
            writeResults(format.format(GPA), format.format(GPA2));
		} catch (IOException e) {
		    System.out.println("Sorry, but we can't find the file specified.");
		    System.out.println("Perhaps you misspelled the file or forgot the \".txt\" ending.");
		    System.out.println("Please re-run this program and try again.");
        }

	}
	private void writeResults(String i, String j) throws IOException { // Write results to new file.
	    BufferedWriter output = new BufferedWriter(new FileWriter("LastTime.txt"));
	    output.write(i + " " + j);
	    output.close();
    }
    private void compareLast(double i, double j) { // Compare current results to results from last run
	    try {
	        /*
	         * Using decimal format turns number into String. Using parseDouble() should convert these
	         * numbers back to numbers.
	         */
	        i = Double.parseDouble(format.format(i));
	        j = Double.parseDouble(format.format(j));
	        double i3; // Holds percentage gain/loss from weighted GPA
	        double j3; // Holds percentage gain/loss from unweighted GPA
            double i4; // Holds point gain/loss from weighted GPA
            double j4; // Holds point gain/loss from unweighted GPA
            Scanner input = new Scanner(new File("LastTime.txt"));
            double i2 = input.nextDouble();
            double j2 = input.nextDouble();
            if (i > i2) { // Current weighted GPA is better than the last one.
                i3 = i / i2;
                i4 = i - i2;
                System.out.println("Your weighted GPA improved by " + format2.format(i3) + "% (" + format.format(i4) + " points).");
            }
            else if (i < i2) { // Current weighted GPA is worse than the last one.
                i3 = i2 / i;
                i4 = i2 - i;
                System.out.println("Your weighted GPA dropped by " + format2.format(i3) + "% (" + format.format(i4) + " points).");
            }
            else if (i == i2) // Current weighted GPA is the same as the last one.
                System.out.println("Your weighted GPA did not improve nor drop.");
            if (j > j2) { // Current unweighted GPA is better than the last one.
                j3 = j / j2;
                j4 = j - j2;
                System.out.println("Your unweighted GPA improved by " + format2.format(j3) + "% (" + format.format(j4) + " points).");
            }
            else if (j < j2) { // Current unweighted GPA is better than the last one.
                j3 = j2 / j;
                j4 = j2 - j;
                System.out.println("Your unweighted GPA dropped by " + format2.format(j3) + "% (" + format.format(j4) + " points).");
            }
            else if (j == j2) // Current unweighted GPA is the same as the last one.
                System.out.println("Your unweighted GPA did not improve nor drop.");
            input.close();
        } catch (FileNotFoundException e) { // Run this if we can't find "LastTime.txt".
	        System.out.println("This seems to be your first test run!");
	        System.out.println("Your last results will be in \"LastTime.txt\".");
        }
    }
	private void calculateGPA(double grade, double multiplier) { // Weighted GPA
		double amount = grade * multiplier;
		GPA += amount;
		GPA2 += grade;
	}
	private void calculateAverage() { // Calculate the average weighted and unweighted GPA
		GPA = GPA / lines;
		GPA2 = GPA2 / lines;
	}
    private double calculateCredits(double grade, double honors) { // Add credits for each class.
	    if (grade <= 100 && grade >= 94) { // A+ or A
            Credits += 4.0;
            Credits2 += 4.0;
        }
        if (grade < 94 && grade >= 90) { // A-
            Credits += 3.7;
            Credits2 += 3.7;
        }
        if (grade < 90 && grade >= 87) { // B+
            Credits += 3.3;
            Credits2 += 3.3;
        }
        if (grade < 87 && grade >= 84) { // B
            Credits += 3.0;
            Credits2 += 3.0;
        }
        if (grade < 84 && grade >= 80) { // B-
            Credits += 2.7;
            Credits += 2.7;
        }
        if (grade < 80 && grade >= 77) { // C+
            Credits += 2.3;
            Credits += 2.3;
        }
        if (grade < 77 && grade >= 74) { // C
            Credits += 2.0;
            Credits += 2.0;
        }
        if (grade < 74 && grade >= 70) { // C-
            Credits += 1.7;
            Credits2 += 1.7;
        }
        if (grade < 70 && grade >= 67) { // D+
            Credits += 1.3;
            Credits += 1.3;
        }
        if (grade < 67 && grade >= 64) { // D
            Credits += 1.0;
            Credits += 1.0;
        }
        if (grade < 64 && grade >= 60) { // D-
            Credits += 0.7;
            Credits += 0.7;
        }
        if (grade < 60) { // F
            Credits += 0;
            Credits2 += 0;
        }
        if (honors == 1)
            Credits += 0.5;
        if (honors == 2)
            Credits += 1.0;
        return Credits;
    }
    private double calculateCredits2() { // Calculate average credit GPA
	    Credits = Credits / lines;
	    return Credits;
    }
}
