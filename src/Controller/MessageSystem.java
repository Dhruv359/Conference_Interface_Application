//package Controller;
//
//import PresenterLayer.ProgramPresenter;
//import UseCase.*;
//
//import java.util.*;
//
//public class MessageSystem {
//
//    private EventManager eventManager = new EventManager();
//    private MessageManager messageManager = new MessageManager();
//    private AttendeeManager attendeeManager = new AttendeeManager();
//    private SpeakerManager speakerManager = new SpeakerManager();
//    private OrganizerManager organizerManager = new OrganizerManager();
//    private HashMap<String, String> userList = new HashMap<>();
//
//    ProgramPresenter programPresenter = new ProgramPresenter();
//    Scanner scan = new Scanner(System.in);
//
//    /**
//     * Main constructor for the message system controller.
//     * @param eventManager event use case
//     * @param messageManager message use case
//     * @param attendeeManager attendee use case
//     * @param speakerManager speaker use case
//     * @param organizerManager organizer use case
//     * @param userList map of all usernames and passwords
//     */
//    public MessageSystem(EventManager eventManager, MessageManager messageManager, AttendeeManager attendeeManager,
//                         SpeakerManager speakerManager, OrganizerManager organizerManager,
//                         HashMap<String, String> userList) {
//        this.eventManager = eventManager;
//        this.messageManager = messageManager;
//        this.attendeeManager = attendeeManager;
//        this.speakerManager = speakerManager;
//        this.organizerManager = organizerManager;
//        this.userList = userList;
//    }
//
//    /**
//     * Getter for message use case that will likely be modified during runtime.
//     * @return message use case object
//     */
//    public MessageManager getMessageManager() {
//        return messageManager;
//    }
//
//    public void setEventManager(EventManager eventManager) {
//        this.eventManager = eventManager;
//    }
//
//    public void setAttendeeManager(AttendeeManager attendeeManager) {
//        this.attendeeManager = attendeeManager;
//    }
//
//    public void setSpeakerManager(SpeakerManager speakerManager) {
//        this.speakerManager = speakerManager;
//    }
//
//    /**
//     * Shows all the the messages received by the user.
//     * @param username username of the user
//     */
//    public void userViewMessage(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("VIEWING YOUR MESSAGES");
//            List<String> messagedUsers = messageManager.getMessagedUsers(username);
//            if (messagedUsers.isEmpty()) {
//                programPresenter.errorMessage("You have no conversations with anyone.");
//                break;
//            }
//            programPresenter.showMessagedUsers(messagedUsers);
//            programPresenter.inputPrompt("username of the other user to see your conversation (-1 to go back)");
//            String otherUser = scan.nextLine();
//            if (otherUser.equals("-1")) {
//                break;
//            }
//            if (userList.containsKey(otherUser) && messagedUsers.contains(otherUser)) {
//                List<String> conversation = messageManager.getConversation(username, otherUser);
//                if (conversation.isEmpty()) {
//                    programPresenter.errorMessage("You have no messages from " + otherUser + ".");
//                }
//                else {
//                    programPresenter.showConversation(conversation, otherUser);
//                }
//            }
//        }
//    }
//
//    /**
//     * Sends a message to another user.
//     * @param username username of the user
//     * @param userType user type of the user
//     */
//    public void userSendMessage(String username, String userType) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("SENDING MESSAGE");
//            programPresenter.inputPrompt("username of the user to message (-1 to go back)");
//            String otherUser = scan.nextLine();
//            if (otherUser.equals("-1")) {
//                break;
//            }
//            programPresenter.inputPrompt("your message");
//            String messageContent = scan.nextLine();
//            if (userList.containsKey(otherUser)) {
//                messageManager.sendMessage(username, userType, otherUser, messageContent);
//                programPresenter.successMessage("Message successfully sent.");
//                break;
//            }
//            else {
//                programPresenter.errorMessage("User does not exist.");
//            }
//        }
//    }
//
//    /**
//     * Adds another user to the user's list of contacts
//     * @param username username of the user
//     */
//    public void userAddContact(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("ADDING CONTACT");
//            programPresenter.inputPrompt("username of contact (-1 to go back)");
//            String contact = scan.nextLine();
//            if (contact.equals("-1")) {
//                break;
//            }
//            if (userList.containsKey(contact)) {
//                if (messageManager.addContact(username, contact)) {
//                    programPresenter.successMessage("You have successfully added " + contact + " to your contacts.");
//                    break;
//                }
//                else {
//                    programPresenter.errorMessage("You are already have + " + contact + " as a contact.");
//                }
//            }
//            else {
//                programPresenter.errorMessage("User does not exist.");
//            }
//        }
//    }
//
//    /**
//     * Deletes a contact from the user's list of contacts.
//     * @param username username of the user
//     */
//    public void userDeleteContact(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("DELETING CONTACT");
//            programPresenter.inputPrompt("username of contact (-1 to go back)");
//            String contact = scan.nextLine();
//            if (contact.equals("-1")) {
//                break;
//            }
//            if (userList.containsKey(contact)) {
//                if (messageManager.removeContact(username, contact)) {
//                    programPresenter.successMessage("You have successfully removed " + contact + " from your list.");
//                    break;
//                }
//                else {
//                    programPresenter.errorMessage(contact + " is not in your contacts.");
//                }
//            }
//            else {
//                programPresenter.errorMessage("User does not exist.");
//            }
//        }
//    }
//
//    /**
//     * Send message to attendees of an event.
//     * @param username username of the user
//     */
//    public void speakerMessageAttendees(String username) {
//        programPresenter.spaceBreak();
//        programPresenter.title("MESSAGING ALL ATTENDEES OF YOUR EVENTS");
//        programPresenter.inputPrompt("message");
//        String messageContent = scan.nextLine();
//        List<String> speakerEvents = speakerManager.getSpeakerEvents(username);
//        List<String> eventAttendees = attendeeManager.getAttendeesOfEvents(speakerEvents);
//        for (String a : eventAttendees) {
//            messageManager.sendMessage(username, "Speaker", a, messageContent);
//        }
//        programPresenter.successMessage("Message successfully sent.");
//    }
//
//    /**
//     * Sends message to either all attendees or all speakers.
//     * @param username username of the user
//     * @param userType user type of the user
//     */
//    public void organizerMultipleMessage(String username, String userType) {
//        List<String> users = new ArrayList<>();
//        programPresenter.spaceBreak();
//        if (userType.equals("Attendee")) {
//            programPresenter.title("MESSAGING ALL ATTENDEES");
//            users = attendeeManager.getUsers();
//        }
//        else if (userType.equals("Speaker")) {
//            programPresenter.title("MESSAGING ALL SPEAKERS");
//            users = speakerManager.getUsers();
//        }
//        programPresenter.inputPrompt("message");
//        String messageContent = scan.nextLine();
//        for (String u : users) {
//            messageManager.sendMessage(username, "Organizer", u, messageContent);
//        }
//        programPresenter.successMessage("Message successfully sent.");
//    }
//
//    /**
//     *      ---MAIN SYSTEMS---
//     */
//
//    public void attendeeMessageSystem(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("MESSAGING");
//            programPresenter.messageOptions("Attendee");
//            String messageOption = scan.nextLine();
//            if (messageOption.equals("1")) {
//                userViewMessage(username);
//            }
//            else if (messageOption.equals("2")) {
//                userSendMessage(username, "Attendee");
//            }
//            else if (messageOption.equals("3")) {
//                programPresenter.spaceBreak();
//                programPresenter.title("YOUR CONTACTS");
//                programPresenter.showContacts(messageManager.getContacts(username), username);
//            }
//            else if (messageOption.equals("4")) {
//                userAddContact(username);
//            }
//            else if (messageOption.equals("5")) {
//                userDeleteContact(username);
//            }
//            else if (messageOption.equals("-1")) {
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Invalid choice.");
//            }
//        }
//    }
//
//    public List<String> getUserContacts(String username) {
//        return messageManager.getContacts(username);
//    }
//
//    public void speakerMessageSystem(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("MESSAGING");
//            programPresenter.messageOptions("Speaker");
//            String messageOption = scan.nextLine();
//            if (messageOption.equals("1")) {
//                userViewMessage(username);
//            }
//            else if (messageOption.equals("2")) {
//                userSendMessage(username, "Speaker");
//            }
//            else if (messageOption.equals("3")) {
//                programPresenter.spaceBreak();
//                programPresenter.title("YOUR CONTACTS");
//                programPresenter.showContacts(messageManager.getContacts(username), username);
//            }
//            else if (messageOption.equals("4")) {
//                userAddContact(username);
//            }
//            else if (messageOption.equals("5")) {
//                userDeleteContact(username);
//            }
//            else if (messageOption.equals("6")) {
//                speakerMessageAttendees(username);
//            }
//            else if (messageOption.equals("-1")) {
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Invalid choice.");
//            }
//        }
//    }
//
//    public void organizerMessageSystem(String username) {
//        while (true) {
//            programPresenter.spaceBreak();
//            programPresenter.title("MESSAGING");
//            programPresenter.messageOptions("Organizer");
//            String messageOption = scan.nextLine();
//            if (messageOption.equals("1")) {
//                userViewMessage(username);
//            }
//            else if (messageOption.equals("2")) {
//                userSendMessage(username, "Organizer");
//            }
//            else if (messageOption.equals("3")) {
//                programPresenter.spaceBreak();
//                programPresenter.title("YOUR CONTACTS");
//                programPresenter.showContacts(messageManager.getContacts(username), username);
//            }
//            else if (messageOption.equals("4")) {
//                userAddContact(username);
//            }
//            else if (messageOption.equals("5")) {
//                userDeleteContact(username);
//            }
//            else if (messageOption.equals("6")) {
//                organizerMultipleMessage(username, "Speaker");
//            }
//            else if (messageOption.equals("7")) {
//                organizerMultipleMessage(username, "Attendee");
//            }
//            else if (messageOption.equals("-1")) {
//                break;
//            }
//            else {
//                programPresenter.errorMessage("Invalid choice.");
//            }
//        }
//    }
//
//}