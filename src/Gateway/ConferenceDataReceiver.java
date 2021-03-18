package Gateway;

import UseCase.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConferenceDataReceiver {

    public AttendeeManager readAttendeeManager() {
        AttendeeManager attendeeManager = new AttendeeManager();
        try {
            FileInputStream fileIn = new FileInputStream("phase2/src/data/AttendeeData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            attendeeManager = (AttendeeManager) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            return new AttendeeManager();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("AttendeeManager class not found");
            c.printStackTrace();
        }
        return attendeeManager;
    }

    public SpeakerManager readSpeakerManager() {
        SpeakerManager speakerManager = new SpeakerManager();
        try {
            FileInputStream fileIn = new FileInputStream("phase2/src/data/SpeakerData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            speakerManager = (SpeakerManager) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            return new SpeakerManager();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("SpeakerManager class not found");
            c.printStackTrace();
        }
        return speakerManager;
    }

    public OrganizerManager readOrganizerManager() {
        OrganizerManager organizerManager = new OrganizerManager();
        try {
            FileInputStream fileIn = new FileInputStream("phase2/src/data/OrganizerData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            organizerManager = (OrganizerManager) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            return new OrganizerManager();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("OrganizerManager class not found");
            c.printStackTrace();
        }
        return organizerManager;
    }

    public EventManager readEventManager() {
        EventManager eventManager = new EventManager();
        try {
            FileInputStream fileIn = new FileInputStream("phase2/src/data/EventData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            eventManager = (EventManager) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            return new EventManager();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("EventManager class not found");
            c.printStackTrace();
        }
        return eventManager;
    }

    public MessageManager readMessageManager() {
        MessageManager messageManager = new MessageManager();
        try {
            FileInputStream fileIn = new FileInputStream("phase2/src/data/MessageData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            messageManager = (MessageManager) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            return new MessageManager();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("MessageManager class not found");
            c.printStackTrace();
        }
        return messageManager;
    }

    public HashMap<String, String> readUserList() {
        HashMap<String, String> userList = new HashMap<>();
        try {
            FileInputStream fileIn = new FileInputStream("phase2/src/data/UserData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userList = (HashMap<String, String>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("HashMap class not found");
            c.printStackTrace();
        }
        return userList;
    }

    // Preliminary reading of events from a text file
    public List<String> readEventList() throws FileNotFoundException {
        Scanner s = new Scanner(new File("phase2/src/Data/EventList.txt"));
        ArrayList<String> eventList = new ArrayList<>();
        while (s.hasNextLine()){
            eventList.add(s.nextLine());
        }
        s.close();
        return eventList;
    }

    public ArrayList<ArrayList<String>> readUserRequest(){
        ArrayList<ArrayList<String>> userRequest = new ArrayList<ArrayList<String>>();
        try {
            FileInputStream fileIn = new FileInputStream("phase2/src/data/UserRequest.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userRequest = (ArrayList<ArrayList<String>>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            return new ArrayList<ArrayList<String>>();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("userRequest class not found");
            c.printStackTrace();
        }
        return userRequest;

    }
}
