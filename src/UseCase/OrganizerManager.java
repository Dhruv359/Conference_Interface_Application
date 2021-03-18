package UseCase;

import Entity.Organizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Use case class for all organizer related responsibilities.
 */
public class OrganizerManager implements UserAccountManager, Serializable {

    private List<Organizer> organizerList = new ArrayList<>();
    private static final long serialVersionUID = 7L;

    /**
     * Creates an organizer.
     * @param username username input
     * @param password password input
     */
    public void createUser(String username, String password) {
        Organizer o = new Organizer(username, password);
        organizerList.add(o);
    }

    /**
     * Checks if the organizer exists.
     * @param username username of the organizer
     * @return true of contacts of the user
     */
    public boolean checkUserExistence(String username) {
        for (Organizer o : organizerList) {
            if (o.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the usernames of all organizers.
     * @return list of usernames of all organizers
     */
    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        for (Organizer o : organizerList) {
            users.add(o.getUsername());
        }
        return users;
    }

    public List<Organizer> getOrganizerList() {
        return organizerList;
    }

}
