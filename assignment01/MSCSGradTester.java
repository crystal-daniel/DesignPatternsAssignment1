package assignment01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MSCSGradTester {
	Checker mscs = Checker.NullMSCS;
	Checker mscsAI = Checker.NullAI;
	Checker mscsCyber = Checker.NullCyber;
	static Scanner kbd = new Scanner(System.in);
	public boolean graduationCheck() {
		System.out.println("Select checker");
		System.out.println("\t1. MSCS only");
		System.out.println("\t2. MSCS AI track");
		System.out.println("\t3. MSCS Cybersecurity track");
		int choice = 0;
		if (kbd.hasNextInt()) {
			choice = kbd.nextInt();
		} else {
			System.out.println("Invalid input. Please run program again.");
			System.exit(0);
		}
		if (choice < 1 || choice > 3) {
			System.out.println("You must select an entry 1, 2, or 3");
			System.exit(0);
		}
		kbd.nextLine(); // consume the end-of-line character
		System.out.println("Paste in the comma-separated list of courses that were passed");		
		String input = kbd.nextLine();
		input = input.replace("[", "");
		input = input.replace("]", "");
		input = input.replace("{", "");
		input = input.replace("}", "");
		input = input.replace("(", "");
		input = input.replace(")", "");
		String[] courses = input.split(",");
		for(int i = 0; i < courses.length; i++)
			courses[i] = courses[i].replace(" ", "").toUpperCase();
		List<String> uniqueCourses = new ArrayList<>(Arrays.asList(courses)); 
		switch(choice) {
		case 1:
			mscsAI = Checker.NullAI;
			mscsCyber = Checker.NullCyber;
			break;
		case 2:
			mscsAI = new MSCSAIChecker();
			mscsCyber = Checker.NullCyber;
			break;
		case 3:
			mscsCyber = new MSCSCyberChecker();
			mscsAI = Checker.NullAI;
		}
		mscs = new MSCSChecker();
		boolean b1 = mscs.satisfies(uniqueCourses);
		boolean b2 = mscsAI.satisfies(uniqueCourses);
		boolean b3 = mscsCyber.satisfies(uniqueCourses);
		return b1 && b2 && b3;
	}
	public static void main(String[] args) {
		MSCSGradTester test = new MSCSGradTester();
		char response = 'Y';
		while (response == 'Y') {
			boolean check = test.graduationCheck();
			if(check)
				System.out.println("The courses taken meet the course distribution requirements.\n"
					+ "GPA, graduate residence, project or thesis completion must all be checked separately.\n"
					+ "4+1 completion must also be checked separately");
			else
				System.out.println("Please read the messages above.");
			System.out.println("--------------------------------");
			System.out.println("Do you want another test (Y/N)?");
			response = kbd.nextLine().toUpperCase().charAt(0);
		}
		System.out.println("DONE");
	}
}