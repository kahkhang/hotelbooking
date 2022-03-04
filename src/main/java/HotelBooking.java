
public class HotelBooking {
    public static void main(String[] args) throws NoRoomException {
        Hotel hotel = new Hotel();
        System.out.println(hotel.requestForRoomAssignment());
    }
}
