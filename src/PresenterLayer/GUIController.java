package PresenterLayer;

import Controller.*;
import Gateway.ConferenceDataReceiver;
import Gateway.ConferenceDataSaver;
import UseCase.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class GUIController {

    private EventManager eventManager;
    private MessageManager messageManager;
    private AttendeeManager attendeeManager;
    private SpeakerManager speakerManager;
    private OrganizerManager organizerManager;
    private HashMap<String,String> userList;
    private ConferenceDataReceiver conferenceDataReceiver;
    private ConferenceDataSaver conferenceDataSaver;
    private GUIPresenterInterface guiPresenterInterface;
    private ConferenceSystemGUI conferenceSystemGUI;

    public void run(){
        this.conferenceDataReceiver = new ConferenceDataReceiver();
        this.conferenceDataSaver = new ConferenceDataSaver();

        // Set all data from use cases (use cases are the main storage system for data)
        this.eventManager = conferenceDataReceiver.readEventManager();
        this.messageManager = conferenceDataReceiver.readMessageManager();
        this.attendeeManager = conferenceDataReceiver.readAttendeeManager();
        this.speakerManager = conferenceDataReceiver.readSpeakerManager();
        this.organizerManager = conferenceDataReceiver.readOrganizerManager();
        this.userList = conferenceDataReceiver.readUserList();

        // Check if events are already read in, read in events and create event objects in eventManager if not
        if (!eventManager.hasEvents()) {
            List<String> eventList = null;
            try {
                eventList = conferenceDataReceiver.readEventList();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (String e : eventList) {
                eventManager.createEvent(e);
            }
        }
        guiPresenterInterface = new UIPresenter(this);
        guiPresenterInterface.openSignUpForm(guiPresenterInterface);
    }

    //this is the first function called whenever, someone logs in and creates and conference system GUI object.
    public boolean loginSystem(String name, String password, String usertype, String choice){
        this.conferenceSystemGUI= new ConferenceSystemGUI(eventManager,messageManager,attendeeManager,speakerManager,organizerManager,userList,conferenceDataSaver);
        boolean successful;
        successful = conferenceSystemGUI.runLoginSystem(name,password,usertype,choice);
        return successful;
    }

    public String schedulingSystem(String option, String userinput)
    {
        String data = "da";
        data=conferenceSystemGUI.runSchedulingSystem(option,userinput);
        System.out.println(data);
        return data;
    }
    public String messagingSystem(String option,String userinput)
    {
        String data = "sdf";
        data=conferenceSystemGUI.runMessaaginSystem(option, userinput);
        System.out.println(data);
        return data;
    }




    public void logOut()
    {
        conferenceSystemGUI.logOut();
    }



    private void saveData(EventManager em, MessageManager mm, AttendeeManager am, SpeakerManager sm, OrganizerManager om, HashMap<String,String> userList) {
        ConferenceDataSaver conferenceDataSaver = new ConferenceDataSaver();
        conferenceDataSaver.saveEventManager(em);
        conferenceDataSaver.saveMessageManager(mm);
        conferenceDataSaver.saveAttendeeManager(am);
        conferenceDataSaver.saveSpeakerManager(sm);
        conferenceDataSaver.saveOrganizerManager(om);
        conferenceDataSaver.saveUserList(userList);
    }

}
