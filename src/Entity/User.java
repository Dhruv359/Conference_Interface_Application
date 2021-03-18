package Entity;

import java.io.Serializable;

/**
 * An abstract User class, parent to Organizer, Speaker, and Attendee.
 */
public abstract class User implements Serializable {

    public String username;
    public String password;
    private static final long serialVersionUID = 4L;

    /**
     * Main constructor for User class, creates a User with a username and password.
     * @param username username of the user
     * @param password password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** Returns the username of the user.
     * @return a string of the username
     */
    public String getUsername() {
        return username;
    }

    /** Returns the password of the user.
     * @return a string of the user's password.
     */
    public String getPassword() {
        return password;
    }

    /** Sets the username of the user.
     * @param username a string containing the user's name.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Sets the user's password.
     * @param password a string of the user's ID.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns whether user is an Attendee.
     * @return false by default
     */
    public boolean isAttendee() {
        return false;
    }

    /**
     * Returns whether user is an Organizer.
     * @return false by default
     */
    public boolean isOrganizer() {
        return false;
    }

    /**
     * Returns whether user is an Speaker.
     * @return false by default
     */
    public boolean isSpeaker() {
        return false;
    }

    /**
     * Returns whether two users have the same username, which implies equivalence.
     * @return if usernames are the same
     */
    public boolean equals(User other) {
        return this.username.equals(other.getUsername());
    }

}
