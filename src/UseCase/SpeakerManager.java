package UseCase;

import Entity.Speaker;

import java.io.Serializable;
import java.util.*;

/**
 * Use case class for all speaker related responsibilities.
 */
public class SpeakerManager implements UserAccountManager, Serializable {

    private List<Speaker> speakerList = new ArrayList<>();
    private HashMap<String, List<String>> speakerMap = new HashMap<>(); // Mapping of speaker usernames to eventIDs
    private static final long serialVersionUID = 8L;

    /**
     * Creates a speaker.
     * @param username username input
     * @param password password input
     */
    public void createUser(String username, String password) {
        Speaker s = new Speaker(username, password);
        speakerList.add(s);
    }

    /**
     * Checks if the speaker exists.
     * @param username username of the organizer
     * @return true of contacts of the user
     */
    public boolean checkUserExistence(String username) {
        for (Speaker s : speakerList) {
            if (s.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the usernames of all speakers.
     * @return list of usernames of all speakers
     */
    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        for (Speaker s : speakerList) {
            users.add(s.getUsername());
        }
        return users;
    }

    /**
     * Returns the list of events for a particular speaker
     * @param username username of the speaker
     * @return list of events for a particular speaker
     */
    public List<String> getSpeakerEvents(String username) {
        if (speakerMap.containsKey(username)) {
            return speakerMap.get(username);
        }
        return Collections.emptyList(); // Empty list
    }

    /**
     * Assigns a speaker to a particular event
     * @param username username of the speaker
     * @param eventID event id of the event
     * @return true if speaker is assigned to the event successfully
     */
    public boolean assignSpeaker(String username, String eventID) {
        // Check for existing mapping to see if eventID is already there, if not just add eventID
        if (speakerMap.containsKey(username)) {
            if (speakerMap.get(username).contains(eventID)) {
                return false;
            }
            else {
                speakerMap.get(username).add(eventID);
                return true;
            }
        }
        // If there is no speaker mapping, create a new mapping
        List<String> events = new ArrayList<>();
        events.add(eventID);
        speakerMap.put(username, events);
        return true;
    }

    /**
     * Returns the total number of speakers in the system.
     * @return total number of speakers
     */
    public int getNumSpeakers() {
        return speakerList.size();
    }

    public List<String> getSpeakerStats() {
        List<Integer> lengths = new ArrayList<>();
        List<String> speakers = new ArrayList<>();
        for (Speaker s : speakerList) {
            lengths.add(getSpeakerEvents(s.getUsername()).size());
            speakers.add(s.getUsername() + "\t\t\tNumber of Events Spoken At: " + getSpeakerEvents(s.getUsername()).size());
        }
        for (int i = 0; i < lengths.size() - 1; i++)
            for (int j = 0; j < lengths.size() - i - 1; j++)
                if (lengths.get(j) > lengths.get(j+1)) {
                    int temp1 = lengths.get(j);
                    lengths.set(j, lengths.get(j + 1));
                    lengths.set(j + 1, temp1);
                    String temp2 = speakers.get(j);
                    speakers.set(j, speakers.get(j + 1));
                    speakers.set(j + 1, temp2);
                }
        return speakers;
    }

}
