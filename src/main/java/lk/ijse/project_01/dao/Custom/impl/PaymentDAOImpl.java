package lk.ijse.project_01.dao.Custom.impl;

import lk.ijse.project_01.DB.DBConnection;
import lk.ijse.project_01.DTO.PaymentDTO;
import lk.ijse.project_01.dao.Custom.PaymentDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean savePayment(PaymentDTO payment) throws SQLException {
        String sql = "INSERT INTO Payment (Amount, PaymentDate, PaymentMethod, GuestPhone) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setDouble(1, payment.getAmount());
            pst.setDate(2, java.sql.Date.valueOf(payment.getPaymentDate()));
            pst.setString(3, payment.getPaymentMethod());
            pst.setString(4, payment.getGuestPhone());

            return pst.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateBookingStatus(String guestPhone) throws SQLException {
        String sql = "UPDATE booking SET status = 'Paid' WHERE guestPhone = ?";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, guestPhone);
            return pst.executeUpdate() > 0;
        }
    }

    public static boolean savePayment(String guestPhone, Double guestPaid, String paymentMethod) {
        String sql = "INSERT INTO Payment (Amount, PaymentDate, PaymentMethod, GuestPhone) VALUES (?, CURRENT_DATE, ?, ?)";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setDouble(1, guestPaid);
            pst.setString(2, paymentMethod);
            pst.setString(3, guestPhone);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updatePaymentStatus(String guestPhone) {
        String sql = "UPDATE booking SET status = 'Paid' WHERE guestPhone = ?";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, guestPhone);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}