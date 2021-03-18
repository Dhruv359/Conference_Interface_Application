package Entity;

import java.io.Serializable;

/**
 * Entity class for Rooms in the conference.
 */
public class Room implements Serializable {

    private String name;
    private int capacity;
    private static final long serialVersionUID = 11L;

    /**
     * Main constructor for Room object.
     * @param name name of the room
     * @param capacity maximum capacity for the room
     */
    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Getter for the name of the room.
     * @return name of room
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for capacity of the room.
     * @return capacity of room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the String form of Room object.
     * @return String for Room object
     */
    public String toString() {
        return "Room: [" + name + "] " + "Capacity: <" + capacity + ">";
    }

}
