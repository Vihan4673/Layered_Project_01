package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.GuestBO;
import lk.ijse.project_01.dao.Custom.GuestDAO;
import lk.ijse.project_01.dao.Custom.impl.GuestDAOImpl;
import lk.ijse.project_01.DTO.GuestDTO;
import java.sql.SQLException;
import java.util.List;

public class GuestBOImpl implements GuestBO {

    private final GuestDAO guestDAO = new GuestDAOImpl();

    @Override
    public String getNextGuestId() throws SQLException {
        return guestDAO.getNextGuestId();
    }

    @Override
    public boolean saveGuest(GuestDTO guestDTO) throws SQLException {
        return guestDAO.saveGuest(guestDTO);
    }

    @Override
    public boolean updateGuest(GuestDTO guestDTO) throws SQLException {
        return guestDAO.updateGuest(guestDTO);
    }

    @Override
    public boolean deleteGuest(String guestId) throws SQLException {
        return guestDAO.deleteGuest(guestId);
    }

    @Override
    public List<GuestDTO> getAllGuests() throws SQLException {
        return guestDAO.getAllGuests();
    }

    @Override
    public GuestDTO findGuestByPhone(String phone) throws SQLException {
        return guestDAO.findGuestByPhone(phone);
    }
}
