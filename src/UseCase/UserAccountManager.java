package UseCase;

import java.util.List;

/**
 * Interface for use cases dealing with user types.
 */
public interface UserAccountManager {

    /**
     * Creates a user with username and password.
     * @param username username of user
     * @param password password of user
     */
    public void createUser(String username, String password);

    /**
     * Check if user exists in corresponding user list.
     * @param username username of user
     * @return true if user exists
     */
    public boolean checkUserExistence(String username);

    /**
     * Get usernames of all users.
     * @return list of username
     */
    public List<String> getUsers();

}
