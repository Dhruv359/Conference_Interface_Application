package UI.FormFactory;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;

public interface FormFactoryInterface {
    public void display(String username, String usertype, GUIPresenterInterface guiPresenterInterface);
    public void display(GUIPresenterInterface guiPresenterInterface);
}

/*
I chose the simple factory design pattern to implement displaying GUI forms on the screen. It was intuitive to use this design pattern  as it helped to hide and encapsulate object creation process of the GUI forms (since creating an object is necessary to display the form) and open appropriate forms depending on user clicks. Using this design technique removed dependencies between the controller layer and the UI layer since I was now calling/creating objects of forms using an interface objects. 

The classes and responsibilities were 

FormFactory.java - contained the getForm(String formName, String user type) method which returned (and thus displayed) an object of the specified GUI Form according to the usertype of the user who called it.

FormFactoryInterface - contained the display(String formName, String usertype, UIPresenter presenter) method that was implemented in all the GUI forms to open them and thus this interface was implemented by all GUI forms. 

Using this, I was now able to use a FormFactoryInterface variable to get the desired form in my Controller class without being dependant on the UI layer. 
 */