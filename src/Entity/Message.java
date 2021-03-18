package Entity;

import java.io.Serializable;

/**
 * Message entity that describes messages users send to each other.
 */
public class Message implements Serializable {

    private String senderUsername;
    private String senderType;
    private String messageContent;
    private static final long serialVersionUID = 2L;

    /**
     * Constructor method for Message. Creates a message with the name of the sender and their type along with the
     * actual message content.
     * @param senderUsername username of the sender
     * @param senderType type of the sender
     * @param messageContent the message content itself
     */
    public Message(String senderUsername, String senderType, String messageContent) {
        this.senderUsername = senderUsername;
        this.senderType = senderType;
        this.messageContent = messageContent;
    }

    /**
     * Overrides method in Object class to generate a String representation of Message objects.
     * @return String representation of Message
     */
    public String toString() {
        return senderUsername + "[" + senderType + "] : " + messageContent;
    }

}
