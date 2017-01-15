package menu;

import java.util.Scanner;

import logicPack.FileReadWrite;

public class Menu {

	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		menu();
	}

	private static void menu() {

		SubMenu submenu = new SubMenu();
		FileReadWrite reader = new FileReadWrite();
		System.out.println("====== WELCOME TO YOUR ORGANIZER ======");
		try {
			submenu.setArray(reader.readingFile());
		} catch (NullPointerException ex) {
			System.out.println("File is empty");
		}
		do {
			try {
				System.out.println("Choose option from the menu:");
				System.out.println("\t\t 1.Add Event.");
				System.out.println("\t\t 2.Print menu.");
				System.out.println("\t\t 3.Change menu.");
				System.out.println("\t\t 4.Delete Event.");
				System.out.println("\t\t 5.Exit");
				switch (Integer.parseInt(SCANNER.nextLine())) {
				case 1:
					submenu.createEventAddToArray();
				case 2:
					submenu.printEvents();
				case 3:
					submenu.changeEvent();
				case 4:
					submenu.deleteEvent();
				case 5: {
					System.out.println("\t\t   Goodbay!");
					break;
				}
				default:
					System.out.println("Select option correctly!");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Select option correctly!");
			}
		} while (true);
	}
}