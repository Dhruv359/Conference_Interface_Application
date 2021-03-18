//package Controller;
//
//import Gateway.ConferenceDataReceiver;
//import Gateway.ConferenceDataSaver;
//import PresenterLayer.ProgramPresenter;
//import UseCase.*;
//
//import java.util.*;
//import java.time.LocalTime;
//
//public class ScheduleSystem {
//
//    private EventManager eventManager = new EventManager();
//    private AttendeeManager attendeeManager = new AttendeeManager();
//    private SpeakerManager speakerManager = new SpeakerManager();
//    private OrganizerManager organizerManager = new OrganizerManager();
//    private HashMap<String, String> userList = new HashMap<>();
//    private ArrayList<ArrayList<String>> userRequest = new ArrayList<>();
//
//    ProgramPresenter programPresenter = new ProgramPresenter();
//    Scanner scan = new Scanner(System.in);
//
//    public ScheduleSystem(EventManager eventManager, AttendeeManager attendeeManager, SpeakerManager speakerManager,
//                          OrganizerManager organizerManager, HashMap<String, String> userList, ArrayList<ArrayList<String>> userRequest) {
//        this.eventManager = eventManager;
//        this.attendeeManager = attendeeManager;
//        this.speakerManager = speakerManager;
//        this.organizerManager = organizerManager;
//        this.userList = userList;
//        this.userRequest = userRequest;
//    }
//
//    public ScheduleSystem() {
//        ConferenceDataReceiver conferenceDataReceiver = new ConferenceDataReceiver();
//        EventManager eventManager = conferenceDataReceiver.readEventManager();
//        MessageManager messageManager = conferenceDataReceiver.readMessageManager();
//        AttendeeManager attendeeManager = conferenceDataReceiver.readAttendeeManager();
//        SpeakerManager speakerManager = conferenceDataReceiver.readSpeakerManager();
//        OrganizerManager organizerManager = conferenceDataReceiver.readOrganizerManager();
//        HashMap<String, String> userList = conferenceDataReceiver.readUserList();
//    }
//    /**
//     *      ---GETTERS FOR POTENTIALLY MODIFIED USE CASES---
//     */
//
//    public EventManager getEventManager() {
//        return eventManager;
//    }
//
//    public AttendeeManager getAttendeeManager() {
//        return attendeeManager;
//    }
//
//    public SpeakerManager getSpeakerManager() {
//        return speakerManager;
//    }
//
//    /**
//     *      ---HELPER METHODS FOR MAIN SYSTEMS---
//     */
//
//    // Sign up attendee for an event
//    public int attendeeAddEvent(String username, String eventID) {
//        while (true) {
//            boolean attendeeVipStatus = attendeeManager.checkVIPStatus(username);
//            programPresenter.spaceBreak();
//            programPresenter.title("SIGNING UP FOR EVENT");
//            programPresenter.inputPrompt("event ID (-1 to go back)");
//            //String eventID = scan.nextLine();
//            if (eventID.equals("-1")) {
//                break;
//            }
//            if (eventManager.checkEventVipStatus(eventID)){
//                if (!attendeeVipStatus) {
//                    programPresenter.errorMessage("You are not a VIP, you cannot sign up for this event.");
//                    return 0;
//                }
//            }
//            if (eventManager.eventAvailable(eventID, "Attendee")) {
//                if (attendeeManager.signUpAttendee(username, eventID)) {
//                    eventManager.updateNumAttendees(eventID, 1);
//                    programPresenter.successMessage("You have signed up for your desired event.");
//                    return 1;
//                }
//                else {
//                    programPresenter.errorMessage("You are already signed up for the event.");
//                    return 2;
//                }
//            }
//            else {
//                programPresenter.errorMessage("The event is not available or doesn't exist.");
//                return 3;
//            }
//        }
//        return 3;
//    }
//    // Removes attendee from an event
//    public boolean attendeeRemoveEvent(String username, String eventID) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("REMOVING EVENT");
//            programPresenter.inputPrompt("event ID (-1 to go back)");
//            //String eventID = scan.nextLine();
//            if (eventID.equals("-1")) {
//                break;
//            }
//            if (attendeeManager.cancelEvent(username, eventID)) {
//                eventManager.updateNumAttendees(eventID, -1);
//                programPresenter.successMessage("You have removed the event from your schedule.");
//                return true;
//
//            }
//            else {
//                programPresenter.errorMessage("Could not remove event.");
//                return false;
//            }
//        }
//        return false;
//    }
//
//    public boolean attendeeCheckVIP(String username) {
//        programPresenter.spaceBreak();
//        return attendeeManager.checkVIPStatus(username);
//    }
//
//    // Add room
//    public boolean organizerAddRoom(String roomName, int roomCapacity) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("ADDING ROOM");
//            programPresenter.inputPrompt("room (-1 to go back)");
//            // String room = scan.nextLine();
//            if (roomName.equals("-1")) {
//                return false;
//            }
//            programPresenter.inputPrompt("capacity (must > 0)");
//            // String stringRoomCapacity = scan.nextLine();
//            // int roomCapacity = Integer.parseInt(stringRoomCapacity);
//            if (eventManager.addRoom(roomName, roomCapacity) && roomCapacity > 0) {
//                programPresenter.successMessage("Room successfully added.");
//                return true;
//            }
//            else {
//                programPresenter.errorMessage("Room already exists or capacity entered is not valid.");
//                return false;
//            }
//        }
//    }
//
//    // TODO: USE REGEX TO ENSURE VALID TIME AND DATE?
//    // Helper function to parse validity of input information from scheduling event
//    /**
//     * Checks validity of input information from scheduling event.
//     * @param eventID id of the event being scheduled
//     * @param room room of the event being scheduled
//     * @param startTime starting time of the event being scheduled
//     * @param endTime ending time of the event being scheduled
//     * @param date date of the event being scheduled
//     * @param capacity capacity of the event being scheduled
//     * @return true if event is scheduled
//     */
//    public boolean validScheduleInfo(String eventID, String room, String startTime, String endTime,
//                                     String date, int capacity) {
//        String timeExpression = "(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]";
//        String dateExpression = "(([0-1]?[0-9])|(2[0-9])|(3[0-1])/(([0-1]?[0-9])|(2[0-9])|(3[0-1])))";
//        if (!eventManager.canSchedule(eventID)) {
//            programPresenter.errorMessage("Event does not exist or is already scheduled.");
//            return false;
//        }
//        if (!eventManager.roomExists(room)) {
//            programPresenter.errorMessage("Room does not exist.");
//            return false;
//        }
//        if (LocalTime.parse(startTime).isAfter(LocalTime.parse(endTime))) {
//            programPresenter.errorMessage("Entered times are not valid.");
//            return false;
//        }
//        if (capacity <= 0) {
//            programPresenter.errorMessage("Invalid capacity - the capacity must be at least 1.");
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * Schedules an event
//     * @param eventID id of the event being scheduled
//     * @param room room of the event being scheduled
//     * @param startTime starting time of the event being scheduled
//     * @param endTime ending time of the event being scheduled
//     * @param roomCapacity room capacity of the event being scheduled
//     * @param date date of the event being scheduled
//     * @param vipStatus vip status of the event being scheduled
//     * @return true if event is scheduled
//     */
//    public boolean organizerScheduleEvent(String eventID, String room, String startTime, String endTime, String roomCapacity, String date, boolean vipStatus) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("SCHEDULING EVENT");
//            programPresenter.showUnscheduledEvents(eventManager.getUnscheduledEvents());
//            programPresenter.inputPrompt("event ID (-1 to go back)");
//            //String eventID = scan.nextLine();
//            if (eventID.equals("-1")) {
//                break;
//            }
//            programPresenter.inputPrompt("room");
//            //String room = scan.nextLine();
//            programPresenter.inputPrompt("start time (HH:MM)");
//            //String startTime = scan.nextLine();
//            programPresenter.inputPrompt("end time (HH:MM)");
//            //String endTime = scan.nextLine();
//            programPresenter.inputPrompt("date (DD/MM/YYYY)");
//            //String date = scan.nextLine();
//            programPresenter.inputPrompt("maximum capacity");
//            //String stringCapacity = scan.nextLine();
//            programPresenter.inputPrompt("if this event is for VIPs only (y/n)");
//            //String StringVIP = scan.nextLine();
//
//            int capacity = Integer.parseInt(roomCapacity);
//            if (!validScheduleInfo(eventID, room, startTime, endTime, date, capacity)) {
//                return false;
//            }
//
//            if (eventManager.scheduleEvent(eventID, room, startTime, endTime, date, capacity, vipStatus)) {
//                programPresenter.successMessage("Event has been scheduled for " + startTime + " to "
//                        + endTime + ", " + date + ".");
//                return true;
//            }
//            else {
//                programPresenter.errorMessage("Event could not be added.");
//                return false;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Creates a speaker.
//     */
//    public void organizerCreateSpeaker() {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("CREATING SPEAKER ACCOUNT");
//            programPresenter.inputPrompt("speaker username (-1 to go back)");
//            String username = scan.nextLine();
//            if (username.equals("-1")) {
//                break;
//            }
//            programPresenter.inputPrompt("speaker password");
//            String password = scan.nextLine();
//            if (!userList.containsKey(username)) {
//                speakerManager.createUser(username, password);
//                userList.put(username, password);
//                ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
//                conferenceDataSaver.writeUserInfo(username, password, "Speaker");
//                programPresenter.successMessage("Speaker account successfully created.");
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Username already exists.");
//            }
//        }
//    }
//
//    /**
//     * Assigns a speaker to a particular event.
//     * @param eventID event id of the event
//     * @param username username of the speaker
//     */
//    public boolean organizerAssignSpeaker(String eventID, String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("ASSIGNING SPEAKER");
//            programPresenter.inputPrompt("event ID (-1 to go back)");
//            //String eventID = scan.nextLine();
//            if (eventID.equals("-1")) {
//                break;
//            }
//            programPresenter.inputPrompt("speaker username");
//            //String username = scan.nextLine();
//            if (eventManager.eventAvailable(eventID, "Speaker")) {
//                List<String> speakerEvents = speakerManager.getSpeakerEvents(username);
//                if (eventManager.addSpeaker(speakerEvents, eventID, username)) {
//                    speakerManager.assignSpeaker(username, eventID);
//                    programPresenter.successMessage("Speaker has been added to the event.");
//                    return true;
//
//                }
//                else {
//                    programPresenter.errorMessage("Speaker could not be added to the event.");
//                    return false;
//                }
//            }
//            else {
//                programPresenter.errorMessage("Speaker cannot be added to the event.");
//                return false;
//            }
//
//        }
//        return false;
//    }
//
//    /**
//     * Creates a regular or vip attendee.
//     */
//    public void organizerCreateAttendee() {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("CREATING ATTENDEE ACCOUNT");
//            programPresenter.inputPrompt("attendee username (-1 to go back)");
//            String username = scan.nextLine();
//            if (username.equals("-1")) {
//                break;
//            }
//            programPresenter.inputPrompt("attendee password");
//            String password = scan.nextLine();
//            if (!userList.containsKey(username)) {
//                programPresenter.inputPrompt("if this attendee is a VIP (y)");
//                String vipInput = scan.nextLine().trim();
//                if (vipInput.equals("y")){
//                    attendeeManager.createVIPUser(username, password);
//                } else {
//                    attendeeManager.createUser(username, password);
//                }
//                userList.put(username, password);
//                ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
//                conferenceDataSaver.writeUserInfo(username, password, "Attendee");
//                programPresenter.successMessage("Attendee account successfully created.");
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Username already exists.");
//            }
//        }
//    }
//
//    /**
//     * Cancels a scheduled event.
//     */
//    public void organizerCancelEvent() {
//        while (true){
//            programPresenter.spaceBreak();
//            programPresenter.title("CANCEL EVENT");
//
//            programPresenter.inputPrompt("event ID (-1 to go back)");
//            String eventID = scan.nextLine();
//            if (eventID.equals("-1")) {
//                break;
//            }
//            if (eventManager.removeScheduledEvent(eventID)) {
//                attendeeManager.cancelEventAllAttendees(eventID);
//                eventManager.removeEventSpeakers(eventID);
//                programPresenter.successMessage("You have removed the event from the conference schedule.");
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Could not remove event.");
//            }
//        }
//    }
//
//    /**
//     * Returns summary stats of the conference.
//     * @param choice choice of statistic
//     * @return summary stats of the conference
//     */
//    public String organizerSummaryStats(String choice){
//
//            programPresenter.spaceBreak();
//            programPresenter.title("SUMMARY STATISTICS");
//            programPresenter.spaceBreak();
//            programPresenter.showSummaryOptions();
//            programPresenter.inputPrompt("choice (-1 to go back)");
//
//            if (choice.equals("AppStats")) {
//                int num_users = attendeeManager.getNumAttendees();
//                int num_speakers = speakerManager.getNumSpeakers();
//                int num_vip_users = attendeeManager.getNumVipAttendees();
//                int num_regular_users = num_users - num_vip_users;
//                String str = "";
//                return "Total number of Attendees: " + num_users + "\n" + "[2] Total number of Speakers: " +
//                        num_speakers + "\n" + "[3] Number of VIP users: " + num_vip_users + "\n" + "[4] Number of non-VIP attendees: "
//                        + num_regular_users;
//            }
//            else if(choice.equals("AttendanceStats")) {
//                List<String> eventsStats_bottom = new ArrayList<>();
//                List<String> eventsStatsReverse = eventManager.getEventsStats();
//                Collections.reverse(eventsStatsReverse);
//                int i = 1;
//                for (String e : eventsStatsReverse) {
//                    if (i <= 5) {
//                        eventsStats_bottom.add(e);
//                    }
//                    i++;
//                }
//                //programPresenter.showEventsStats(eventsStats_bottom);
//                String str = "Top 5 Events - Highest attendance"+"\n";
//                for (String e : eventsStats_bottom) {
//                    String str_temp = e + "\n";
//                    str = str.concat(str_temp);
//                }
//                str += " \nTop 5 Events - Lowest Attendance\n";
//
//
//                List<String> eventsStats_top = new ArrayList<>();
//                int k = 1;
//                for (String e : eventManager.getEventsStats()) {
//                    if (k <= 5) {
//                        eventsStats_top.add(e);
//                    }
//                    k++;
//                }
//                //programPresenter.showEventsStats(eventsStats_top);
//                String str1 = "";
//                for (String e : eventsStats_top) {
//                    String str_temp = e + "\n";
//                    str1 = str1.concat(str_temp);
//                }
//
//                return str+str1;
//            }
//            else if (choice.equals("Basic")) {
//                int totalEvents = eventManager.numEvents();
//                int numVipEvents = eventManager.numVipEvents();
//                int numRegularEvents = totalEvents - numVipEvents;
//                programPresenter.eventStatistics(totalEvents, numVipEvents, numRegularEvents);
//                return  "[1] Total Number of events: "+totalEvents+"\n" + "[2] Number of VIP Events: "+
//                        numVipEvents + "\n" + "[3] Number of Regular Events: "+ numRegularEvents;
//            }
//            else if (choice.equals("SpeakerStats")) {
//                List<String> speaker_bottom = new ArrayList<>();
//                List<String> speakersStatsReverse = speakerManager.getSpeakerStats();
//                Collections.reverse(speakersStatsReverse);
//                int i = 1;
//                for (String e : speakersStatsReverse) {
//                    if (i <= 5) {
//                        speaker_bottom.add(e);
//                    }
//                    i++;
//                }
//                programPresenter.showEventsStats(speaker_bottom);
//                String str = "Top 5 Speakers - Most Events Spoken At"+"\n";
//                for (String e : speaker_bottom) {
//                    String str_temp = e + "\n";
//                    str = str.concat(str_temp);
//                }
//                str += " \nTop 5 Speakers - Least Events Spoken At\n";
//
//                List<String> speaker_top = new ArrayList<>();
//                int k = 1;
//                for (String e : speakerManager.getSpeakerStats()) {
//                    if (k <= 5) {
//                        speaker_top.add(e);
//                    }
//                    k++;
//                }
////                programPresenter.showEventsStats(speaker_top);
//                String str1 = "";
//                for (String e : speaker_top) {
//                    String str_temp = e + "\n";
//                    str1 = str1.concat(str_temp);
//                }
//                return str+str1;
//            }
//            else {
//                return "0";
//            }
//    }
//
//    /**
//     * updates the capacity of the the event and returns an indicator if it worked.
//     * @param eventID event if of the event
//     * @param capacity capacity of the event
//     * return true if capacity has been successfully updated
//     */
//    public String organizerUpdateCapacity(String eventID, String capacity) {
//
//            programPresenter.spaceBreak();
//            programPresenter.title("UPDATING CAPACITY");
//            programPresenter.inputPrompt("event ID (-1 to go back)");
//            if (eventID.equals("-1")) {
//                return "-1";
//            }
//            programPresenter.inputPrompt("capacity");
//            if (eventManager.updateCapacity(eventID, Integer.parseInt(capacity))) {
//                programPresenter.successMessage("Event capacity successfully updated.");
//                return "1";
//            }
//            else {
//                programPresenter.errorMessage("Invalid capacity or event has not been scheduled yet.");
//                return "0";
//            }
//
//    }
//
//
//
//    // next 2 methods:
//    // use 2-dimensional arraylist serialization
//    // for organizer to get numbered list of users' requests, and refer to specific requests easily,
//    // in case of mistyping requests
//    public void userAddRequest(){
//        programPresenter.spaceBreak();
//        programPresenter.inputPrompt("your request");
//        String request = scan.nextLine();
//        String status = "Pending";
//        ArrayList<String> requestLine = new ArrayList<>();
//        requestLine.add(request);
//        requestLine.add(status);
//        userRequest.add(requestLine);
//    }
//    public void organizerChangeRequestStatus(){
//        programPresenter.spaceBreak();
//        programPresenter.showUserRequest(userRequest);
//        String idx = scan.nextLine().trim();
//        int index = Integer.parseInt(idx);
//        String status = "Addressed";
//        userRequest.get(index).set(1, status);
//    }
//
//    /**
//     *      ---MAIN SYSTEMS---
//     */
//
//    public void attendeeScheduleSystem(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.spaceBreak();
//            programPresenter.title("ATTENDEE SCHEDULE MENU");
//            programPresenter.scheduleOptions("Attendee");
//            String scheduleOption = scan.nextLine();
//            if (scheduleOption.equals("1") ) {
//                programPresenter.printSchedule(eventManager.getSchedule(Collections.emptyList(), true));
//            }
//            else if (scheduleOption.equals("2")) {
//                List<String> events = attendeeManager.getAttendeeEvents(username);
//                programPresenter.printSchedule(eventManager.getSchedule(events, false));
//            }
//            else if (scheduleOption.equals("3")) {
//                //attendeeAddEvent(username);
//            }
//            else if (scheduleOption.equals("4")) {
//                //attendeeRemoveEvent(username);
//            }
//            else if (scheduleOption.equals("5")) {
//                attendeeCheckVIP(username);
//            }
//            else if (scheduleOption.equals("6")){
//                userAddRequest();
//            }
//            else if (scheduleOption.equals("-1")) {
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Invalid choice.");
//            }
//        }
//    }
//
//    public HashMap<String, List<String>> getEventSchedule() {
//        return eventManager.getSchedule(Collections.emptyList(), true);
//    }
//
//    public void speakerScheduleSystem(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("SPEAKER SCHEDULE MENU");
//            programPresenter.scheduleOptions("Speaker");
//            String scheduleOption = scan.nextLine();
//            if (scheduleOption.equals("1")) {
//                programPresenter.printSchedule(eventManager.getSchedule(Collections.emptyList(), true));
//            }
//            else if (scheduleOption.equals("2")) {
//                List<String> events = speakerManager.getSpeakerEvents(username);
//                programPresenter.printSchedule(eventManager.getSchedule(events, false));
//            }
//            else if (scheduleOption.equals("-1")) {
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Invalid choice.");
//            }
//        }
//    }
//
//
//    public void organizerScheduleSystem(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("ORGANIZER SCHEDULE MENU");
//            programPresenter.scheduleOptions("Organizer");
//            String scheduleOption = scan.nextLine();
//            if (scheduleOption.equals("1") ) {
//                programPresenter.printSchedule(eventManager.getSchedule(Collections.emptyList(), true));
//            }
//            else if (scheduleOption.equals("2")) {
//                //organizer
//                //
//                //m();
//            }
//
//            else if (scheduleOption.equals("3")) {
//                //organizerScheduleEvent();
//            }
//            else if (scheduleOption.equals("4")) {
//                organizerCreateSpeaker();
//            }
//            else if (scheduleOption.equals("5")) {
//               // organizerAssignSpeaker();
//            }
//            else if (scheduleOption.equals("6")) {
//                organizerCreateAttendee();
//            }
//            else if (scheduleOption.equals("7")) {
//                organizerCancelEvent();
//            }
//            else if (scheduleOption.equals("8")) {
//                //organizerSummaryStats();
//            }
//            else if (scheduleOption.equals("9")){
//                organizerChangeRequestStatus();
//            }
//            else if (scheduleOption.equals("-1")) {
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Invalid choice.");
//            }
//        }
//    }
//
//
//
//}
