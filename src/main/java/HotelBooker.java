import java.util.ArrayList;

public class HotelBooker {
    Hotel hotel = new Hotel();

    public void assignRoom() throws WrongStatusException{
        throw new WrongStatusException("Room is not available!");
    }
}