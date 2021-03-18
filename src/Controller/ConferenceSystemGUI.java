package Controller;

import Gateway.*;
import PresenterLayer.ProgramPresenter;
import UseCase.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Top level class that contains the main controller method.
 */
public class ConferenceSystemGUI {

    private EventManager eventManager;
    private MessageManager messageManager;
    private AttendeeManager attendeeManager;
    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;
    private HashMap<String, String> userList;
    private ConferenceDataReceiver conferenceDataReceiver;
    private ConferenceDataSaver conferenceDataSaver;
    private LoginSystem loginSystem;
    private ScheduleSystemGUI scheduleSystemGUI;
    private MessageSystemGUI messageSystem;
    private String currentUser;
    private String currentUserType;

    private ArrayList<ArrayList<String>> userRequest;


    public ConferenceSystemGUI(EventManager eventManager, MessageManager messageManager, AttendeeManager attendeeManager, SpeakerManager speakerManager, OrganizerManager organizerManager, HashMap<String, String> userList, ConferenceDataSaver conferenceDataSaver) {
        this.eventManager = eventManager;
        this.messageManager = messageManager;
        this.attendeeManager = attendeeManager;
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.conferenceDataSaver = conferenceDataSaver;
        this.userList = userList;
    }

    public boolean runLoginSystem(String username, String password, String usertype, String choice) {
        loginSystem = new LoginSystem(attendeeManager, speakerManager, organizerManager, userList, conferenceDataSaver, userRequest);
        boolean successful = false;
        if (choice.equalsIgnoreCase("logIn")) {
            successful = loginSystem.logIn(username, password, usertype);
        }
        if (choice.equalsIgnoreCase("SignUp")) {
            successful = loginSystem.signUp(username, password, usertype);
        }
        if (successful) {
            this.currentUser = loginSystem.getCurrentUser();
            this.currentUserType = loginSystem.getCurrentUserType();
            // Change reference to get updated info from use cases after loginSystem has modified them
            attendeeManager = loginSystem.getAttendeeManager();
            speakerManager = loginSystem.getSpeakerManager();
            organizerManager = loginSystem.getOrganizerManager();
            userList = loginSystem.getUserList();
            saveData(eventManager, messageManager, attendeeManager, speakerManager, organizerManager, userList);
            return true;
        }
        return false;
    }

    public void assignSystem() {
        this.scheduleSystemGUI = new ScheduleSystemGUI(eventManager, attendeeManager, speakerManager,
                organizerManager, userList);
        this.messageSystem = new MessageSystemGUI(eventManager, messageManager, attendeeManager, speakerManager,
                organizerManager, userList);
    }

    public String runSchedulingSystem(String option, String userInput) {
        String data = "";
        System.out.println(currentUser);
        System.out.println(currentUserType);
        assignSystem();
        if (currentUserType.equals("Attendee") || currentUserType.equalsIgnoreCase("VIP Attendee")) {
            data = scheduleSystemGUI.attendeeScheduleSystem(currentUser, option, userInput);
        } else if (currentUserType.equals("Speaker")) {
            data = scheduleSystemGUI.speakerScheduleSystem(currentUser, option, userInput);
        } else if (currentUserType.equals("Organizer")) {
            data = scheduleSystemGUI.organizerScheduleSystem(currentUser, option, userInput);
            System.out.println(data);
        }
        // New references to potentially modified use cases from scheduling
        attendeeManager = scheduleSystemGUI.getAttendeeManager();
        speakerManager = scheduleSystemGUI.getSpeakerManager();
        eventManager = scheduleSystemGUI.getEventManager();
        // Update references in messageSystem
        messageSystem.setAttendeeManager(attendeeManager);
        messageSystem.setSpeakerManager(speakerManager);
        messageSystem.setEventManager(eventManager);
        return data;
    }

    public String runMessaaginSystem(String option, String userinput) {
        //return required data, can be 1/-1 or a nonempty string.
        String data = "";
        if (this.messageSystem == null) {
            assignSystem();
        }
        if (currentUserType.equals("Attendee") || currentUserType.equalsIgnoreCase("VIP Attendee")) {
            data = messageSystem.attendeeMessageSystem(currentUser, option, userinput);
        } else if (currentUserType.equals("Speaker")) {
            data = messageSystem.speakerMessageSystem(currentUser, option, userinput);
        } else if (currentUserType.equals("Organizer")) {
            data = messageSystem.organizerMessageSystem(currentUser, option, userinput);
        }
        // New references to potentially modified use cases from messaging
        messageManager = messageSystem.getMessageManager();
        return data;

    }


    public void saveData(EventManager em, MessageManager mm, AttendeeManager am, SpeakerManager sm, OrganizerManager om, HashMap<String, String> userList) {
        ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
        conferenceDataSaver.saveEventManager(em);
        conferenceDataSaver.saveMessageManager(mm);
        conferenceDataSaver.saveAttendeeManager(am);
        conferenceDataSaver.saveSpeakerManager(sm);
        conferenceDataSaver.saveOrganizerManager(om);
        conferenceDataSaver.saveUserList(userList);
    }

    public void logOut() {
        ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
        conferenceDataSaver.saveEventManager(this.eventManager);
        conferenceDataSaver.saveMessageManager(this.messageManager);
        conferenceDataSaver.saveAttendeeManager(this.attendeeManager);
        conferenceDataSaver.saveSpeakerManager(this.speakerManager);
        conferenceDataSaver.saveOrganizerManager(this.organizerManager);
        conferenceDataSaver.saveUserList(this.userList);
    }


    {
//        // Instantiate scheduling and message system controllers with use cases
//        ScheduleSystem scheduleSystem = new ScheduleSystem(eventManager, attendeeManager, speakerManager,
//                organizerManager, userList);
//        MessageSystem messageSystem = new MessageSystem(eventManager, messageManager, attendeeManager, speakerManager,
//                organizerManager, userList);
//
//        ProgramPresenter programPresenter = new ProgramPresenter();
//
//        // User welcome message
//        programPresenter.spaceBreak();
//        programPresenter.welcomeUser("[" + currentUserType + "] " + currentUser);

        // Main program loop
//        while(true) {
//
//            // Prompt user to access either scheduling system or messaging system
//            programPresenter.spaceBreak();
//            programPresenter.title("MAIN MENU");
//            programPresenter.systemOptions();
//            //tring systemOption = scan.nextLine();
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
//
//        } // Ends program loop
//
//        // Saving all data
//        saveData(eventManager, messageManager, attendeeManager, speakerManager, organizerManager, userList);
//
//    }
    }

}


