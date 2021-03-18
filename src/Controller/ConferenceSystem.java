//package Controller;
//
//import Gateway.*;
//import PresenterLayer.ProgramPresenter;
//import UseCase.*;
//import Controller.*;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * Top level class that contains the main controller method.
// */
//public class ConferenceSystem {
//
//    ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
//
//    /**
//     * Highest level method, called directly by the user application, all systems converge here.
//     * @throws FileNotFoundException when files required for gateway methods do not exist (only EventList.txt needed)
//     */
//    public void run() throws FileNotFoundException {
//
//        Scanner scan = new Scanner(System.in);
//        ProgramPresenter programPresenter = new ProgramPresenter();
//        ConferenceDataReceiver conferenceDataReceiver = new ConferenceDataReceiver();
//        ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
//
//        // Set all data from use cases (use cases are the main storage system for data)
//        EventManager eventManager = conferenceDataReceiver.readEventManager();
//        MessageManager messageManager = conferenceDataReceiver.readMessageManager();
//        AttendeeManager attendeeManager = conferenceDataReceiver.readAttendeeManager();
//        SpeakerManager speakerManager = conferenceDataReceiver.readSpeakerManager();
//        OrganizerManager organizerManager = conferenceDataReceiver.readOrganizerManager();
//        HashMap<String, String> userList = conferenceDataReceiver.readUserList();
//        ArrayList<ArrayList<String>> userRequest = conferenceDataReceiver.readUserRequest();
//
//        // Check if events are already read in, read in events and create event objects in eventManager if not
//        if (!eventManager.hasEvents()) {
//            List<String> eventList = conferenceDataReceiver.readEventList();
//            for (String e : eventList) {
//                eventManager.createEvent(e);
//            }
//        }
//
//
//
//
//        // Instantiate LoginSystem with all login related data (user lists and usernames and their type)
//        LoginSystem loginSystem = new LoginSystem(attendeeManager, speakerManager, organizerManager, userList, conferenceDataSaver, userRequest);
//
//        // Welcome to the program, log in or sign up
//        programPresenter.title("CONFERENCE APP");
//        programPresenter.welcomeMessage();
//
//        // User signs in
////        LoginSystem.SignUp();
////        saveData(eventManager, messageManager, attendeeManager, speakerManager, organizerManager, userList);
//
//        // Tracking username and user's type from login
//        String currentUser = loginSystem.getCurrentUser();   //name of user logged in.
//        String currentUserType = loginSystem.getCurrentUserType();   // eg: attendee, organizer etc.
//
//        // Change reference to get updated info from use cases after loginSystem has modified them
//        attendeeManager = loginSystem.getAttendeeManager();
//        speakerManager = loginSystem.getSpeakerManager();
//        organizerManager = loginSystem.getOrganizerManager();
//        userList = loginSystem.getUserList();
//        saveData(eventManager, messageManager, attendeeManager, speakerManager, organizerManager, userList, userRequest);
//
//        // Instantiate scheduling and message system controllers with use cases
//        ScheduleSystem scheduleSystem = new ScheduleSystem(eventManager, attendeeManager, speakerManager,
//                organizerManager, userList, userRequest);
//        MessageSystem messageSystem = new MessageSystem(eventManager, messageManager, attendeeManager, speakerManager,
//                organizerManager, userList);
//
//        // User welcome message
//        programPresenter.spaceBreak();
//        programPresenter.welcomeUser("[" + currentUserType + "] " + currentUser);
//
//        // Main program loop
//        while(true) {
//
//            // Prompt user to access either scheduling system or messaging system
//            programPresenter.spaceBreak();
//            programPresenter.title("MAIN MENU");
//            programPresenter.systemOptions();
//            String systemOption = scan.nextLine();
//
//            if (systemOption.equals("1")) {
//                if (currentUserType.equals("Attendee")) {
//                    scheduleSystem.attendeeScheduleSystem(currentUser);
//                }
//                else if (currentUserType.equals("Speaker")) {
//                    scheduleSystem.speakerScheduleSystem(currentUser);
//                }
//                else if (currentUserType.equals("Organizer")) {
//                    scheduleSystem.organizerScheduleSystem(currentUser);
//                }
//                // New references to potentially modified use cases from scheduling
//                attendeeManager = scheduleSystem.getAttendeeManager();
//                speakerManager = scheduleSystem.getSpeakerManager();
//                eventManager = scheduleSystem.getEventManager();
//                // Update references in messageSystem
//                messageSystem.setAttendeeManager(attendeeManager);
//                messageSystem.setSpeakerManager(speakerManager);
//                messageSystem.setEventManager(eventManager);
//            }
//
//            else if (systemOption.equals("2")) {
//                if (currentUserType.equals("Attendee")) {
//                    messageSystem.attendeeMessageSystem(currentUser);
//                }
//                else if (currentUserType.equals("Speaker")) {
//                    messageSystem.speakerMessageSystem(currentUser);
//                }
//                else if (currentUserType.equals("Organizer")) {
//                    messageSystem.organizerMessageSystem(currentUser);
//                }
//                // New references to potentially modified use cases from messaging
//                messageManager = messageSystem.getMessageManager();
//            }
//
//            else if (systemOption.equals("3")) {
//                programPresenter.logOut(currentUser);
//                break;
//            }
//
//            else {
//                programPresenter.errorMessage("Invalid choice.");
//            }
//        } // Ends program loop
//
//        // Saving all data
//        saveData(eventManager, messageManager, attendeeManager, speakerManager, organizerManager, userList, userRequest);
//
//    }
//    private void saveData(EventManager em, MessageManager mm, AttendeeManager am, SpeakerManager sm, OrganizerManager om, HashMap<String,String> userList, ArrayList<ArrayList<String>> userRequest) {
//        ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
//        conferenceDataSaver.saveEventManager(em);
//        conferenceDataSaver.saveMessageManager(mm);
//        conferenceDataSaver.saveAttendeeManager(am);
//        conferenceDataSaver.saveSpeakerManager(sm);
//        conferenceDataSaver.saveOrganizerManager(om);
//        conferenceDataSaver.saveUserList(userList);
//        conferenceDataSaver.saveUserRequest(userRequest);
//    }
//
//
//}
//
//
