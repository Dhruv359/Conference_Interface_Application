package UI.FormFactory;

import PresenterLayer.GUIPresenterInterface;
import PresenterLayer.UIPresenter;
import UI.MainMenu.MenuOptionForm;
import UI.SignUp.Form;
import UI.SignUp.SignUpForm;
import UI.UserMessagingForms.AttendeeMessagingForm;
import UI.UserMessagingForms.OrganizerMessagingForm;
import UI.UserMessagingForms.SpeakerMessagingForm;
import UI.UserSchedulingForms.AttendeeSchedulingForm;
import UI.UserSchedulingForms.OrganizerSchedulingForm;
import UI.UserSchedulingForms.SpeakerSchedulingForm;

public class FormFactory {

    private String name;
    private String usertype;
    private UIPresenter uiPresenter;
    private GUIPresenterInterface guiPresenterInterface;

    public FormFactory(String name, String usertype, GUIPresenterInterface guiPresenterInterface)
    {
        this.name=name;
        this.usertype=usertype;
        this.guiPresenterInterface=guiPresenterInterface;
    }
    public FormFactory(GUIPresenterInterface guiPresenterInterface)
    {
        this.guiPresenterInterface=guiPresenterInterface;
    }

    public FormFactoryInterface getForm(String formName, String usertype2)
    {
        //open Sign up Form
        if(formName.equalsIgnoreCase("SignUp"))
        {
            return new SignUpForm(guiPresenterInterface);
        }
        //open menu form
        if(formName.equalsIgnoreCase("Menu"))
        {
            return new MenuOptionForm(usertype,name,uiPresenter);
        }
        //open sschduling form
        if(formName.equalsIgnoreCase("Scheduling Form"))
        {
            if(usertype2.equalsIgnoreCase("Organizer"))
            {
                return new OrganizerSchedulingForm(name, uiPresenter);
            }
            if(usertype2.equalsIgnoreCase("Speaker"))
            {
                return new SpeakerSchedulingForm(name,uiPresenter);
            }
            if (usertype2.equalsIgnoreCase("Attendee"))
            {
                return new AttendeeSchedulingForm(name,uiPresenter);
            }
            if (usertype2.equalsIgnoreCase("VIP Attendee"))
            {
                return new AttendeeSchedulingForm(name,uiPresenter);
            }
        }
        //open messaging form
        if(formName.equalsIgnoreCase("Messaging Form"))
        {
            if(usertype2.equalsIgnoreCase("Organizer"))
            {
                return new OrganizerMessagingForm(name,uiPresenter);
            }
            if(usertype2.equalsIgnoreCase("Speaker"))
            {
                return new SpeakerMessagingForm(name,uiPresenter);
            }
            if (usertype2.equalsIgnoreCase("Attendee"))
            {
                return new AttendeeMessagingForm(name,uiPresenter);
            }
            if (usertype2.equalsIgnoreCase("VIP Attendee"))
            {
                return new AttendeeMessagingForm(name,uiPresenter);
            }
        }
        return null;
    }


}
