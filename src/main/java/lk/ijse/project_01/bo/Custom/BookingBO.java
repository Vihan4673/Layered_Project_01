package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.DTO.BookingDTO;
import lk.ijse.project_01.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface BookingBO extends SuperBO {
    boolean bookRoom(BookingDTO dto) throws SQLException;
    boolean isRoomAvailable(String roomId, LocalDate checkIn, int duration) throws SQLException;
    boolean cancelBooking(String bookingId) throws SQLException;
    List<BookingDTO> getAllBookings() throws SQLException;
    List<BookingDTO> searchBookings(String keyword) throws SQLException;
    String getLastBookingId() throws SQLException;
    List<String> getAvailableRooms(String roomType, LocalDate checkIn, int duration) throws SQLException;
    String getGuestNameByPhone(String phoneNo) throws SQLException;
}
