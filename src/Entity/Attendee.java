package Entity;

/**
 * Attendee entity, subclass of User class.
 */
public class Attendee extends User {

    private boolean vipStatus;
    /**
     * Super constructor inherited from User.
     * @param username username of the attendee
     * @param password password of the attendee
     */
    public Attendee(String username, String password) {
        super(username, password);
    }

    /**
     * Overrides method in superclass, returns if the user is an attendee, always true.
     * @return true
     */
    public boolean isAttendee() {
        return true;
    }

    /**
     * Sets the VIP status of the Attendee.
     * @param vipStatus true if Attendee is a VIP
     */
    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    /**
     * Get the VIP status of the Attendee.
     * @return true if Attendee is a VIP
     */
    public boolean getVipStatus() {
        return vipStatus;
    }

}
