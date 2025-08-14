package lk.ijse.project_01.dao.Custom.impl;

import lk.ijse.project_01.DB.DBConnection;
import lk.ijse.project_01.DTO.BookingDTO;
import lk.ijse.project_01.dao.Custom.BookingDAO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public boolean bookRoom(BookingDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement pstmBooking = null;
        PreparedStatement pstmRoom = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            String sqlBooking = "INSERT INTO booking(bookingId, guestPhone, roomType, roomId, checkIn, duration, totalPrice, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmBooking = con.prepareStatement(sqlBooking);
            pstmBooking.setString(1, dto.getBookingId());
            pstmBooking.setString(2, dto.getGuestPhone());
            pstmBooking.setString(3, dto.getRoomType());
            pstmBooking.setString(4, dto.getRoomId());
            pstmBooking.setDate(5, Date.valueOf(dto.getCheckIn()));
            pstmBooking.setInt(6, dto.getDuration());
            pstmBooking.setDouble(7, dto.getTotalPrice());
            pstmBooking.setString(8, dto.getStatus());

            int affectedRows = pstmBooking.executeUpdate();
            if (affectedRows == 0) {
                con.rollback();
                return false;
            }

            String sqlUpdateRoom = "UPDATE room SET availability = false WHERE roomId = ?";
            pstmRoom = con.prepareStatement(sqlUpdateRoom);
            pstmRoom.setString(1, dto.getRoomId());

            int affectedRoomRows = pstmRoom.executeUpdate();
            if (affectedRoomRows == 0) {
                con.rollback();
                return false;
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (pstmBooking != null) pstmBooking.close();
            if (pstmRoom != null) pstmRoom.close();
            if (con != null) con.setAutoCommit(true);
        }
    }


    @Override
    public boolean isRoomAvailable(String roomId, LocalDate checkIn, int duration) throws SQLException {
        String sql = "SELECT COUNT(*) FROM booking WHERE roomId = ? AND status = 'Booked' AND (" +
                "(? BETWEEN checkIn AND DATE_ADD(checkIn, INTERVAL duration DAY)) OR " +
                "(DATE_ADD(?, INTERVAL ? DAY) BETWEEN checkIn AND DATE_ADD(checkIn, INTERVAL duration DAY)) OR " +
                "(checkIn BETWEEN ? AND DATE_ADD(?, INTERVAL ? DAY))" +
                ")";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, roomId);
            pstm.setDate(2, java.sql.Date.valueOf(checkIn));
            pstm.setDate(3, java.sql.Date.valueOf(checkIn));
            pstm.setInt(4, duration);
            pstm.setDate(5, java.sql.Date.valueOf(checkIn));
            pstm.setDate(6, java.sql.Date.valueOf(checkIn));
            pstm.setInt(7, duration);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
                return false;
            }
        }
    }

    @Override
    public boolean cancelBooking(String bookingId) throws SQLException {
        String sqlGetRoomId = "SELECT roomId FROM booking WHERE bookingId = ?";
        String sqlDeleteBooking = "DELETE FROM booking WHERE bookingId = ?";
        String sqlUpdateRoom = "UPDATE room SET availability = true WHERE roomId = ?";

        try (Connection con = DBConnection.getInstance().getNewConnection()) {
            con.setAutoCommit(false);

            String roomId;

            try (PreparedStatement pstmGetRoomId = con.prepareStatement(sqlGetRoomId)) {
                pstmGetRoomId.setString(1, bookingId);
                try (ResultSet rs = pstmGetRoomId.executeQuery()) {
                    if (rs.next()) {
                        roomId = rs.getString("roomId");
                    } else {
                        con.rollback();
                        return false;
                    }
                }
            }

            try (PreparedStatement pstmDeleteBooking = con.prepareStatement(sqlDeleteBooking)) {
                pstmDeleteBooking.setString(1, bookingId);
                if (pstmDeleteBooking.executeUpdate() == 0) {
                    con.rollback();
                    return false;
                }
            }

            try (PreparedStatement pstmUpdateRoom = con.prepareStatement(sqlUpdateRoom)) {
                pstmUpdateRoom.setString(1, roomId);
                if (pstmUpdateRoom.executeUpdate() == 0) {
                    con.rollback();
                    return false;
                }
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<BookingDTO> getAllBookings() throws SQLException {
        List<BookingDTO> bookings = new ArrayList<>();

        String sql = "SELECT bookingId, guestPhone, roomType, roomId, checkIn, duration, totalPrice, status FROM booking";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                BookingDTO dto = new BookingDTO(
                        rs.getString("bookingId"),
                        rs.getString("guestPhone"),
                        rs.getString("roomType"),
                        rs.getString("roomId"),
                        rs.getDate("checkIn").toLocalDate(),
                        rs.getInt("duration"),
                        rs.getDouble("totalPrice"),
                        rs.getString("status")
                );
                bookings.add(dto);
            }
        }
        return bookings;
    }

    @Override
    public List<BookingDTO> searchBookings(String keyword) throws SQLException {
        List<BookingDTO> bookings = new ArrayList<>();
        String sql = "SELECT bookingId, guestPhone, roomType, roomId, checkIn, duration, totalPrice, status " +
                "FROM booking WHERE guestPhone LIKE ? OR bookingId LIKE ?";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            String likeKeyword = "%" + keyword + "%";
            pstm.setString(1, likeKeyword);
            pstm.setString(2, likeKeyword);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    BookingDTO dto = new BookingDTO(
                            rs.getString("bookingId"),
                            rs.getString("guestPhone"),
                            rs.getString("roomType"),
                            rs.getString("roomId"),
                            rs.getDate("checkIn").toLocalDate(),
                            rs.getInt("duration"),
                            rs.getDouble("totalPrice"),
                            rs.getString("status")
                    );
                    bookings.add(dto);
                }
            }
        }
        return bookings;
    }

    @Override
    public String getLastBookingId() throws SQLException {
        String sql = "SELECT bookingId FROM booking ORDER BY bookingId DESC LIMIT 1";
        try (Connection con = DBConnection.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getString("bookingId");
            } else {
                return null;
            }
        }
    }

    @Override
    public List<String> getAvailableRooms(String roomType, LocalDate checkIn, int duration) throws SQLException {
        List<String> availableRooms = new ArrayList<>();

        String sql = "SELECT roomId FROM room WHERE roomType = ? AND availability = true AND roomId NOT IN (" +
                "SELECT roomId FROM booking WHERE status = 'Booked' AND (" +
                "(? BETWEEN checkIn AND DATE_ADD(checkIn, INTERVAL duration DAY)) OR " +
                "(DATE_ADD(?, INTERVAL ? DAY) BETWEEN checkIn AND DATE_ADD(checkIn, INTERVAL duration DAY)) OR " +
                "(checkIn BETWEEN ? AND DATE_ADD(?, INTERVAL ? DAY))" +
                "))";

        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, roomType);
            pstm.setDate(2, Date.valueOf(checkIn));
            pstm.setDate(3, Date.valueOf(checkIn));
            pstm.setInt(4, duration);
            pstm.setDate(5, Date.valueOf(checkIn));
            pstm.setDate(6, Date.valueOf(checkIn));
            pstm.setInt(7, duration);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    availableRooms.add(rs.getString("roomId"));
                }
            }
        }
        return availableRooms;
    }

    @Override
    public String getGuestNameByPhone(String phoneNo) throws SQLException {
        String sql = "SELECT name FROM guest WHERE contact = ?";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, phoneNo);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) return rs.getString("name");
                else return null;
            }
        }
    }
}
