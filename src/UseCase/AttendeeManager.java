package UseCase;

import Entity.Attendee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use case for Attendee related responsibilities.
 */
public class AttendeeManager implements UserAccountManager, Serializable {

    private List<Attendee> attendeeList = new ArrayList<>();
    private HashMap<String, List<String>> attendeeMap = new HashMap<>(); // Maps event IDs to attendee usernames
    private static final long serialVersionUID = 3L;

    /**
     * Creates a User of type Attendee and adds it to attendeeList.
     * @param username username of Attendee
     * @param password password of Attendee
     */
    public void createUser(String username, String password) {
        Attendee a = new Attendee(username, password);
        attendeeList.add(a);
    }

    /**
     * Creates a User of type Attendee who is a VIP and adds it to attendeeList.
     * @param username username of VIP Attendee
     * @param password password of VIP Attendee
     */
    public void createVIPUser(String username, String password) {
        Attendee a = new Attendee(username, password);
        a.setVipStatus(true);
        attendeeList.add(a);
    }

    /**
     * Returns whether attendee is a VIP given an attendee username.
     * @param username username of attendee
     * @return true if attendee is a VIP
     */
    public boolean checkVIPStatus(String username) {
        for (Attendee a : attendeeList) {
            if (a.getUsername().equals(username)) {
                return a.getVipStatus();
            }
        }
        return false;
    }

    /**
     * From UserAccountManager interface, returns if username corresponds to an Attendee.
     * @param username username of user
     * @return true if username points to an Attendee
     */
    public boolean checkUserExistence(String username) {
        for (Attendee a : attendeeList) {
            if (a.getUsername().equals(username)) {
                return true;
            }

        }
        for (Attendee a : attendeeList) {
            if (a.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * From UserAccountManager interface, returns a list of usernames of all attendees.
     * @return list of all attendee usernames
     */
    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        for (Attendee a : attendeeList) {
            users.add(a.getUsername());
        }
        return users;
    }

    /**
     * Returns a list of usernames of attendees attending the events corresponding to the list of event IDs provided.
     * @param eventList list of event IDs
     * @return list of attendee usernames
     */
    public List<String> getAttendeesOfEvents (List<String> eventList) {
        List<String> attendees = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : attendeeMap.entrySet()) {
            if (eventList.contains(entry.getKey())) {
                attendees.addAll(entry.getValue());
            }
        }
        return attendees;
    }

    /**
     * Gets a list of event IDs of events that user is attending.
     * @param username username of the attendee
     * @return list of event IDs
     */
    public List<String> getAttendeeEvents(String username) {
        List<String> attendeeEvents = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : attendeeMap.entrySet()) {
            if (entry.getValue().contains(username)) {
                attendeeEvents.add(entry.getKey());
            }
        }
        return attendeeEvents;
    }

    /**
     * Signs up the attendee to the event iff they are not signed up to the event already. Assume event is not full and
     * the event exists in the event list. (Only condition we must check for is whether user is already signed up).
     * @param username username of the attendee
     * @param eventID event ID of the event
     * @return boolean value indicating whether sign up was successful
     */
    public boolean signUpAttendee(String username, String eventID) {
        // Mapping already exists
        if (attendeeMap.containsKey(eventID)) {
            if (attendeeMap.get(eventID).contains(username)) {
                return false;
            }
            else {
                attendeeMap.get(eventID).add(username);
                return true;
            }
        }
        // Need to create new mapping
        else {
            List<String> attendees = new ArrayList<>();
            attendees.add(username);
            attendeeMap.put(eventID, attendees);
            return true;
        }
    }

    /**
     * Removes the attendee from the event iff they are attending it, otherwise if they are not already attending it
     * then no removal occurs.
     * @param username username of the attendee
     * @param eventID event ID of the event
     * @return true if attendee was successfully removed
     */
    public boolean cancelEvent(String username, String eventID) {
        if (attendeeMap.containsKey(eventID)) {
            if (attendeeMap.get(eventID).contains(username)) {
                attendeeMap.get(eventID).remove(username);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the event from the attendeeMap when event has been cancelled and return if successful.
     * @param eventID ID of event
     * @return true if event was removed
     */
    public boolean cancelEventAllAttendees(String eventID) {
        if (attendeeMap.containsKey(eventID)) {
            attendeeMap.remove(eventID);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the number of attendees in the system.
     * @return number of attendees
     */
    public int getNumAttendees() {
        return attendeeList.size();
    }

    /**
     * Returns the number of VIP attendees in the system.
     * @return number of VIP attendees
     */
    public int getNumVipAttendees() {
        int count = 0;
        for (Attendee a : attendeeList) {
            if(checkVIPStatus(a.getUsername())){
                count+=1;
            }
        }
        return count;
    }

}
