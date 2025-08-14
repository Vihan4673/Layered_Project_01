package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.bo.SuperBO;
import lk.ijse.project_01.DTO.GuestDTO;

import java.sql.SQLException;
import java.util.List;

public interface GuestBO extends SuperBO {
    String getNextGuestId() throws SQLException;
    boolean saveGuest(GuestDTO guestDTO) throws SQLException;
    boolean updateGuest(GuestDTO guestDTO) throws SQLException;
    boolean deleteGuest(String guestId) throws SQLException;
    List<GuestDTO> getAllGuests() throws SQLException;
    GuestDTO findGuestByPhone(String phone) throws SQLException;
}
