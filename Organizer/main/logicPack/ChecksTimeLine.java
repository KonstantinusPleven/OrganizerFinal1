package logicPack;

import java.util.ArrayList;

import eventObjects.Event;
import eventObjects.EventsException;

public class ChecksTimeLine {
	
	
	public void checkForAnotherEvent(ArrayList<Event> array, Event event) throws EventsException {
		for (int i = 0; i < array.size(); i++) {
			if (event.getIdentificationNumber() == array.get(i).getIdentificationNumber())
				throw new EventsException();
		}
	}
}
