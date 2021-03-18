package Entity;

/**
 * Speaker entity, subclass of User class.
 */
public class Speaker extends User {

    /**
     * Super constructor inherited from User.
     * @param username username of the speaker
     * @param password password of the speaker
     */
    public Speaker(String username, String password) {
        super(username, password);
    }

    /**
     * Overrides method in superclass, returns if the user is a speaker, always true.
     * @return true
     */
    public boolean isSpeaker() {
        return true;
    }

}
