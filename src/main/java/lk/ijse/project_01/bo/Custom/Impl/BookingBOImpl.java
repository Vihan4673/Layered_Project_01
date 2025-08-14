package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.DTO.BookingDTO;
import lk.ijse.project_01.bo.Custom.BookingBO;
import lk.ijse.project_01.dao.Custom.BookingDAO;
import lk.ijse.project_01.dao.DAOFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookingBOImpl implements BookingBO {

    private final BookingDAO bookingDAO = (BookingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOKING);


    @Override
    public boolean bookRoom(BookingDTO dto) throws SQLException {
        return bookingDAO.bookRoom(dto);
    }

    @Override
    public boolean isRoomAvailable(String roomId, LocalDate checkIn, int duration) throws SQLException {
        return bookingDAO.isRoomAvailable(roomId, checkIn, duration);
    }

    @Override
    public boolean cancelBooking(String bookingId) throws SQLException {
        return bookingDAO.cancelBooking(bookingId);
    }

    @Override
    public List<BookingDTO> getAllBookings() throws SQLException {
        return bookingDAO.getAllBookings();
    }

    @Override
    public List<BookingDTO> searchBookings(String keyword) throws SQLException {
        return bookingDAO.searchBookings(keyword);
    }

    @Override
    public String getLastBookingId() throws SQLException {
        return bookingDAO.getLastBookingId();
    }

    @Override
    public List<String> getAvailableRooms(String roomType, LocalDate checkIn, int duration) throws SQLException {
        return bookingDAO.getAvailableRooms(roomType, checkIn, duration);
    }

    @Override
    public String getGuestNameByPhone(String phoneNo) throws SQLException {
        return bookingDAO.getGuestNameByPhone(phoneNo);
    }
}
