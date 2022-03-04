import java.util.*;

public class Hotel {
    private List<RoomStatus> roomStatuses = new ArrayList<>();
    private List<String> roomNames = new ArrayList<>();
    private Map<String, Integer> roomNameToIndexMap = new HashMap<>();
    PriorityQueue<Integer> availableRooms = new PriorityQueue<Integer>();

    public Hotel() {
        char[] alphabet = "ABCDE".toCharArray();
        for(int i = 0; i < 5; i++) {
            roomNames.add("1" + alphabet[i]);
        }
        for(int i = 4; i >= 0; i--) {
            roomNames.add("2" + alphabet[i]);
        }
        for(int i = 0; i < 5; i++) {
            roomNames.add("3" + alphabet[i]);
        }
        for(int i = 4; i >= 0; i--) {
            roomNames.add("4" + alphabet[i]);
        }
        for(int i = 0; i < roomNames.size(); i++) {
            roomStatuses.add(RoomStatus.Available);
            roomNameToIndexMap.put(roomNames.get(i), i);
            availableRooms.add(i);
        }
    }

    public Integer getRoomIndexFromName(String name) throws InvalidRoomException {
        if (!roomNameToIndexMap.containsKey(name)) {
            throw new InvalidRoomException("Invalid room " + name);
        }
        return roomNameToIndexMap.get(name);
    }
}
