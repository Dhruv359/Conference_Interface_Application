package Entity;

/**
 * Organizer entity, subclass of User class.
 */
public class Organizer extends User {

    /**
     * Super constructor inherited from User.
     * @param username username of the organizer
     * @param password password of the organizer
     */
    public Organizer(String username, String password) {
        super(username, password);
    }

    /**
     * Overrides method in superclass, returns if the user is an organizer, always true.
     * @return true
     */
    public boolean isOrganizer() {
        return true;
    }

}
