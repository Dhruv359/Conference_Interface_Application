package UI.UserMessagingForms;

/**
 * Interface for AttendeeMessagingForm
 */
public interface AttendeeMessagingFormInterface  {

    /**
     * Shows a message in the GUI.
     * @param message message content
     */
    public void showMessage(String message);

    /**
     * Opens the Events form.
     * @param data list of events to be shown.
     */
    public void openEventsForm(String data);

    /**
     * Displays the conversation between two people in the GUI.
     * @param conversationWith conversation between two people
     */
    public void setTxtrShowConversationWith(String conversationWith);

    /**
     * Displays the contacts of the attendee in the GUI.
     * @param contacts contacts of the attendee
     */
    public void setTxtrShowContactsHere(String contacts);

    /**
     * Displays the usernames of the contacts in the GUI.
     * @param showUsernames usernames of the contacts
     */
    public void setTxtrShowUsernames(String showUsernames);
}
