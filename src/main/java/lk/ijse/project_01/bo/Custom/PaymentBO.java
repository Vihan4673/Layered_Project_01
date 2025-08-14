package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.DTO.PaymentDTO;
import lk.ijse.project_01.bo.SuperBO;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    boolean processPayment(PaymentDTO payment) throws SQLException;

    boolean updateBookingStatus(String guestPhone) throws SQLException;
}
