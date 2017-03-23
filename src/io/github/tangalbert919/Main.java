package io.github.tangalbert919;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("GPA Calculator v1.01");
		System.out.print("Enter the file name (Ex: output.txt) --> ");
		String input = enterString();
		IOFile file = new IOFile(input);
		file.rwFile();
	}
	public static String enterString() {
		Scanner input = new Scanner(System.in);
		String temp = input.nextLine();
		input.close();
		return temp;
	}
}
