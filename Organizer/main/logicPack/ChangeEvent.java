package logicPack;

import java.util.ArrayList;
import java.util.Scanner;

import eventObjects.Event;
import eventObjects.SetEventObject;

public class ChangeEvent {

	private static final String DATE_FORMAT = "dd/MM/yyyy/hh";
	private final static Scanner SCANNER = new Scanner(System.in);
	private final SetEventObject eventSeter = new SetEventObject();
	
	public Event findEvent(ArrayList<Event> array, String indentification) {
		Event event = null;
		for (int i = 0; i < array.size(); i++) {
			event = (Event) array.get(i);
			if (Integer.parseInt(indentification)==event.getIdentificationNumber()) {
				return event;
			}
		}
		return null;
	}

	public Event changeType(Event event) {
		eventSeter.printTypeMenu();
		event.setType(eventSeter.setType(SCANNER.nextLine()));
		return event;
	}

	public Event changeMarker(Event event) {
		eventSeter.printMarkerMenu();
		event.setMarker(eventSeter.setMarker(SCANNER.nextLine()));
		return event;
	}

	public Event changeDescription(Event event, String description) {
		System.out.println("Enter little description");
		event.setDescription(description);
		return event;
	}

	public Event changeDate(Event event) {
		System.out.println("Enter date for your event(" + DATE_FORMAT + "):");
		event.setDate(eventSeter.setDate(SCANNER));
		return event;
	}
}
