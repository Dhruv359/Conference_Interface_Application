package UseCase;

import Entity.Event;
import Entity.Room;

import java.io.Serializable;
import java.util.*;

/**
 * Use case for scheduling events.
 */
public class EventManager implements Serializable {

    private List<Event> eventList = new ArrayList<>();
    private HashMap<Room, List<Event>> conferenceSchedule = new HashMap<>(); // Room to event objects mapping
    private static final long serialVersionUID = 6L;


    /**
     * Creates a new event object in the event list with a unique id.
     * @param event name of the event
     */
    public void createEvent(String event) {
        Random rand = new Random();
        while (true) {
            int randomNum = rand.nextInt(eventList.size() + 100) + 1;
            String id = "id" + randomNum;
            boolean uniqueID = true;
            int counter = 0;
            for (Event e : eventList) {
                if (e.getEventID().equals(id)) {
                    uniqueID = false;
                    break;
                }
            }
            if (uniqueID) {
                Event newEvent = new Event(event, id);
                eventList.add(newEvent);
                break;
            }
        }
    }

    /**
     * Return whether event is a VIP-only event.
     * @param eventID ID of event
     * @return true if event is VIP-only
     */
    public boolean checkEventVipStatus(String eventID) {
        for (Event e : eventList) {
            if (e.getEventID().equals(eventID)) {
                return e.getVipEvent();
            }
        }
        return false;
    }

    /**
     * Checks to see whether an event can be scheduled by the organizer.
     * @param eventID ID of the event
     * @return true if event can be scheduled
     */
    public boolean canSchedule(String eventID) {
        Event currEvent = new Event();
        boolean eventExists = false;
        for (Event e : eventList) {
            // Check for event ID in the event list, get event object from ID
            if (e.getEventID().equals(eventID)) {
                eventExists = true;
                currEvent = e;
            }
        }
        // See if the event object is already scheduled
        for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
            if (entry.getValue().contains(currEvent)) {
                return false;
            }
        }
        return eventExists;
    }

    /**
     * Returns a schedule of the events from given event list in the form of String values to be passed into UI,
     * and if we want the entire schedule the second parameter must be true.
     * @param events list of event IDs
     * @param allEvents boolean value that is true only if we want the entire conference schedule
     * @return schedule of events pertaining to the request of the parameters
     */
    public HashMap<String, List<String>> getSchedule(List<String> events, boolean allEvents) {
        HashMap<String, List<String>> scheduledEvents = new HashMap<>();
        // First iterate over rooms of the conference
        for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
            // Sort events by date
            entry.getValue().sort(new Event());
            // For each room we have a list of event strings
            List<String> eventStrings = new ArrayList<>();
            eventStrings.add(String.format("%-30s%-15s%-15s%-15s%-15s%-15s%-15s\n", "Date and Time", "Event Name",
                    "Event ID", "Event Type", "VIP Status", "Capacity", "Speakers"));
            // If we want all the events we take all the event strings regardless
            if (allEvents) {
                for (Event e : entry.getValue()) {
                    eventStrings.add(e.toString());
                }
            }
            // If not then we only take the events that are in the parameter (the user events)
            else {
                for (Event e : entry.getValue()) {
                    if (events.contains(e.getEventID())) {
                        eventStrings.add(e.toString());
                    }
                }
            }
            // Then, each room number has a list of events in their String form
            if (!eventStrings.isEmpty()) {
                System.out.println(entry.getKey().toString());
                scheduledEvents.put(entry.getKey().toString(), eventStrings);
            }
        }
        return scheduledEvents;
    }



    /**
     * Returns a schedule of the events from given event list in the form of String values to be passed into UI,
     * and if we want the entire schedule the second parameter must be true.
     * @param events list of event IDs
     * @param allEvents boolean value that is true only if we want the entire conference schedule
     * @return schedule of events pertaining to the request of the parameters
     */
    public List<String> getSchedule(List<String> events, boolean allEvents, boolean getList) {
        List<String> schedule = new ArrayList<>();
        // First iterate over rooms of the conference
        for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
            // Sort events by date
            entry.getValue().sort(new Event());
            // For each room we have a list of event strings
            List<String> eventStrings = new ArrayList<>();
            eventStrings.add(String.format("%-30s%-15s%-15s%-15s%-15s%-15s%-15s\n", "Date and Time", "Event Name",
                    "Event ID", "Event Type", "VIP Status", "Capacity", "Speakers"));
            // If we want all the events we take all the event strings regardless
            if (allEvents) {
                for (Event e : entry.getValue()) {
                    eventStrings.add(e.toString());
                }
            }
            // If not then we only take the events that are in the parameter (the user events)
            else {
                for (Event e : entry.getValue()) {
                    if (events.contains(e.getEventID())) {
                        eventStrings.add(e.toString());
                    }
                }
            }
            // Then, each room number has a list of events in their String form
            if (!eventStrings.isEmpty()) {
                schedule.add(entry.getKey().toString());
                schedule.addAll(eventStrings);
            }
        }
        return schedule;
    }

    /**
     * Returns whether there exists events in the event list.
     * @return true if there exists event objects in the event list
     */
    public boolean hasEvents() {
        return !eventList.isEmpty();
    }

    /**
     * Returns whether the room exists in the conference schedule based on the name of the room.
     * @param room name of the room
     * @return true if room already exists
     */
    public boolean roomExists(String room) {
        for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
            if (entry.getKey().getName().equals(room)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a room to the schedule.
     * @param roomName name of the room
     * @return whether adding room was successful
     */
    public boolean addRoom(String roomName, int capacity) {
        // Check if room is unique
        if (!roomExists(roomName)) {
            List<Event> emptyEvents = new ArrayList<>();
            Room room = new Room(roomName, capacity);
            conferenceSchedule.put(room, emptyEvents);
            return true;
        }
        // Can't add room when it already exists
        else {
            return false;
        }
    }

    /**
     * Check if the event is available to the specific user type, (for attendees, see if the event is not at full
     * capacity, and for speakers it is automatically available.
     * @param eventID ID of the event
     * @return true if the event is available
     */
    public boolean eventAvailable(String eventID, String userType) {
        // Event can only be accessible for the public if it has been scheduled and has speakers
        for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
            for (Event e : entry.getValue()) {
                // Check if event is in the schedule
                if (e.getEventID().equals(eventID)) {
                    // Attendee case, check for capacity
                    if (userType.equals("Attendee")) {
                        return (e.getNumAttendees() < e.getCapacity());
                    }
                    // Speaker case, automatically available since it is scheduled
                    else if (userType.equals("Speaker")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Updates the number of people attending an event for an event object. Assume the event exists and is available,
     * and that the added amount will not exceed capacity.
     * @param eventID ID of the event
     * @param amount amount of attendees being added, usually either 1 or -1 (signing up and cancelling)
     */
    public void updateNumAttendees(String eventID, int amount) {
        for (Event e : eventList) {
            if (e.getEventID().equals(eventID)) {
                e.setNumAttendees(e.getNumAttendees() + amount);
            }
        }
    }

    /**
     * Updates the capacity for an event, return if successful - makes sure that capacity does not exceed room capacity
     * or that the number of attendees does not exceed capacity.
     * @param eventID ID of event
     * @param capacity capacity to be set
     * @return true if capacity was successfully updated
     */
    public boolean updateCapacity(String eventID, int capacity) {
        // If event has not been scheduled yet, capacity cannot be updated
        if (canSchedule(eventID)) {
            return false;
        }
        else {
            for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
                for (Event e : entry.getValue()) {
                    if (e.getEventID().equals(eventID) && (entry.getKey().getCapacity() >= capacity) &&
                            (e.getNumAttendees() <= capacity)) {
                        e.setCapacity(capacity);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns a list of event IDs and event names of events that can be scheduled by the organizers, mainly for
     * UI to show organizers what events can be scheduled.
     * @return list of event IDs and names of unscheduled events
     */
    public List<String> getUnscheduledEvents() {
        List<String> unscheduledEvents = new ArrayList<>();
        for (Event e : eventList) {
            if (canSchedule(e.getEventID())) {
                unscheduledEvents.add(e.getEventName() + " [" + e.getEventID() + "]");
            }
        }
        return unscheduledEvents;
    }

    /**
     * Schedules the event by adding it to the corresponding room in the conferenceSchedule map. Assume the room exists
     * and all information is valid (as checked in the controller).
     * @param eventID ID of the event
     * @param room name of room event is being added to
     * @param startTime event start time
     * @param endTime event end time
     * @param date date of the event
     * @param capacity event total capacity
     * @return whether scheduling of the event was successful
     */
    public boolean scheduleEvent(String eventID, String room, String startTime, String endTime, String date,
                                 int capacity, boolean vipStatus) {
        // Check for time conflicts in the room
        for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
            if (entry.getKey().getName().equals(room)) {
                for (Event e : entry.getValue()) {
                    if (e.hasConflict(startTime, endTime, date)) {
                        return false;
                    }
                }
            }
        }
        // Find event object in eventList
        for (Event e : eventList) {
            // Update the event object's info
            if (eventID.equals(e.getEventID())) {
                e.setStartTime(startTime);
                e.setEndTime(endTime);
                e.setDate(date);
                e.setCapacity(capacity);
                e.setVipEvent(vipStatus);
                for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
                    if (entry.getKey().getName().equals(room)) {
                        // Check if event capacity exceeds room capacity
                        if (capacity > entry.getKey().getCapacity()) {
                            return false;
                        }
                        // Add the event to the room
                        entry.getValue().add(e);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if an event can be removed from the schedule and removes it if possible.
     * @param eventID ID of the event
     * @return true if event is removed from the schedule
     */
    public boolean removeScheduledEvent(String eventID){
        Event currEvent = new Event();
        for (Event e : eventList) {
            // Check for event ID in the event list
            if (e.getEventID().equals(eventID)) {
                currEvent = e;
            }
        }
        // See if the event object is already scheduled
        for (Map.Entry<Room, List<Event>> entry : conferenceSchedule.entrySet()) {
            if (entry.getValue().contains(currEvent)) {
                entry.getValue().remove(currEvent);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all the speakers for a particular event if the event exists.
     * @param eventID ID of the event
     */
    public void removeEventSpeakers(String eventID){
        Event currEvent = new Event();
        boolean eventExists = false;
        for (Event e : eventList) {
            // Check for event ID in the event list
            if (e.getEventID().equals(eventID)) {
                eventExists = true;
                currEvent = e;
            }
        }
        if (eventExists) {
            currEvent.removeAllSpeakers();
            currEvent.updateEventType();
        }
    }

    /**
     * Adds speaker to the event, updates the speaker list in the event object and changes the event type accordingly.
     * @param speakerEvents list of event IDs for events that speaker is speaking at
     * @param eventID ID of event where speaker is to be added
     * @param speakerUsername username of the speaker to be assigned
     * @return true if speaker was added to event
     */
    public boolean addSpeaker(List<String> speakerEvents, String eventID, String speakerUsername) {
        // Get a list of all the event objects
        List<Event> events = new ArrayList<>();
        Event mainEvent = new Event();
        for (Event e : eventList) {
            if (speakerEvents.contains(e.getEventID())) {
                events.add(e);
            }
            if (e.getEventID().equals(eventID)) {
                mainEvent = e;
            }
        }
        // Check for time conflicts
        for (Event e : events) {
            if (mainEvent.hasConflict(e.getStartTime(), e.getEndTime(), e.getDate())) {
                return false;
            }
        }
        // No time conflicts, add speaker username to event object instance variable
        mainEvent.addSpeaker(speakerUsername);
        mainEvent.updateEventType();
        return true;
    }

    /**
     Returns a list of event names, event ids and number of attendees for the event
     * @return list of event IDs, events ids and number of attendees
     */
    public List<String> getEventsStats(){
        List<Integer> lengths = new ArrayList<>();
        List<String> eventsStats = new ArrayList<>();
        for (Event e : eventList) {
            if (!canSchedule(e.getEventID())) {
                eventsStats.add(e.getEventName() + " [" + e.getEventID() + "]" + " Number of attendees: " +
                        e.getNumAttendees());
                lengths.add(e.getNumAttendees());
            }

            int i, j;
            for (i = 0; i < lengths.size()-1; i++)
                for (j = 0; j < lengths.size()-i-1; j++)
                    if (lengths.get(j) > lengths.get(j+1)){
                        int temp1 = lengths.get(j);
                        lengths.set(j,lengths.get(j+1));
                        lengths.set(j+1,temp1);
                        String temp2 = eventsStats.get(j);
                        eventsStats.set(j,eventsStats.get(j+1));
                        eventsStats.set(j+1,temp2);
                    }
        }
        return eventsStats;
    }

    /**
     Returns the number of scheduled vip events.
     * @return number of vip events
     */
    public int numVipEvents(){
        int count = 0;
        for (Event e : eventList) {
            if (!canSchedule(e.getEventID())) {
                if(checkEventVipStatus(e.getEventID())){
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     Returns the total number of scheduled events.
     * @return number of scheduled events
     */
    public int numEvents() {
        int count = 0;
        for (Event e : eventList) {
            if (!canSchedule(e.getEventID())) {
                count += 1;

            }
        }
        return count;
    }

}

