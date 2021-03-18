package UI.SignUp;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;

/**
 * Interface for sign up form.
 */
public interface Form {

        /**
         * Shows the sign up form to the user.
         * @param guiPresenterInterface interface for UIPresenter
         */
        public void signUp(GUIPresenterInterface guiPresenterInterface);

        /**
         * Shows the login form to the user.
         * @param guiPresenterInterface interface for UIPresenter
         */
        public void login(GUIPresenterInterface guiPresenterInterface);

        /**
         * Pops up an error message to the user.
         * @param message error message to be shown
         */
        public void showErrorMessage(String message);

        /**
         * Gets the username of the user.
         * @return username of user
         */
        public String getTxtUniqueName();

        /**
         * Gets the password of the user.
         * @return password of user
         */
        public String getPassword();

        /**
         * Gets the user type of user.
         * @return type of user
         */
        public String getUsertype();

}
