package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.dao.SuperDAO;
import lk.ijse.project_01.DTO.GuestDTO;

import java.sql.SQLException;
import java.util.List;

public interface GuestDAO extends SuperDAO {
    String getNextGuestId() throws SQLException;
    boolean saveGuest(GuestDTO guestDTO) throws SQLException;
    boolean updateGuest(GuestDTO guestDTO) throws SQLException;
    boolean deleteGuest(String guestId) throws SQLException;
    List<GuestDTO> getAllGuests() throws SQLException;
    GuestDTO findGuestById(String guestId) throws SQLException;
    GuestDTO findGuestByPhone(String phone) throws SQLException;
}
