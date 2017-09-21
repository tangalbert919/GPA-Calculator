package io.github.tangalbert919;

import java.io.IOException;
import java.util.Scanner;

/**
 * The command-line main class. Primarily used.
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("GPA Calculator v1.40");
		System.out.println("If you need help, check out the \"HOWTO.txt\" file.");
		HelpFile.generateFile();
		System.out.print("Enter the file name (Ex: input.txt) --> ");
		String input = enterString();
		IOFile file = new IOFile(input, "Command");
		file.rwFile();
	}
	private static String enterString() {
		Scanner input = new Scanner(System.in);
		String temp = input.nextLine();
		input.close();
		return temp;
	}
}
