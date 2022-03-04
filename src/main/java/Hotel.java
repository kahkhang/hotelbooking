import java.util.*;

public class Hotel {
    private final List<Room> rooms = new ArrayList<>();
    private final Map<String, Integer> roomNameToIndexMap = new HashMap<>();
    private final PriorityQueue<Integer> availableRoomIndexes = new PriorityQueue<Integer>();

    public Hotel() {
        List<String> roomNames = new ArrayList<>();
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
            rooms.add(new Room(roomNames.get(i), RoomStatus.Available));
            roomNameToIndexMap.put(roomNames.get(i), i);
            availableRoomIndexes.add(i);
        }
    }

    public Integer getRoomIndexFromName(String name) throws InvalidRoomException {
        if (!roomNameToIndexMap.containsKey(name)) {
            throw new InvalidRoomException("Invalid room " + name);
        }
        return roomNameToIndexMap.get(name);
    }

    public RoomStatus getStatus(String name) throws InvalidRoomException {
        return this.rooms.get(this.getRoomIndexFromName(name)).getStatus();
    }

    public String requestForRoomAssignment() throws NoRoomException {
        if (availableRoomIndexes.isEmpty()) {
            throw new NoRoomException("No available rooms!");
        }
        Integer roomIndex = availableRoomIndexes.poll();
        rooms.get(roomIndex).setStatus(RoomStatus.Occupied);
        return this.rooms.get(roomIndex).getName();
    }

    public void checkOutOfRoom(String name) throws InvalidRoomException, WrongStatusException {
        Integer roomIndex = this.getRoomIndexFromName(name);
        Room room = this.rooms.get(roomIndex);
        if (room.getStatus() != RoomStatus.Occupied) {
            throw new WrongStatusException("Room must be occupied!");
        }
        room.setStatus(RoomStatus.Vacant);
    }

    public void markRoomCleaned(String name) throws InvalidRoomException, WrongStatusException {
        Integer roomIndex = this.getRoomIndexFromName(name);
        Room room = this.rooms.get(roomIndex);
        if (room.getStatus() != RoomStatus.Vacant) {
            throw new WrongStatusException("Room must be vacant!");
        }
        room.setStatus(RoomStatus.Available);
        this.availableRoomIndexes.add(roomIndex);
    }

    public void markRoomRepair(String name) throws InvalidRoomException, WrongStatusException {
        Integer roomIndex = this.getRoomIndexFromName(name);
        Room room = this.rooms.get(roomIndex);
        if (room.getStatus() != RoomStatus.Vacant) {
            throw new WrongStatusException("Room must be vacant!");
        }
        room.setStatus(RoomStatus.Repair);
    }

    public void repairRoom(String name) throws InvalidRoomException, WrongStatusException {
        Integer roomIndex = this.getRoomIndexFromName(name);
        Room room = this.rooms.get(roomIndex);
        if (room.getStatus() != RoomStatus.Repair) {
            throw new WrongStatusException("Room must be marked as repair!");
        }
        room.setStatus(RoomStatus.Vacant);
    }
}
