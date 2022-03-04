import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    @Test
    void requestForRoomAssignment() throws NoRoomException, InvalidRoomException, WrongStatusException {
        Hotel hotel = new Hotel();
        assertEquals(hotel.requestForRoomAssignment(), "1A");
        assertEquals(hotel.requestForRoomAssignment(), "1B");
        assertEquals(hotel.requestForRoomAssignment(), "1C");
        hotel.checkOutOfRoom("1A");
        hotel.checkOutOfRoom("1B");
        hotel.checkOutOfRoom("1C");
        hotel.markRoomCleaned("1B");
        hotel.markRoomCleaned("1C");
        assertEquals(hotel.requestForRoomAssignment(), "1B");
    }

    @Test
    void checkOutOfRoom() throws NoRoomException, InvalidRoomException, WrongStatusException {
        Hotel hotel = new Hotel();
        assertEquals(hotel.requestForRoomAssignment(), "1A");
        hotel.checkOutOfRoom("1A");
        Assertions.assertThrows(WrongStatusException.class, () -> {
            hotel.checkOutOfRoom("1B");
        });
    }

    @Test
    void markRoomCleaned() throws NoRoomException, InvalidRoomException, WrongStatusException {
        Hotel hotel = new Hotel();
        assertEquals(hotel.requestForRoomAssignment(), "1A");
        hotel.checkOutOfRoom("1A");
        hotel.markRoomCleaned("1A");
        assertEquals(hotel.getStatus("1A"), RoomStatus.Available);
        Assertions.assertThrows(WrongStatusException.class, () -> {
            hotel.markRoomCleaned("1B");
        });
    }

    @Test
    void markRoomRepair() throws NoRoomException, InvalidRoomException, WrongStatusException {
        Hotel hotel = new Hotel();
        assertEquals(hotel.requestForRoomAssignment(), "1A");
        hotel.checkOutOfRoom("1A");
        hotel.markRoomRepair("1A");
        assertEquals(hotel.getStatus("1A"), RoomStatus.Repair);
        Assertions.assertThrows(WrongStatusException.class, () -> {
            hotel.markRoomRepair("1B");
        });
    }

    @Test
    void repairRoom() throws NoRoomException, InvalidRoomException, WrongStatusException {
        Hotel hotel = new Hotel();
        assertEquals(hotel.requestForRoomAssignment(), "1A");
        hotel.checkOutOfRoom("1A");
        hotel.markRoomRepair("1A");
        hotel.repairRoom("1A");
        assertEquals(hotel.getStatus("1A"), RoomStatus.Vacant);
        Assertions.assertThrows(WrongStatusException.class, () -> {
            hotel.repairRoom("1B");
        });
    }

}