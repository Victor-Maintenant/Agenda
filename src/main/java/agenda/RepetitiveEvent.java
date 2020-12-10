package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    ChronoUnit frequency;
    private List<LocalDate> exceptions = new ArrayList<>();
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
        
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        exceptions.add(date);
    }
    
    public boolean isInDay(LocalDate aDay) {
        LocalDateTime myStart = this.getStart();
        Duration myDuration = this.getDuration();
        LocalDateTime dayTimeEnd = myStart.plus(myDuration);
        boolean retour = false;
        for (LocalDate date : exceptions){
            if (date.isEqual(aDay)){
                return false;
            }
        }
        if (myStart.toLocalDate().isEqual(aDay)){
            return true;
        }
        
        while (myStart.toLocalDate().isBefore(aDay) ){
        dayTimeEnd = myStart.plus(myDuration);
        
        if (myStart.toLocalDate().isBefore(aDay) || myStart.toLocalDate().isEqual(aDay)){
            if (dayTimeEnd.toLocalDate().isAfter(aDay) || dayTimeEnd.toLocalDate().isEqual(aDay)) {
                retour = true;
            }
        }
        myStart = myStart.plus(1, frequency);
        }       
        return retour;
        
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency;   
    }

}
