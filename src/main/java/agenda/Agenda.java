package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
	
	private List<Event> events = new LinkedList<>();
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        this.events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     * @throws Exception 
     */
    public List<Event> eventsInDay(LocalDate day) throws Exception {
    	if (this.events.isEmpty()) throw new Exception("Aucun event enregisté !");
    	List<Event> allEventInDay = new LinkedList<>();
    	for(Event e : this.events) {
    		if (e.isInDay(day)) allEventInDay.add(e);
    	}
    	return allEventInDay;
    }
}
