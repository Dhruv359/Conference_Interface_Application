package UI.UserSchedulingForms;

/**
 * Interface for main scheduling form.
 */
public interface SchedulingFormInterface {

    /**
     * Shows the scheduled events in GUI.
     * @param data list of events to be shown
     */
    public void openEventsForm(String data);

    /**
     * Executes a popup for message.
     * @param message message to be popped up to user
     */
    public void showMessage(String message);

}
