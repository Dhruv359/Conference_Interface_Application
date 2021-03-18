package Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Event entity, describes the events of the conference.
 */
public class Event implements Serializable, Comparator<Event> {

    private String eventName;
    private String eventID; // Must be unique for every Event
    private String eventType; // "Party" if no speakers, "Talk" if 1 speaker, "Panel" if multiple speakers
    private String startTime; // Must be numerical, military time "hours:minutes" => "13:20" means 1:20 pm
    private String endTime; // Same condition as above
    private String date; // Formatted "DD/MM/YYYY" => "01/03/2000" => March 1, 2000
    private int capacity; // Precondition: must be > 0, no empty events allowed
    private int numAttendees; // Must be >= 0
    private boolean vipEvent; // True if event is for VIPs only
    private List<String> speakers = new ArrayList<>(); // List of usernames of Speakers speaking at event
    private static final long serialVersionUID = 9L;

    /**
     * Default empty constructor for Event.
     */
    public Event() { }

    /**
     * Constructor method for Event. Creates an event object with a name and unique ID, and upon instantiation has
     * no speakers as well as 0 people attending.
     * @param eventName name of the event
     * @param eventID unique ID of the event
     */
    public Event(String eventName, String eventID) {
        this.eventName = eventName;
        this.eventID = eventID;
        this.numAttendees = 0;
        this.speakers = new ArrayList<>();
        this.eventType = "Party";
    }

    /**
     * Returns the event's name.
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the unique event ID.
     * @return the eventID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Returns the event's type.
     * @return the event type
     */
    public String getEventType(){
        return eventType;
    }

    /**
     * Returns the start time in hours and minutes of the event.
     * @return start time of event
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time in hours and minutes of the event.
     * @return end time of the event
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Returns the date of the event in days, months and years.
     * @return date of the event
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the maximum attendee capacity of the event.
     * @return capacity of the event
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the number of people attending the event.
     * @return number of people attending event
     */
    public int getNumAttendees() {
        return numAttendees;
    }

    /**
     * Returns the VIP status of the event.
     * @return true iff the event is for VIPs only
     */
    public boolean getVipEvent() {
        return vipEvent;
    }

    /**
     * Returns a list of speaker usernames of the speakers of the event.
     * @return list of speaker usernames
     */
    public List<String> getSpeakers() {
        return speakers;
    }

    /**
     * Sets the event type of the event.
     * @param eventType event type of the event
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Sets the start time of the event.
     * @param startTime start time of the event
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the event.
     * @param endTime end time of the event
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /** Sets the date of the event.
     * @param date the date of the event
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the capacity of the event.
     * @param capacity maximum capacity of event
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets the number of people attending the event.
     * @param numAttendees number of people attending event
     */
    public void setNumAttendees(int numAttendees) {
        this.numAttendees = numAttendees;
    }

    /**
     * Sets the VIP status of the event.
     * @param vipEvent true if the event is for VIPs only
     */
    public void setVipEvent(boolean vipEvent) {
        this.vipEvent = vipEvent;
    }

    /**
     * Adds a speaker to the list of speakers speaking to the event and also updates the event type.
     * @param username username of speaker
     */
    public void addSpeaker(String username) {
        speakers.add(username);
        if(speakers.size()==0){
            eventType = "party";
        }
        else if(speakers.size()==1){
            eventType = "talk";
        }
        else{
            eventType = "panel discussion";
        }
    }

    /**
     * Removes all speakers from the list of speakers speaking to the event.
     */
    public void removeAllSpeakers() {
        speakers.clear();
    }

    /**
     * Updates the event type based on the number of speakers - called whenever the number of speakers for the event
     * is changed.
     */
    public void updateEventType() {
        if (speakers.size() == 0) {
            eventType = "Party";
        }
        else if (speakers.size() == 1) {
            eventType = "Talk";
        }
        else {
            eventType = "Panel";
        }
    }

    /**
     * Returns whether the event has a conflict with a specified time and date.
     * @param otherStartTime start time to compare
     * @param otherEndTime end time to compare
     * @param otherDate date to compare
     * @return true if the event conflicts with the given time and date
     */
    public boolean hasConflict(String otherStartTime, String otherEndTime, String otherDate) {
        // Check if event on the same day, if not, no conflict, done
        if (!this.date.equals(otherDate)) {
            return false;
        }
        // On same day, check for conflict between times
        else {
            LocalTime startTimeA = LocalTime.parse(this.startTime);
            LocalTime endTimeA = LocalTime.parse(this.endTime);
            LocalTime startTimeB = LocalTime.parse(otherStartTime);
            LocalTime endTimeB = LocalTime.parse(otherEndTime);
            return startTimeA.isBefore(endTimeB) && startTimeB.isBefore(endTimeA);
        }
    }

    /**
     * Overrides method in Object class to get a String representation of Event objects.
     * @return String representation of Event
     */
    public String toString() {
        String titles = String.format("%-22s%-22s%-22s%-22s\n","Date and Time", "Event Info", "Capacity", "Speakers");
        String dateTime = "<" + startTime + "-" + endTime + ", " + date + ">";
        String ID = "[" + eventID + "]";
        String type = "(" + eventType + ")";
        String status = "Free";
        if (vipEvent) {
            status = "VIP";
        }
        String eventCapacity = numAttendees + "/" + capacity;
        String speakerList = "";
        if (speakers.isEmpty()) {
            speakerList = "[There are no speakers for this event.]";
        }
        for (String s : speakers) {
            speakerList += s + ", ";
        }
        return String.format("%-30s%-15s%-15s%-15s%-15s%-15s%-15s", dateTime, eventName, ID, type, status, eventCapacity, speakerList);
    }

    /**
     * Implements compare method from Comparator interface to sort events by time
     * @param e1 first event being compared
     * @param e2 second event being compared
     * @return 0 if times are equal, positive if e1 after e2, negative if e1 before e2
     */
    public int compare(Event e1, Event e2) {
        String d1 = e1.getDate();
        String d2 = e2.getDate();
        String endTimeE1 = d1.substring(6, 10) + "-" + d1.substring(3, 5) + "-" + d1.substring(0, 2) + "T"
                + e1.getEndTime();
        String startTimeE2 = d2.substring(6, 10) + "-" + d2.substring(3, 5) + "-" + d2.substring(0, 2) + "T"
                + e2.getStartTime();
        LocalDateTime endTimeA = LocalDateTime.parse(endTimeE1);
        LocalDateTime startTimeB = LocalDateTime.parse(startTimeE2);
        return endTimeA.compareTo(startTimeB);
    }

}
