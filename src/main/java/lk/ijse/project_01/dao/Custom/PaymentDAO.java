package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.DTO.PaymentDTO;
import java.sql.SQLException;

public interface PaymentDAO {
    boolean savePayment(PaymentDTO payment) throws SQLException;
    boolean updateBookingStatus(String guestPhone) throws SQLException;
}
