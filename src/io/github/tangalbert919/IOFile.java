package io.github.tangalbert919;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class IOFile {
	
	private static String file;
	private double GPA;
	private double GPA2;
	private double Credits;
	private int lines;
	private DecimalFormat format = new DecimalFormat("0.00");
	
	public IOFile() {
		file = "";
	}
	public IOFile(String string) {
		file = string;
	}
	public void rwFile() {
		try {
            Scanner input = new Scanner(new File(file));
            BufferedWriter output = new BufferedWriter(new FileWriter("GPA.txt"));

            lines = input.nextInt();
            input.nextLine();

            double grade;
            double multiplier;

            while (input.hasNext())
                for (int i = 0; i < lines; i++) {
                    grade = input.nextDouble();
                    multiplier = input.nextDouble();
                    calculateCredits(grade);
                    int j = i + 1;
                    System.out.println(grade + " " + multiplier);
                    System.out.println("Grade in class " + j + " is: " + format.format(calculateGPA(grade,multiplier)) + " (" + grade + " without multiplier).");
                    output.write("Grade in class " + j +  " is " + format.format(grade * multiplier) + " (" + calculateGPA2(grade) + " without multiplier).");
                    output.newLine();
                    input.nextLine();
                }
            calculateAverage();
            calculateAverage2();
            System.out.println("Average weighted GPA is: " + format.format(GPA));
            System.out.println("Average unweighted GPA is: " + format.format(GPA2));
            output.write("Average weighted GPA is: " + format.format(GPA));
            output.newLine();
            output.write("Average unweighted GPA is: " + format.format(GPA2));
            output.newLine();
            input.close();
            output.close();
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
            Scanner input = new Scanner(new File("LastTime.txt"));
            double i2 = input.nextDouble();
            double j2 = input.nextDouble();
            if (i > i2) { // Current weighted GPA is better than the last one.
                i3 = i / i2;
                System.out.println("Your weighted GPA improved by " + format.format(i3) + "%.");
            }
            else if (i < i2) { // Current weighted GPA is worse than the last one.
                i3 = i2 / i;
                System.out.println("Your weighted GPA dropped by " + format.format(i3) + "%.");
            }
            else if (i == i2) // Current weighted GPA is the same as the last one.
                System.out.println("Your weighted GPA did not improve nor drop.");
            if (j > j2) { // Current unweighted GPA is better than the last one.
                j3 = j / j2;
                System.out.println("Your unweighted GPA improved by " + format.format(j3) + "%.");
            }
            else if (j < j2) { // Current unweighted GPA is better than the last one.
                j3 = j2 / j;
                System.out.println("Your unweighted GPA dropped by " + format.format(j3) + "%.");
            }
            else if (j == j2) // Current unweighted GPA is the same as the last one.
                System.out.println("Your unweighted GPA did not improve nor drop.");
            input.close();
        } catch (FileNotFoundException e) { // Run this if we can't find "LastTime.txt".
	        System.out.println("This seems to be your first test run!");
	        System.out.println("Your last results will be in \"LastTime.txt\".");
        }
    }
	private double calculateGPA(double grade, double multiplier) { // Weighted GPA
		double amount = grade * multiplier;
		GPA = GPA + amount;
		return amount;
	}
	private double calculateGPA2(double grade) { // Unweighted GPA
		GPA2 = GPA2 + grade;
		return grade;
	}
	private double calculateAverage() { // Calculate the average from weighted GPA
		GPA = GPA / lines;
		return GPA;
	}
	private double calculateAverage2() { // Calculate the average from unweighted GPA
        GPA2 = GPA2 / lines;
        return GPA2;
    }
    private double calculateCredits(double grade) {
	    if (grade <= 100 && grade >= 94)  // A+ or A
	        Credits += 4.0;
        if (grade < 94 && grade >= 90) // A-
	        Credits += 3.7;
        if (grade >= 87 && grade < 90) // B+
            Credits += 3.3;
        return Credits;
    }
}
