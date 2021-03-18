package PresenterLayer;


import UI.FormFactory.FormFactory;
import UI.FormFactory.FormFactoryInterface;
import UI.MainMenu.MenuOptionInterface;
import UI.SignUp.Form;
import UI.SignUp.SignUpForm;
import UI.UserMessagingForms.*;
import UI.UserSchedulingForms.AttendeeFormInterface;
import UI.UserSchedulingForms.OrganizerSchedulingForm;
import UI.UserSchedulingForms.SchedulingFormInterface;
import UI.UserSchedulingForms.SpeakerSchedulingForm;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class UIPresenter implements GUIPresenterInterface {

    private GUIPresenterInterface ginterface;
    private GUIController guiController;
    private Form form;
    private MenuOptionInterface menuOptionInterface;
    private String username, usertype;
    FormFactory formFactory = new FormFactory(username, usertype, this);
    FormFactoryInterface formFactoryInterface;

    // LoginSystem ls = new LoginSystem();
    //HashMap<String, String> userList = ls.getUserList();

    public UIPresenter(GUIController guiController) {
        this.guiController = guiController;
    }

    public UIPresenter(Form form) {
        this.form = form;
    }

    public UIPresenter() {
        //REMOVE, JUST FOR RUNNING FORMS
    }

    //opens the sign up form from form factory.
    @Override
    public void openSignUpForm(GUIPresenterInterface guiPresenterInterface) {
        FormFactory formFactory1 = new FormFactory(guiPresenterInterface);
        FormFactoryInterface formFactoryInterface = formFactory1.getForm("SignUp", "");
        formFactoryInterface.display(guiPresenterInterface);

    }

    @Override
    public void openMenuForm(GUIPresenterInterface guiPresenterInterface) {
        FormFactory formFactory1 = new FormFactory(guiPresenterInterface);
        FormFactoryInterface formFactoryInterface = formFactory1.getForm("Menu", "");
        formFactoryInterface.display(username, usertype, guiPresenterInterface);

    }

    public void logOut(GUIPresenterInterface guiPresenterInterface) {
        guiController.logOut();
    }


    public void signUp(GUIPresenterInterface guiPresenterInterface, Form form) {
        this.form = form;
        this.username = form.getTxtUniqueName();
        String password = form.getPassword();
        this.usertype = form.getUsertype();
        boolean successful = guiController.loginSystem(username, password, usertype, "SignUp");
        if (!successful) {
            form.showErrorMessage("Incorrect Username/Password. Please Try Again!");
        } else {
            //will run the form for Menu Options.
            FormFactoryInterface formFactoryInterface = formFactory.getForm("Menu", "");
            formFactoryInterface.display(username, usertype, guiPresenterInterface);
        }
    }

    public void login(GUIPresenterInterface guiPresenterInterface, Form form) {
        this.form = form;
        this.username = form.getTxtUniqueName();
        String password = form.getPassword();
        this.usertype = form.getUsertype();
        boolean successful = guiController.loginSystem(username, password, usertype, "logIn");
        if (!successful) {
            form.showErrorMessage("Incorrect Username/Password. Please Try Again!");
        } else {
            FormFactoryInterface formFactoryInterface = formFactory.getForm("Menu", "");
            formFactoryInterface.display(username, usertype, guiPresenterInterface);
        }
    }

    public void openAccessMessagingSystem(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {
        FormFactoryInterface formFactoryInterface = formFactory.getForm("Messaging Form", usertype);
        formFactoryInterface.display(username, usertype, guiPresenterInterface);

    }

    public void openAccessSchedulingSystem(String username, String usertype, GUIPresenterInterface guiPresenterInterface) {
        FormFactoryInterface formFactoryInterface = formFactory.getForm("Scheduling Form", usertype);
        formFactoryInterface.display(username, usertype, guiPresenterInterface);
    }

    public void organizerSchedulingSystem(String option, SchedulingFormInterface schedulingFormInterface, String userinput) {
        System.out.println(option);
        String returnvalue = guiController.schedulingSystem(option, userinput);
        if (option.equalsIgnoreCase("getSchedule")) {
            schedulingFormInterface.openEventsForm(returnvalue);
        } else if (option.equalsIgnoreCase("AddRoom")) {
            if (returnvalue.equals("1")) {
                schedulingFormInterface.showMessage("Successfully added room");
            }
            if (returnvalue.equals("0")) {
                schedulingFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("SeeEvents")) {
            schedulingFormInterface.openEventsForm(returnvalue);
        } else if (option.equalsIgnoreCase("ScheduleEvent")) {
            if (returnvalue.equals("1")) {
                schedulingFormInterface.showMessage("Successfully added event");
            }
            if (returnvalue.equals("0")) {
                schedulingFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("CancelEvent")) {
            if (returnvalue.equals("1")) {
                schedulingFormInterface.showMessage("Successfully removed event");
            }
            if (returnvalue.equals("0")) {
                schedulingFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("AddSpeaker")) {
            if (returnvalue.equals("1")) {
                schedulingFormInterface.showMessage("Successfully added Speaker");
            }
            if (returnvalue.equals("0")) {
                schedulingFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("AssignSpeaker")) {
            if (returnvalue.equals("1")) {
                schedulingFormInterface.showMessage("Successfully Assigned Speaker to Event");
            }
            if (returnvalue.equals("0")) {
                schedulingFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("NewAttendee")) {
            if (returnvalue.equals("1")) {
                schedulingFormInterface.showMessage("Successfully added Attendee");
            }
            if (returnvalue.equals("0")) {
                schedulingFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("GeneratePDF")) {
            if (returnvalue.equals("1")) {
                schedulingFormInterface.showMessage("Created PDF");
            }
        } else if (option.equalsIgnoreCase("getStats")) {
            if (returnvalue.equalsIgnoreCase("0")) {
                schedulingFormInterface.showMessage("Error");
            } else {
                schedulingFormInterface.openEventsForm(returnvalue);
            }
        }
    }

    @Override
    public void attendeeSchedulingSystem(String option, AttendeeFormInterface attendeeFormInterface, String userinput) {
        System.out.println(option);
        String returnvalue = guiController.schedulingSystem(option, userinput);
        if (option.equalsIgnoreCase("getSchedule")) {
            attendeeFormInterface.openEventsForm(returnvalue);
        } else if (option.equalsIgnoreCase("showAttendeeSchedule")) {
            attendeeFormInterface.openEventsForm(returnvalue);
        } else if (option.equalsIgnoreCase("Add Event")) {
            if (returnvalue.equals("1")) {
                attendeeFormInterface.showMessage("Successfully added event");
            }
            if (returnvalue.equals("0")) {
                attendeeFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("RemoveEvent")) {
            if (returnvalue.equals("1")) {
                attendeeFormInterface.showMessage("Successfully removed event");
            }
            if (returnvalue.equals("0")) {
                attendeeFormInterface.showMessage("Unsuccessful");
            }
        } else if (option.equalsIgnoreCase("CheckVIP")) {
            if (returnvalue.equals("1")) {
                attendeeFormInterface.showMessage("You are a VIP");
            }
            if (returnvalue.equals("0")) {
                attendeeFormInterface.showMessage("Not a VIP");
            }
        }
    }

    public void speakerSchedulingSystem(String option, SpeakerSchedulingForm speakerSchedulingForm) {
        System.out.println(option);
        String returnvalue = guiController.schedulingSystem(option, "");
        if (option.equalsIgnoreCase("getSchedule")) {
            speakerSchedulingForm.openEventsForm(returnvalue);
        } else if (option.equalsIgnoreCase("showSpeakerSchedule")) {
            speakerSchedulingForm.openEventsForm(returnvalue);
        }
    }

    //TODO: implement this
    public void organizerMessagingSystem(String option, OrganizerMessagingFormInterface organizerMessagingFormInterface, String userInput) {
        System.out.println(option);
        String returnvalue = guiController.messagingSystem(option, userInput);
        if (option.equalsIgnoreCase("ViewContactsMessaged")) {
            organizerMessagingFormInterface.setTxtrShowUsernames(returnvalue);
        } else if (option.equalsIgnoreCase("ShowContacts")) {
            organizerMessagingFormInterface.setTxtrShowContactsHere(returnvalue);
        } else if (option.equalsIgnoreCase("ShowMessages")) {

            organizerMessagingFormInterface.setTxtrShowConversationWith(returnvalue);
        } else if (option.equalsIgnoreCase("SendMessage")) {
            organizerMessagingFormInterface.showMessage(returnvalue);
        } else if (option.equalsIgnoreCase("AddContact")) {
            if (returnvalue.equals("3")) {
                organizerMessagingFormInterface.showMessage("Could not add that contact");
            } else if (returnvalue.equals("2")) {
                organizerMessagingFormInterface.showMessage("Already have that contact");
            } else if (returnvalue.equals("1")) {
                organizerMessagingFormInterface.showMessage("Contact successfully added");
            }
        } else if (option.equalsIgnoreCase("DeleteContact")) {

            organizerMessagingFormInterface.showMessage(returnvalue);
        } else if (option.equalsIgnoreCase("MessageMultiple")) {
            organizerMessagingFormInterface.showMessage(returnvalue);
        }
    }

    public void speakerMessagingSystem(String option, SpeakerMessagingFormInterface speakerMessagingFormInterface, String userInput) {
        System.out.println(option);
        String returnvalue = guiController.messagingSystem(option, userInput);
        if (option.equalsIgnoreCase("ViewContactsMessaged")) {
            speakerMessagingFormInterface.setTxtrShowUsernames(returnvalue);
        } else if (option.equalsIgnoreCase("ShowContacts")) {
            speakerMessagingFormInterface.setTxtrShowContactsHere(returnvalue);
        } else if (option.equalsIgnoreCase("ShowMessages")) {

            speakerMessagingFormInterface.setTxtrShowConversationWith(returnvalue);
        } else if (option.equalsIgnoreCase("MessageAllAttendees")) {
            speakerMessagingFormInterface.showMessage(returnvalue);
        }
    }

    @Override
    public void attendeeMessagingSystem(String option, AttendeeMessagingFormInterface attendeeMessagingFormInterface, String userInput) {
        System.out.println(option);
        String returnvalue = guiController.messagingSystem(option, userInput);
        if (option.equalsIgnoreCase("ViewContactsMessaged")) {
            attendeeMessagingFormInterface.setTxtrShowUsernames(returnvalue);
        } else if (option.equalsIgnoreCase("ShowContacts")) {
            attendeeMessagingFormInterface.setTxtrShowContactsHere(returnvalue);
        } else if (option.equalsIgnoreCase("ShowMessages")) {

            attendeeMessagingFormInterface.setTxtrShowConversationWith(returnvalue);
        } else if (option.equalsIgnoreCase("SendMessage")) {
            attendeeMessagingFormInterface.showMessage(returnvalue);
        } else if (option.equalsIgnoreCase("AddContact")) {
            if (returnvalue.equals("3")) {
                attendeeMessagingFormInterface.showMessage("Could not add that contact");
            } else if (returnvalue.equals("2")) {
                attendeeMessagingFormInterface.showMessage("Already have that contact");
            } else if (returnvalue.equals("1")) {
                attendeeMessagingFormInterface.showMessage("Contact successfully added");
            }
        } else if (option.equalsIgnoreCase("DeleteContact")) {

            attendeeMessagingFormInterface.showMessage(returnvalue);
        }
    }


}
