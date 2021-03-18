package Controller;

import Gateway.ConferenceDataSaver;
import Gateway.ScheduleToPDF;
import PresenterLayer.ProgramPresenter;
import UseCase.*;

import java.io.IOException;
import java.util.*;
import java.time.LocalTime;

public class ScheduleSystemGUI {

    private EventManager eventManager = new EventManager();
    private AttendeeManager attendeeManager = new AttendeeManager();
    private SpeakerManager speakerManager = new SpeakerManager();
    private OrganizerManager organizerManager = new OrganizerManager();
    private HashMap<String, String> userList = new HashMap<>();

    ProgramPresenter programPresenter = new ProgramPresenter();
    Scanner scan = new Scanner(System.in);

    public ScheduleSystemGUI(EventManager eventManager, AttendeeManager attendeeManager, SpeakerManager speakerManager,
                             OrganizerManager organizerManager, HashMap<String, String> userList) {
        this.eventManager = eventManager;
        this.attendeeManager = attendeeManager;
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.userList = userList;
    }


    /**
     * ---GETTERS FOR POTENTIALLY MODIFIED USE CASES---
     */

    public EventManager getEventManager() {
        return eventManager;
    }

    public AttendeeManager getAttendeeManager() {
        return attendeeManager;
    }

    public SpeakerManager getSpeakerManager() {
        return speakerManager;
    }

    /**
     * ---HELPER METHODS FOR MAIN SYSTEMS---
     */

    // Sign up attendee for an event
    public String attendeeAddEvent(String username, String eventID) {
        boolean attendeeVipStatus = attendeeManager.checkVIPStatus(username);
        programPresenter.spaceBreak();
        programPresenter.title("SIGNING UP FOR EVENT");
        programPresenter.inputPrompt("event ID (-1 to go back)");
        //String eventID = scan.nextLine();
        if (eventID.equals("-1")) {
            return "0";
        }
        if (eventManager.checkEventVipStatus(eventID)) {
            if (!attendeeVipStatus) {
                programPresenter.errorMessage("You are not a VIP, you cannot sign up for this event.");
                return "0";
            }
        }
        if (eventManager.eventAvailable(eventID, "Attendee")) {
            if (attendeeManager.signUpAttendee(username, eventID)) {
                eventManager.updateNumAttendees(eventID, 1);
                programPresenter.successMessage("You have signed up for your desired event.");
                return "1";
            } else {
                programPresenter.errorMessage("You are already signed up for the event.");
                return "0";
            }
        } else {
            programPresenter.errorMessage("The event is not available or doesn't exist.");
            return "0";
        }
    }

    // Removes attendee from an event
    public String attendeeRemoveEvent(String username, String eventID) {
        programPresenter.spaceBreak();
        programPresenter.title("REMOVING EVENT");
        programPresenter.inputPrompt("event ID (-1 to go back)");
        //String eventID = scan.nextLine();
        if (eventID.equals("-1")) {
            return "0";
        }
        if (attendeeManager.cancelEvent(username, eventID)) {
            eventManager.updateNumAttendees(eventID, -1);
            programPresenter.successMessage("You have removed the event from your schedule.");
            return "1";

        } else {
            programPresenter.errorMessage("Could not remove event.");
            return "0";
        }
    }

    public boolean attendeeCheckVIP(String username) {
        programPresenter.spaceBreak();
        return attendeeManager.checkVIPStatus(username);
    }

    // Add room
    public boolean organizerAddRoom(String room, String capacity) {
        while (true) {
            programPresenter.spaceBreak();
            programPresenter.title("ADDING ROOM");
            // programPresenter.inputPrompt("room (-1 to go back)");
            //String room = scan.nextLine();
            if (room.equals("")) {
                return false;
            }

            if (eventManager.addRoom(room, Integer.parseInt(capacity))) {
                programPresenter.successMessage("Room successfully added.");
                return true;

            } else {
                programPresenter.errorMessage("Room already exists.");
                return false;
            }
        }
    }

    // TODO: USE REGEX TO ENSURE VALID TIME AND DATE?
    // Helper function to parse validity of input information from scheduling event

    /**
     * Checks validity of input information from scheduling event
     *
     * @param eventID   id of the event being scheduled
     * @param room      room of the event being scheduled
     * @param startTime starting time of the event being scheduled
     * @param endTime   ending time of the event being scheduled
     * @param date      date of the event being scheduled
     * @param capacity  capacity of the event being scheduled
     * @return true if
     */
    public boolean validScheduleInfo(String eventID, String room, String startTime, String endTime,
                                     String date, int capacity) {
        String timeExpression = "(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]";
        String dateExpression = "(([0-1]?[0-9])|(2[0-9])|(3[0-1])/(([0-1]?[0-9])|(2[0-9])|(3[0-1])))";
        if (!eventManager.canSchedule(eventID)) {
            programPresenter.errorMessage("Event does not exist or is already scheduled.");
            return false;
        }
        if (!eventManager.roomExists(room)) {
            programPresenter.errorMessage("Room does not exist.");
            return false;
        }
        if (LocalTime.parse(startTime).isAfter(LocalTime.parse(endTime))) {
            programPresenter.errorMessage("Entered times are not valid.");
            return false;
        }
        if (capacity <= 0) {
            programPresenter.errorMessage("Invalid capacity - the capacity must be at least 1.");
            return false;
        }

        return true;
    }


    public String organizerScheduleEvent(String eventID, String room, String startTime, String endTime, String roomCapacity, String date, boolean vipStatus) {

        programPresenter.spaceBreak();
        programPresenter.title("SCHEDULING EVENT");
        programPresenter.showUnscheduledEvents(eventManager.getUnscheduledEvents());
        programPresenter.inputPrompt("event ID (-1 to go back)");
        //String eventID = scan.nextLine();
        if (eventID.equals("-1")) {
            return "0";
        }
        programPresenter.inputPrompt("room");
        //String room = scan.nextLine();
        programPresenter.inputPrompt("start time (HH:MM)");
        //String startTime = scan.nextLine();
        programPresenter.inputPrompt("end time (HH:MM)");
        //String endTime = scan.nextLine();
        programPresenter.inputPrompt("date (DD/MM/YYYY)");
        //String date = scan.nextLine();
        programPresenter.inputPrompt("maximum capacity");
        //String stringCapacity = scan.nextLine();
        programPresenter.inputPrompt("if this event is for VIPs only (y/n)");
        //String StringVIP = scan.nextLine();


        int capacity = Integer.parseInt(roomCapacity);
        if (!validScheduleInfo(eventID, room, startTime, endTime, date, capacity)) {
            return "0";
        }
        if (eventManager.scheduleEvent(eventID, room, startTime, endTime, date, capacity, vipStatus)) {
            programPresenter.successMessage("Event has been scheduled for " + startTime + " to "
                    + endTime + ", " + date + ".");
            return "1";
        } else {
            programPresenter.errorMessage("Event could not be added.");
            return "0";
        }
    }

    public String organizerCreateSpeaker(String username, String password) {
        programPresenter.spaceBreak();
        programPresenter.title("CREATING SPEAKER ACCOUNT");
        programPresenter.inputPrompt("speaker username (-1 to go back)");
        // String username = scan.nextLine();
        if (username.equals("-1")) {
            return "0";
        }
        programPresenter.inputPrompt("speaker password");
        if (!userList.containsKey(username)) {
            speakerManager.createUser(username, password);
            userList.put(username, password);
            ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
            conferenceDataSaver.writeUserInfo(username, password, "Speaker");
            programPresenter.successMessage("Speaker account successfully created.");
            return "1";
        } else {
            programPresenter.errorMessage("Username already exists.");
            return "0";
        }

    }

    public String organizerAssignSpeaker(String eventID, String username) {

        programPresenter.spaceBreak();
        programPresenter.title("ASSIGNING SPEAKER");
        programPresenter.inputPrompt("event ID (-1 to go back)");
        //String eventID = scan.nextLine();
        if (eventID.equals("-1")) {
            return "0";
        }
        programPresenter.inputPrompt("speaker username");
        //String username = scan.nextLine();
        if (eventManager.eventAvailable(eventID, "Speaker")) {
            List<String> speakerEvents = speakerManager.getSpeakerEvents(username);
            if (eventManager.addSpeaker(speakerEvents, eventID, username)) {
                speakerManager.assignSpeaker(username, eventID);
                programPresenter.successMessage("Speaker has been added to the event.");
                return "1";

            } else {
                programPresenter.errorMessage("Speaker could not be added to the event.");
                return "0";
            }
        } else {
            programPresenter.errorMessage("Speaker cannot be added to the event.");
            return "0";
        }
    }

    public String organizerCreateAttendee(String username, String password, String vipInput) {
        programPresenter.spaceBreak();
        programPresenter.title("CREATING ATTENDEE ACCOUNT");
        programPresenter.inputPrompt("attendee username (-1 to go back)");

        if (username.equals("-1")) {
            return "0";
        }
        programPresenter.inputPrompt("attendee password");

        if (!userList.containsKey(username)) {
            programPresenter.inputPrompt("if this attendee is a VIP (y)");
            if (vipInput.equals("VIP")) {
                attendeeManager.createVIPUser(username, password);
            } else {
                attendeeManager.createUser(username, password);
            }
            userList.put(username, password);
            ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
            conferenceDataSaver.writeUserInfo(username, password, "Attendee");
            programPresenter.successMessage("Attendee account successfully created.");
            return "1";
        } else {
            programPresenter.errorMessage("Username already exists.");
            return "0";
        }

    }


    public boolean organizerCancelEvent(String username, String eventID) {

        programPresenter.spaceBreak();
        programPresenter.title("CANCEL EVENT");

        programPresenter.inputPrompt("event ID (-1 to go back)");
        //String eventID = scan.nextLine();
        if (eventManager.removeScheduledEvent(eventID)) {
            attendeeManager.cancelEventAllAttendees(eventID);
            eventManager.removeEventSpeakers(eventID);
            programPresenter.successMessage("You have removed the event from the conference schedule.");
            return true;
        } else {
            programPresenter.errorMessage("Could not remove event.");
            return false;

        }
    }

    /**
     * Returns summary stats of the conference.
     *
     * @param choice choice of statistic
     * @return summary stats of the conference
     */
    public String organizerSummaryStats(String choice) {

        programPresenter.spaceBreak();
        programPresenter.title("SUMMARY STATISTICS");
        programPresenter.spaceBreak();
        programPresenter.showSummaryOptions();
        programPresenter.inputPrompt("choice (-1 to go back)");

        if (choice.equals("App Stats")) {
            int num_users = attendeeManager.getNumAttendees();
            int num_speakers = speakerManager.getNumSpeakers();
            int num_vip_users = attendeeManager.getNumVipAttendees();
            int num_regular_users = num_users - num_vip_users;
            String str = "";
            return "Total number of Attendees: " + num_users + "\n" + "[2] Total number of Speakers: " +
                    num_speakers + "\n" + "[3] Number of VIP users: " + num_vip_users + "\n" + "[4] Number of non-VIP attendees: "
                    + num_regular_users;
        } else if (choice.equals("AttendanceStats")) {
            List<String> eventsStats_bottom = new ArrayList<>();
            List<String> eventsStatsReverse = eventManager.getEventsStats();
            Collections.reverse(eventsStatsReverse);
            int i = 1;
            for (String e : eventsStatsReverse) {
                if (i <= 5) {
                    eventsStats_bottom.add(e);
                }
                i++;
            }
            //programPresenter.showEventsStats(eventsStats_bottom);
            String str = "Top 5 Events - Highest attendance" + "\n";
            for (String e : eventsStats_bottom) {
                String str_temp = e + "\n";
                str = str.concat(str_temp);
            }
            str += " \nTop 5 Events - Lowest Attendance\n";


            List<String> eventsStats_top = new ArrayList<>();
            int k = 1;
            for (String e : eventManager.getEventsStats()) {
                if (k <= 5) {
                    eventsStats_top.add(e);
                }
                k++;
            }
            //programPresenter.showEventsStats(eventsStats_top);
            String str1 = "";
            for (String e : eventsStats_top) {
                String str_temp = e + "\n";
                str1 = str1.concat(str_temp);
            }

            return str + str1;
        } else if (choice.equals("EventStats")) {
            int totalEvents = eventManager.numEvents();
            int numVipEvents = eventManager.numVipEvents();
            int numRegularEvents = totalEvents - numVipEvents;
            programPresenter.eventStatistics(totalEvents, numVipEvents, numRegularEvents);
            return "[1] Total Number of events: " + totalEvents + "\n" + "[2] Number of VIP Events: " +
                    numVipEvents + "\n" + "[3] Number of Regular Events: " + numRegularEvents;
        } else if (choice.equals("SpeakerStats")) {
            List<String> speaker_bottom = new ArrayList<>();
            List<String> speakersStatsReverse = speakerManager.getSpeakerStats();
            Collections.reverse(speakersStatsReverse);
            int i = 1;
            for (String e : speakersStatsReverse) {
                if (i <= 5) {
                    speaker_bottom.add(e);
                }
                i++;
            }
            programPresenter.showEventsStats(speaker_bottom);
            String str = "Top 5 Speakers - Most Events Spoken At" + "\n";
            for (String e : speaker_bottom) {
                String str_temp = e + "\n";
                str = str.concat(str_temp);
            }
            str += " \nTop 5 Speakers - Least Events Spoken At\n";

            List<String> speaker_top = new ArrayList<>();
            int k = 1;
            for (String e : speakerManager.getSpeakerStats()) {
                if (k <= 5) {
                    speaker_top.add(e);
                }
                k++;
            }
//                programPresenter.showEventsStats(speaker_top);
            String str1 = "";
            for (String e : speaker_top) {
                String str_temp = e + "\n";
                str1 = str1.concat(str_temp);
            }
            return str + str1;
        } else {
            return "0";
        }
    }

    /**
     * ---MAIN SYSTEMS---
     */

    public String attendeeScheduleSystem(String username, String scheduleOption, String userinput) {
        //remove loop, scan statements add option values.
        programPresenter.spaceBreak();
        programPresenter.spaceBreak();
        programPresenter.title("ATTENDEE SCHEDULE MENU");
        programPresenter.scheduleOptions("Attendee");
        //String scheduleOption = scan.nextLine();
        if (scheduleOption.equalsIgnoreCase("getSchedule")) {
            List<String> schedule = eventManager.getSchedule(Collections.emptyList(), true, true);
            StringBuilder str = new StringBuilder();
            for (String key : schedule) {
                str.append(key);
                str.append("\n");
            }
            return str.toString();
        } else if (scheduleOption.equalsIgnoreCase("showAttendeeSchedule")) {
            List<String> events = attendeeManager.getAttendeeEvents(username);
            List<String> schedule = eventManager.getSchedule(events, false, true);
            StringBuilder str = new StringBuilder();
            for (String x : schedule) {
                str.append(x);
                str.append("\n");
            }
            return str.toString();
        } else if (scheduleOption.equalsIgnoreCase("Add Event")) {
            return attendeeAddEvent(username, userinput);
        } else if (scheduleOption.equalsIgnoreCase("Remove Event")) {
            return attendeeRemoveEvent(username, userinput);
        } else if (scheduleOption.equalsIgnoreCase("CheckVIP")) {
            boolean result = attendeeManager.checkVIPStatus(username);
            if (result) {
                return "1";
            } else {
                return "0";
            }
        }
        return "null";
    }

    public HashMap<String, List<String>> getEventSchedule() {
        return eventManager.getSchedule(Collections.emptyList(), true);
    }

    public String speakerScheduleSystem(String username, String scheduleOption, String userinput) {
        if (scheduleOption.equalsIgnoreCase("getSchedule")) {
            List<String> schedule = eventManager.getSchedule(Collections.emptyList(), true, true);
            StringBuilder str = new StringBuilder();
            for (String key : schedule) {
                str.append(key);
                str.append("\n");
            }
            return str.toString();
        } else if (scheduleOption.equalsIgnoreCase("showSpeakerSchedule")) {
            List<String> events = speakerManager.getSpeakerEvents(username);
            StringBuilder str = new StringBuilder();
            for (String x : events) {
                str.append(x);
            }
            return str.toString();
        }
        return "null";
    }


    public String organizerScheduleSystem(String username, String scheduleOption, String userInput) {
        programPresenter.spaceBreak();
        programPresenter.title("ORGANIZER SCHEDULE MENU");
        programPresenter.scheduleOptions("Organizer");
        if (scheduleOption.equalsIgnoreCase("getSchedule")) {
            //    System.out.println("inoptionmethod");
            programPresenter.printSchedule(eventManager.getSchedule(Collections.emptyList(), true));
            List<String> schedule = eventManager.getSchedule(Collections.emptyList(), true, true);
            StringBuilder str = new StringBuilder();
            for (String key : schedule) {
                str.append(key);
                str.append("\n");
            }
            return str.toString();
        } else if (scheduleOption.equals("AddRoom")) {
            String roomInfo[] = userInput.split(",");
            boolean successful = organizerAddRoom(roomInfo[0], roomInfo[1]);
            if (successful) {
                return "1";
            } else {
                return "0";
            }

        } else if (scheduleOption.equals("SeeEvents")) {
            List<String> events = eventManager.getUnscheduledEvents();
            StringBuilder str = new StringBuilder();
            for (String event : events) {
                str.append(event);
                str.append("\n");
            }
            return str.toString();
        } else if (scheduleOption.equals("ScheduleEvent")) {
            String eventList[] = userInput.split(",");
            boolean vip = true;
            System.out.println(eventList[6]);
            System.out.println("ksjdnfsj");
            if (eventList[6].equalsIgnoreCase("VIP")) {
                vip = true;
            } else {
                vip = false;
            }

            return organizerScheduleEvent(eventList[0], eventList[1], eventList[2], eventList[3], eventList[4], eventList[5], vip);
        } else if (scheduleOption.equalsIgnoreCase("CancelEvent")) {
            boolean result = organizerCancelEvent(username, userInput);
            if (result) {
                return "You have removed the event from the conference schedule.";
            } else {
                return "Event could not be removed";
            }
        } else if (scheduleOption.equals("AddSpeaker")) {
            String eventList[] = userInput.split(",");
            return organizerCreateSpeaker(eventList[0], eventList[1]);
        } else if (scheduleOption.equals("AssignSpeaker")) {
            String eventList[] = userInput.split(",");
            return organizerAssignSpeaker(eventList[0], eventList[1]);
        } else if (scheduleOption.equals("NewAttendee")) {

            String eventList[] = userInput.split(",");
            return organizerCreateAttendee(eventList[0], eventList[1], eventList[2]);

        } else if (scheduleOption.equals("GeneratePDF")) {
            try {
                ScheduleToPDF stp = new ScheduleToPDF();
                stp.scheduleToPDF(eventManager.getSchedule(Collections.emptyList(), true));
                return "1";
            } catch (Exception e) {
                System.out.println("error");
            }
        } else if (scheduleOption.equalsIgnoreCase("getStats")) {
            String stats = organizerSummaryStats(userInput);
            System.out.println(stats);
            return stats;
        }
        return "null";
    }


}
