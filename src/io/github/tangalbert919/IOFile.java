package io.github.tangalbert919;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class IOFile {
	
	private static String file;
	private double GPA;
	private double GPA2;
	private int lines;
	DecimalFormat format = new DecimalFormat("0.00");
	
	public IOFile() {
		file = "";
	}
	public IOFile(String string) {
		file = string;
	}
	public void rwFile() throws IOException {
		
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
		input.close();
		output.close();
	}
	public double calculateGPA(double grade, double multiplier) { // Weighted GPA
		double amount = grade * multiplier;
		GPA = GPA + amount;
		return amount;
	}
	public double calculateGPA2(double grade) { // Unweighted GPA
		GPA2 = GPA2 + grade;
		return grade;
	}
	public double calculateAverage() {
		GPA = GPA / lines;
		return GPA;
	}
	public double calculateAverage2() {
		GPA2 = GPA2 / lines;
		return GPA2;
	}
}
