package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {

	private LocalDate terminationInclusive;
	private long numberOfOccurences;
	private List<LocalDate> exceptions = new ArrayList<>();
    
    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
         super(title, start, duration, frequency);
         this.terminationInclusive = terminationInclusive;

    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.numberOfOccurences = numberOfOccurrences; 
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
    	return this.terminationInclusive;
    }

    public long getNumberOfOccurrences() {
    	return this.numberOfOccurences;
    }
    
    public void addException(LocalDate date) {
        exceptions.add(date);
    }
    
    @Override
    public boolean isInDay(LocalDate aDay) {
    	LocalDateTime tampon = this.getStart();
        Duration myDuration = this.getDuration();
        boolean retour = false;
        for (LocalDate date : exceptions){
            if (date.isEqual(aDay)){
                return false;
            }
        }
        if (tampon.toLocalDate().isEqual(aDay)){
            return true;
        }
        if (this.terminationInclusive != null) {
        	if (dateInferiorTerminationInclusive(aDay) || dateInferiorOrEqualToNOccurence(aDay)) {
        		while (tampon.toLocalDate().isBefore(aDay) || tampon.toLocalDate().isEqual(aDay)) {
        	        LocalDateTime dayTimeEnd = tampon.plus(myDuration);
        			if (tampon.toLocalDate().isBefore(aDay) || tampon.toLocalDate().isEqual(aDay)){
        	            if (dayTimeEnd.toLocalDate().isAfter(aDay) || dayTimeEnd.toLocalDate().isEqual(aDay)) {
        	                retour = true;
        	            }
        			}
        			tampon = tampon.plus(1, this.frequency);
        		}
        	}
        
        }
        else {
        	if (dateInferiorOrEqualToNOccurence(aDay)) {
        		while (tampon.toLocalDate().isBefore(aDay) || tampon.toLocalDate().isEqual(aDay)) {
        			LocalDateTime dayTimeEnd = tampon.plus(myDuration);
        			if (tampon.toLocalDate().isBefore(aDay) || tampon.toLocalDate().isEqual(aDay)){
        	            if (dayTimeEnd.toLocalDate().isAfter(aDay) || dayTimeEnd.toLocalDate().isEqual(aDay)) {
        	                retour = true;
        	            }
        			}
        			tampon = tampon.plus(1, this.frequency);
        		}
        	}
        }
        return retour;
    }

	private boolean dateInferiorTerminationInclusive(LocalDate aDay) {
		return this.terminationInclusive.isAfter(aDay) || this.terminationInclusive.isEqual(aDay);
	}

	private boolean dateInferiorOrEqualToNOccurence(LocalDate aDay) {
		return (this.getStart().plus(this.numberOfOccurences, this.frequency).toLocalDate().isAfter(aDay))  || (this.getStart().plus(this.numberOfOccurences, this.frequency).toLocalDate().isEqual(aDay));
	}

   
}
