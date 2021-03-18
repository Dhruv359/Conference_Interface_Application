package UI.MainMenu;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;

/**
 * Interface for MenuOptionForm
 */
public interface MenuOptionInterface {

        /**
         * Opens the messaging form associated with each usertype
         * @param username username of the user
         * @param usertype user type of the user
         * @param guiPresenterInterface presenter interface of the GUI
         */
        public void openAccessMessagingSystem(String username, String usertype, GUIPresenterInterface presenterInterface);

        /**
         * Opens the scheduling form associated with each usertype
         * @param username username of the user
         * @param usertype user type of the user
         * @param guiPresenterInterface presenter interface of the GUI
         */
        public void openAccessSchedulingSystem(String username, String usertype, GUIPresenterInterface guiPresenterInterface);

}
