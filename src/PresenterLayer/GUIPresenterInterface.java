package PresenterLayer;

import UI.SignUp.Form;
import UI.SignUp.SignUpForm;
import UI.UserMessagingForms.*;
import UI.UserSchedulingForms.AttendeeFormInterface;
import UI.UserSchedulingForms.SchedulingFormInterface;
import UI.UserSchedulingForms.SpeakerFormInterface;
import UI.UserSchedulingForms.SpeakerSchedulingForm;

public interface GUIPresenterInterface {

    public void openSignUpForm(GUIPresenterInterface guiPresenterInterface);
    public void openAccessMessagingSystem(String username, String usertype, GUIPresenterInterface guiPresenterInterface);
    public void openAccessSchedulingSystem(String username, String usertype, GUIPresenterInterface guiPresenterInterface);
    public void signUp(GUIPresenterInterface guiPresenterInterface, Form signUpForm);
    public void login(GUIPresenterInterface guiPresenterInterface, Form signUpForm);
    public void organizerSchedulingSystem(String option, SchedulingFormInterface schedulingFormInterface,String userinput);
    public void openMenuForm(GUIPresenterInterface guiPresenterInterface);
    public void logOut(GUIPresenterInterface guiPresenterInterface);
    public void attendeeSchedulingSystem(String option, AttendeeFormInterface attendeeFormInterface, String userinput);
    public void speakerSchedulingSystem(String option, SpeakerSchedulingForm speakerSchedulingForm);
    public void organizerMessagingSystem(String option, OrganizerMessagingFormInterface organizerMessagingFormInterface, String userInput);
    public void speakerMessagingSystem(String option, SpeakerMessagingFormInterface speakerMessagingFormInterface, String userInput);
    public void attendeeMessagingSystem(String option, AttendeeMessagingFormInterface attendeeMessagingFormInterface, String userInput);



}
