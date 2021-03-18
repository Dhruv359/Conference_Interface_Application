package UseCase;

import Entity.Message;

import java.io.Serializable;
import java.util.*;

/**
 * Use case class for messaging related responsibilities.
 */
public class MessageManager implements Serializable {

    private HashMap<String, List<Message>> messageMap = new HashMap<>(); // Keys in "User1 User2" form
    private HashMap<String, List<String>> contactMap = new HashMap<>();
    private static final long serialVersionUID = 1L;

    // Returns the usernames of all the users user has messaged
    public List<String> getMessagedUsers(String username) {
        List<String> messagedUsers = new ArrayList<>();
        for (Map.Entry<String, List<Message>> entry : messageMap.entrySet()) {
            // Check if user and otherUser pairing exist (similar to sendMessage() function)
            String[] usernameArray = entry.getKey().split(" ");
            if (usernameArray[0].equals(username)) {
                messagedUsers.add(usernameArray[1]);
            }
            else if (usernameArray[1].equals(username)) {
                messagedUsers.add(usernameArray[0]);
            }
        }
        return messagedUsers;
    }

    /**
     * Returns the list of contacts for the user.
     * @param user username String of the user
     * @return list of contacts of the user
     */
    public List<String> getContacts(String user) {
        // Check if user has no contacts
        if (!contactMap.containsKey(user) || contactMap.get(user).isEmpty()) {
            return Collections.emptyList();
        }
        else {
            return contactMap.get(user);
        }
    }

    /**
     * Adds a contact to the list of contacts for the user.
     * @param user username String of the user
     * @param contact username String of the contact
     * @return true or false depending on whether adding contact was successful
     */
    public boolean addContact(String user, String contact) {
        // User has no contacts to begin with
        if (!contactMap.containsKey(user)) {
            List<String> contacts = new ArrayList<>();
            contacts.add(contact);
            contactMap.put(user, contacts);
        }
        else {
            // Check if user already has contact as a contact
            for (String c : contactMap.get(user)) {
                if (c.equals(contact)) {
                    return false;
                }
            }
            contactMap.get(user).add(contact);
        }
        return true;
    }

    /**
     * Removes a contact from the list of the contacts from the user.
     * @param user username String of the user
     * @param contact username String of the contact
     * @return true or false depending on whether removing the contact was successful
     */
    public boolean removeContact(String user, String contact) {
        // User does not have contacts to begin with
        if (!contactMap.containsKey(user)) {
            return false;
        }
        else {
            for (String c : contactMap.get(user)) {
                if (c.equals(contact)) {
                    contactMap.get(user).remove(contact);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a list of messages between two users.
     * @param user username String of the user
     * @param otherUser username String of the other user
     * @return a list of messages (the conversation) between user and otherUser
     */
    public List<String> getConversation (String user, String otherUser) {
        List<String> conversation = new ArrayList<>();
        for (Map.Entry<String, List<Message>> entry : messageMap.entrySet()) {
            // Check if user and otherUser pairing exist (similar to sendMessage() function)
            String[] usernameArray = entry.getKey().split(" ");
            List<String> usernameList = Arrays.asList(usernameArray);
            if (usernameList.contains(user) && usernameList.contains(otherUser)) {
                // Extract the string representations of the messages
                for (Message m : entry.getValue()) {
                    conversation.add(m.toString());
                }
            }
        }
        return conversation;
    }

    /**
     * Adds a message to the conversation pertaining to the sender and recipient. Assume that the sender and recipient
     * are both valid users in the system.
     * @param sender username String of sender
     * @param senderType type of sender ("Attendee", "Organizer" or "Speaker")
     * @param recipient username String of recipient
     * @param messageContent the content of the message
     */
    public void sendMessage(String sender, String senderType, String recipient, String messageContent) {
        // Instantiate message object
        Message message = new Message(sender, senderType, messageContent);
        // Check both combinations of sender and recipient
        String firstPair = sender + " " + recipient;
        String secondPair = recipient + " " + sender;
        // Check if conversation already exists between sender and recipient
        if (!messageMap.containsKey(firstPair) && !messageMap.containsKey(secondPair)) {
            List<Message> conversation = new ArrayList<>();
            conversation.add(message);
            messageMap.put(firstPair, conversation);
            return; // Exits the function so code below is not run
        }
        if (messageMap.containsKey(firstPair)) {
            messageMap.get(firstPair).add(message);
        }
        else if (messageMap.containsKey(secondPair)) {
            messageMap.get(secondPair).add(message);
        }
    }

}
