package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.DTO.PaymentDTO;
import lk.ijse.project_01.bo.Custom.PaymentBO;
import lk.ijse.project_01.dao.Custom.PaymentDAO;
import lk.ijse.project_01.dao.Custom.impl.PaymentDAOImpl;
import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO  {

    private final PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public boolean processPayment(PaymentDTO payment) throws SQLException {

        return paymentDAO.savePayment(payment);
    }

    @Override
    public boolean updateBookingStatus(String guestPhone) throws SQLException {
        return paymentDAO.updateBookingStatus(guestPhone);
    }
}
