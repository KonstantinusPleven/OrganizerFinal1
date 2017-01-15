package menu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import eventObjects.Event;
import eventObjects.EventsException;
import eventObjects.SetEventObject;
import logicPack.*;

public class SubMenu {

	private final static Scanner SCANNER = new Scanner(System.in);
	private static final String DATE_FORMAT = "dd/MM/yyyy/hh";

	private ChangeEvent changer = new ChangeEvent();
	private PrintEvents printer = new PrintEvents();
	private SetEventObject eventSeter = new SetEventObject();
	private FileReadWrite writer = new FileReadWrite();
	private ChecksTimeLine checker = new ChecksTimeLine();

	private ArrayList<Event> array = new ArrayList<Event>();

	public void createEventAddToArray() {
		do {
			try {
				Event event = new Event();
				System.out.println("Enter date for your event(" + DATE_FORMAT + "):");
				event.setDate(eventSeter.setDate(SCANNER));
				eventSeter.printTypeMenu();
				event.setType(eventSeter.setType(SCANNER.nextLine()));
				eventSeter.printMarkerMenu();
				event.setMarker(eventSeter.setMarker(SCANNER.nextLine()));
				System.out.println("Enter little description");
				event.setDescription(eventSeter.setDescription(SCANNER));
				checker.checkForAnotherEvent(array, event);
				array.add(event);
				writer.writingFile(array);
				printer.printEvent(event);
				break;

			} catch (NumberFormatException ex) {
				System.out.println("Incorrect option!");
			} catch (NullPointerException ex) {
				System.out.println("Incorrect value!");
			} catch (EventsException ex) {
				System.out.println("Can't have 2 events at the same time.");
			}
		} while (true);
	}

	public void printEvents() {
		do {
			try {
				System.out.println("Select option from the menu:");
				System.out.println("\t\t1.Print events for the day.");
				System.out.println("\t\t2.Print events for next 7 days.");
				System.out.println("\t\t3.Print events for next month.");
				System.out.println("\t\t4.Print all events.");
				System.out.println("\t\t5.Exit this menu.");
				switch (Integer.parseInt(SCANNER.nextLine())) {
				case 1:
					printer.printDay(array);
				case 2:
					printer.printWeek(array);
				case 3:
					printer.printMonth(array);
				case 4:
					printer.printEventTable(array);
				case 5:
					break;
				default:
					System.out.println("Select option correctly!");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Select option correctly!");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} while (true);
	}

	public void changeEvent() {
		do {
			try {
				printer.printEventTable(array);
				System.out.println("Enter the identification number of the event:");
				Event event = changer.findEvent(array, SCANNER.nextLine());
				if (event == null) {
					System.out.println("There isn't event with that ID number");
					break;
				}
				System.out.println("Select option from the menu:");
				System.out.println("\t\t1.Date.");
				System.out.println("\t\t2.Type.");
				System.out.println("\t\t3.Marker.");
				System.out.println("\t\t4.Description.");
				System.out.println("\t\t5.Exit.");
				array.remove(event);
				switch (Integer.parseInt(SCANNER.nextLine())) {
				case 1:
					event = changer.changeDate(event);
				case 2:
					event = changer.changeType(event);
				case 3:
					event = changer.changeMarker(event);
				case 4:
					event = changer.changeDescription(event, SCANNER.nextLine());
				case 5:
					break;
				}
				printer.printEvent(event);
				array.add(event);
				writer.writingFile(array);
			} catch (NumberFormatException ex) {
				System.out.println("Select option correctly.");
			} catch (NullPointerException ex) {
				System.out.println("There isn't such element.");
			}
		} while (true);
	}

	public void deleteEvent() {
		if (array.isEmpty())
			System.out.println("There arent events yet");
		else {
			printer.printEventTable(array);
			System.out.println("Enter the identification number of the event:");
			Event event = changer.findEvent(array, SCANNER.nextLine());
			if (array.remove(event))
				System.out.println("\t\tDone!");
			writer.writingFile(array);
		}
	}

	public void setArray(ArrayList<Event> array) {
		this.array = array;
	}

}
