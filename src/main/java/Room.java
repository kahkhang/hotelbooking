public class Room {
    private RoomStatus status;
    private String name;

    public Room(String name, RoomStatus status) {
        this.name = name;
        this.status = status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomStatus getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }
}
