package Controller;

import PresenterLayer.ProgramPresenter;
import UseCase.*;

import java.util.*;

public class MessageSystemGUI {

    private EventManager eventManager = new EventManager();
    private MessageManager messageManager = new MessageManager();
    private AttendeeManager attendeeManager = new AttendeeManager();
    private SpeakerManager speakerManager = new SpeakerManager();
    private OrganizerManager organizerManager = new OrganizerManager();
    private HashMap<String, String> userList = new HashMap<>();

    ProgramPresenter programPresenter = new ProgramPresenter();
    Scanner scan = new Scanner(System.in);

    /**
     * Main constructor for the message system controller.
     * @param eventManager event use case
     * @param messageManager message use case
     * @param attendeeManager attendee use case
     * @param speakerManager speaker use case
     * @param organizerManager organizer use case
     * @param userList map of all usernames and passwords
     */
    public MessageSystemGUI(EventManager eventManager, MessageManager messageManager, AttendeeManager attendeeManager,
                         SpeakerManager speakerManager, OrganizerManager organizerManager,
                         HashMap<String, String> userList) {
        this.eventManager = eventManager;
        this.messageManager = messageManager;
        this.attendeeManager = attendeeManager;
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.userList = userList;
    }

    /**
     * Getter for message use case that will likely be modified during runtime.
     * @return message use case object
     */
    public MessageManager getMessageManager() {
        return messageManager;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void setAttendeeManager(AttendeeManager attendeeManager) {
        this.attendeeManager = attendeeManager;
    }

    public void setSpeakerManager(SpeakerManager speakerManager) {
        this.speakerManager = speakerManager;
    }

    /**
     *
     * @param username
     */
    public String userViewMessage(String username, String otherUser) {
            programPresenter.spaceBreak();
            programPresenter.title("VIEWING YOUR MESSAGES");
            List<String> messagedUsers = messageManager.getMessagedUsers(username);
            if (messagedUsers.isEmpty()) {
                programPresenter.errorMessage("You have no conversations with anyone.");
                return "No conversation with user";
            }
            programPresenter.showMessagedUsers(messagedUsers);
            programPresenter.inputPrompt("username of the other user to see your conversation (-1 to go back)");
            //String otherUser = scan.nextLine();
            if (otherUser.equals("-1")) {
                return "null";
            }
            if (userList.containsKey(otherUser) && messagedUsers.contains(otherUser)) {
                List<String> conversation = messageManager.getConversation(username, otherUser);
                if (conversation.isEmpty()) {
                    return ("You have no messages from " + otherUser + ".");
                }
                else {
                    StringBuilder str = new StringBuilder();
                    for (String message : conversation) {
                        str.append(message);
                        str.append(",");
                    }
                    return str.toString();

                }
            }
        return "null";
    }

    public String userViewContacts(String username) {
        List<String> contacts = messageManager.getContacts(username);
        StringBuilder str = new StringBuilder();
        for (String x : contacts) {
            str.append(x);
            str.append(",");
        }
        return str.toString();
            }


    public String userSendMessage(String username, String userinput) {
            programPresenter.spaceBreak();
            programPresenter.title("SENDING MESSAGE");
            programPresenter.inputPrompt("username of the user to message (-1 to go back)");
            //String otherUser = scan.nextLine();
            String [] str = userinput.split(",");
            if (str[0].equals("-1")) {
                return "null";
            }
            programPresenter.inputPrompt("your message");
            ///String messageContent = scan.nextLine();
            if (userList.containsKey(str[0])) {
                messageManager.sendMessage(username, str[1], str[0], str[2]);
                return ("Message successfully sent.");
            }
            else {
                return ("User does not exist.");
        }
    }

    public String userAddContact(String username, String contact) {

            programPresenter.spaceBreak();
            programPresenter.title("ADDING CONTACT");
            programPresenter.inputPrompt("username of contact (-1 to go back)");
            //String contact = scan.nextLine();
            if (contact.equals("-1")) {
                return "3";
            }
            if (userList.containsKey(contact)) {
                if (messageManager.addContact(username, contact)) {
                    // ("You have successfully added " + contact + " to your contacts.");
                    return "1";

                }
                else {
                    // ("You are already have + " + contact + " as a contact.");
                    return "2";
                }
            }
            else {
                return ("3");
            }
        }

    public String userDeleteContact(String username, String contact) {
            programPresenter.spaceBreak();
            programPresenter.title("DELETING CONTACT");
            programPresenter.inputPrompt("username of contact (-1 to go back)");
            //String contact = scan.nextLine();
            if (contact.equals("-1")) {
                return "null";
            }
            if (userList.containsKey(contact)) {
                if (messageManager.removeContact(username, contact)) {
                    return ("You have successfully removed " + contact + " from your list.");

                }
                else {
                    return (contact + " is not in your contacts.");
                }
            }
            else {
                return ("User does not exist.");
            }
    }

    public String speakerMessageAttendees(String username, String userinput) {
        programPresenter.spaceBreak();
        programPresenter.title("MESSAGING ALL ATTENDEES OF YOUR EVENTS");
        programPresenter.inputPrompt("message");
        //String messageContent = scan.nextLine();
        List<String> speakerEvents = speakerManager.getSpeakerEvents(username);
        List<String> eventAttendees = attendeeManager.getAttendeesOfEvents(speakerEvents);
        for (String a : eventAttendees) {
            messageManager.sendMessage(username, "Speaker", a, userinput);
        }
        return("Message successfully sent.");
    }

    public String organizerMultipleMessage(String username, String messageContent) {
        List<String> users = new ArrayList<>();
        programPresenter.spaceBreak();
        String[] str = messageContent.split(",");
        if (str[0].equals("Attendee")) {
            programPresenter.title("MESSAGING ALL ATTENDEES");
            users = attendeeManager.getUsers();
        }
        else if (str[0].equals("Speaker")) {
            programPresenter.title("MESSAGING ALL SPEAKERS");
            users = speakerManager.getUsers();
        }
        programPresenter.inputPrompt("message");
        //String messageContent = scan.nextLine();
        for (String u : users) {
            messageManager.sendMessage(username, "Organizer", u, str[1]);
        }
        return ("Message successfully sent.");
    }

    /**
     *      ---MAIN SYSTEMS---
     */

    public String attendeeMessageSystem(String username, String messageOption, String userinput) {
        //Depending on what the option i.e. messageoption run the relevant if statement and return a data.
            programPresenter.spaceBreak();
            programPresenter.title("MESSAGING");
            programPresenter.messageOptions("Attendee");
            if (messageOption.equals("ViewContacts")) {

                return userViewContacts(username);
            }
            else if (messageOption.equals("ShowMessages")) {

                return userViewMessage(username, userinput);
            }
            else if (messageOption.equals("SendMessage")) {

                return userSendMessage(username, userinput);
            }

            else if (messageOption.equalsIgnoreCase("ShowContacts")) {
                return userViewContacts(username);
            }
            else if (messageOption.equalsIgnoreCase("AddContact")) {
                return userAddContact(username, userinput);
            }
            else if (messageOption.equalsIgnoreCase("DeleteContact")) {
                return userDeleteContact(username, userinput);
            }

            else {
                return ("Invalid choice.");
            }
        }


    public String speakerMessageSystem(String username, String messageOption, String userinput) {
        //Depending on what the option i.e. messageoption run the relevant if statement and return a data.
        programPresenter.spaceBreak();
            programPresenter.title("MESSAGING");
            programPresenter.messageOptions("Speaker");
            if (messageOption.equals("ShowMessages")) {

                return userViewMessage(username, userinput);
            }
            else if (messageOption.equals("ViewContacts")) {

                return userViewContacts(username);
            }
            else if (messageOption.equalsIgnoreCase("AddContact")) {
                return userAddContact(username, userinput);
            }
            else if (messageOption.equalsIgnoreCase("DeleteContact")) {
                return userDeleteContact(username, userinput);
            }
            else if (messageOption.equals("MessageAllAttendees")) {
                return speakerMessageAttendees(username, userinput);
            }
            return "null";
    }

    public String organizerMessageSystem(String username, String messageOption, String userinput) {
        //Depending on what the option i.e. messageoption run the relevant if statement and return a data.
        programPresenter.spaceBreak();
            programPresenter.title("MESSAGING");
            programPresenter.messageOptions("Organizer");

        if (messageOption.equals("ShowMessages")) {

            return userViewMessage(username, userinput);
        }
        else if (messageOption.equals("SendMessage")) {

            return userSendMessage(username, userinput);
        }
        else if (messageOption.equals("ViewContacts")) {

            return userViewContacts(username);
        }
        else if (messageOption.equalsIgnoreCase("ShowContacts")) {
            return userViewContacts(username);
        }
        else if (messageOption.equalsIgnoreCase("AddContact")) {
            return userAddContact(username, userinput);
        }
        else if (messageOption.equalsIgnoreCase("DeleteContact")) {
            return userDeleteContact(username, userinput);
        }
        // userinput: "usertype, message"
        else if (messageOption.equalsIgnoreCase("MessageMultiple")) {
            return organizerMultipleMessage(username, userinput);
        }
        return null;
    }

}