package Controller;

import Gateway.ConferenceDataSaver;
import PresenterLayer.ProgramPresenter;
import UseCase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LoginSystem {

    private String currentUser = "";
    private String currentUserType = "";
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private MessageManager messageManager;
    private HashMap<String, String> userList;
    private ConferenceDataSaver conferenceDataSaver;
    private ArrayList<ArrayList<String>> userRequest;

    ProgramPresenter loginPresenter = new ProgramPresenter();
    Scanner scan = new Scanner(System.in);

    public LoginSystem(AttendeeManager attendeeManager, SpeakerManager speakerManager,
                       OrganizerManager organizerManager, HashMap<String, String> userList,ConferenceDataSaver conferenceDataSaver, ArrayList<ArrayList<String>> userRequest) {
        this.attendeeManager = attendeeManager;
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.userList = userList;
        this.conferenceDataSaver=conferenceDataSaver;
        this.userRequest = userRequest;
    }
//    //should remove this code
//    public LoginSystem() {
//        ConferenceDataReceiver conferenceDataReceiver = new ConferenceDataReceiver();
//        EventManager eventManager = conferenceDataReceiver.readEventManager();
//        MessageManager messageManager = conferenceDataReceiver.readMessageManager();
//        AttendeeManager attendeeManager = conferenceDataReceiver.readAttendeeManager();
//        SpeakerManager speakerManager = conferenceDataReceiver.readSpeakerManager();
//        OrganizerManager organizerManager = conferenceDataReceiver.readOrganizerManager();
//        HashMap<String, String> userList = conferenceDataReceiver.readUserList();
//        assignUseCase(eventManager, messageManager, attendeeManager, speakerManager, organizerManager, conferenceDataReceiver, userList);
//    }
//
//    //should remove this code.
//    private void assignUseCase(EventManager eventManager, MessageManager messageManager, AttendeeManager attendeeManager, SpeakerManager speakerManager, OrganizerManager organizerManager, ConferenceDataReceiver conferenceDataReceiver, HashMap<String, String> userList) {
//        this.attendeeManager = attendeeManager;
//        this.messageManager = messageManager;
//        this.speakerManager = speakerManager;
//        this.organizerManager = organizerManager;
//        this.userList = userList;
//        this.conferenceDataReceiver = conferenceDataReceiver;
//    }

    /**
     * Returns the current user's username.
     * @return the current user's username.
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the current user's user type.
     * @return the current user's user type.
     */
    public String getCurrentUserType() {
        return currentUserType;
    }

    /**
     * Returns the data of all attendees.
     * @return all attendees' data.
     */
    public AttendeeManager getAttendeeManager() {
        return attendeeManager;
    }

    /**
     * Returns the data of all organizers.
     * @return all organizers' data.
     */
    public OrganizerManager getOrganizerManager() {
        return organizerManager;
    }

    /**
     * Returns the data of all organizers.
     * @return all speakers' data.
     */
    public SpeakerManager getSpeakerManager() {
        return speakerManager;
    }

    /**
     * Returns the user details of all users in the system.
     * @return all users' details.
     */
    public HashMap<String, String> getUserList() {
        return userList;
    }


    /**
     * Signs up the user and returns true if signup is successful.
     * @param un username input by user
     * @param pw password input by user
     * @param usertype usertype input by user
     * @return true if signup is successful
     */
    public boolean signUp(String un, String pw, String usertype) {
        loginPresenter.signUpWelcome();
        String username = "";
        loginPresenter.inputPrompt("a unique username");
        if (userList.containsKey(un)) {
            loginPresenter.errorMessage("Username is already taken.");
            return false;
        } else {
            loginPresenter.successMessage("Username accepted.");
            username = un;
        }
        // Set password
        loginPresenter.inputPrompt("a new password");
        String password = pw;
      //  loginPresenter.successMessage("Password accepted.");
        // Set user type
        while (true) {
            loginPresenter.promptUserType();
            if (usertype.equals("Organizer")) {
                organizerManager.createUser(username, password); // Creates a new user in the corresponding use case
                userList.put(username, password); // Add to user list
                conferenceDataSaver.saveUserList(userList);
                currentUserType = "Organizer"; // Set user type
                break;
            } else if (usertype.equals("Speaker")) {
                speakerManager.createUser(username, password);
                userList.put(username, password);
                conferenceDataSaver.saveUserList(userList);
                currentUserType = "Speaker";
                break;
            } else if (usertype.equals("Attendee")) {
                attendeeManager.createUser(username, password);
                userList.put(username, password);
                conferenceDataSaver.saveUserList(userList);
                currentUserType = "Attendee";
                break;
            } else if (usertype.equals("VIP Attendee")) {
                attendeeManager.createVIPUser(username, password);
                userList.put(username, password);
                conferenceDataSaver.saveUserList(userList);
                currentUserType = "VIP Attendee";
                break;
            } else {
                loginPresenter.errorMessage("Invalid choice.");
            }
        }
        loginPresenter.successMessage("You have created a new account.");
        currentUser = username;
        conferenceDataSaver.writeUserInfo(username, password, currentUserType);
        return true;
    }

    /**
     * Logs in the user and returns true if login is successful.
     * @param username username input by user
     * @param password password input by user
     * @param usertype usertype input by user
     * @return true if login is successful
     */
    public boolean logIn(String username, String password, String usertype) {
        loginPresenter.loginWelcome();
        String logInUsername = "";
        String logInPassword = "";
        boolean canLogIn = true;

        // Loop for checking if user exists
        loginPresenter.inputPrompt("your username");
        //String uName = scan.nextLine();
        if (userList.containsKey(username)) {
            loginPresenter.successMessage("Your username exists.");
            logInUsername = username;
        } else {
            loginPresenter.errorMessage("User does not exist.");
            return false;
        }

        // Loop for checking if password matches user
        loginPresenter.inputPrompt("your password");
        //String pWord = scan.nextLine();
        if (userList.get(logInUsername).equals(password)) {
            loginPresenter.successMessage("You are now logged in.");
        } else {
            loginPresenter.errorMessage("Wrong password.");
            return false;
        }

        if (attendeeManager.checkUserExistence(username)) {
            currentUserType = "Attendee";
        } else if (organizerManager.checkUserExistence(username)) {
            currentUserType = "Organizer";
        } else if (speakerManager.checkUserExistence(username)) {
            currentUserType = "Speaker";
        }
        currentUser = username; // Now tracking logged in user
        currentUserType=usertype;
        return true;
    }

    /**
     *      ---MAIN SYSTEMS---
     */

    //NO need of this form.
//     public void loginMainSystem() {
//         EventQueue.invokeLater(new Runnable() {
//             public void run() {
//                 try {
//                     WelcomeForm frame = new WelcomeForm();
//                     frame.setVisible(true);
//
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                 }
//             }
//
//         });
//         while (true) {
//             loginPresenter.spaceBreak();
//             loginPresenter.loginOptions();
//             String loginChoice = scan.nextLine();
//             if (loginChoice.equals("1")) {
//                 EventQueue.invokeLater(new Runnable() {
//                     public void run() {
//                         try {
//                             SignUpForm frame = new SignUpForm();
//                             frame.setVisible(true);
//                         } catch (Exception e) {
//                             e.printStackTrace();
//                         }
//                     }
//                 });
//                 break;
//             }
//             else if (loginChoice.equals("2")) {
//                 logIn();
//                 break;
//             }
//             else if (loginChoice.equals("3")) {
//                 System.exit(0);
//             }
//             else {
//                 loginPresenter.errorMessage("Invalid input");
//             }
//         }
     }


