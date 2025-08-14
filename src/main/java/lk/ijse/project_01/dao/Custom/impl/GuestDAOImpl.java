package lk.ijse.project_01.dao.Custom.impl;

import lk.ijse.project_01.Entity.BFDashboardEntity;
import lk.ijse.project_01.dao.Custom.GuestDAO;
import lk.ijse.project_01.DTO.GuestDTO;
import lk.ijse.project_01.Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAOImpl implements GuestDAO {

    @Override
    public String getNextGuestId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT GuestID FROM guest ORDER BY GuestID DESC LIMIT 1");
        char prefix = 'G';

        if (rs.next()) {
            String lastId = rs.getString(1);
            if (lastId != null && lastId.length() > 1) {
                int lastNum = Integer.parseInt(lastId.substring(1));
                int nextNum = lastNum + 1;
                return String.format("%c%03d", prefix, nextNum);
            }
        }
        return prefix + "001";
    }

    @Override
    public boolean saveGuest(GuestDTO guestDTO) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO guest VALUES (?,?,?,?,?)",
                guestDTO.getGuestId(),
                guestDTO.getName(),
                guestDTO.getNic(),
                guestDTO.getAddress(),
                guestDTO.getPhone()
        );
    }

    @Override
    public boolean updateGuest(GuestDTO guestDTO) throws SQLException {
        return CrudUtil.execute(
                "UPDATE guest SET name=?, NIC=?, address=?, Contact=? WHERE GuestID=?",
                guestDTO.getName(),
                guestDTO.getNic(),
                guestDTO.getAddress(),
                guestDTO.getPhone(),
                guestDTO.getGuestId()
        );
    }

    @Override
    public boolean deleteGuest(String guestId) throws SQLException {
        return CrudUtil.execute("DELETE FROM guest WHERE GuestID=?", guestId);
    }

    @Override
    public List<GuestDTO> getAllGuests() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM guest");
        List<GuestDTO> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new GuestDTO(
                    rs.getString("GuestID"),
                    rs.getString("name"),
                    rs.getString("NIC"),
                    rs.getString("address"),
                    rs.getString("Contact")
            ));
        }
        return list;
    }

    @Override
    public GuestDTO findGuestById(String guestId) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM guest WHERE GuestID=?", guestId);
        if (rs.next()) {
            return new GuestDTO(
                    rs.getString("GuestID"),
                    rs.getString("name"),
                    rs.getString("NIC"),
                    rs.getString("address"),
                    rs.getString("Contact")
            );
        }
        return null;
    }

    @Override
    public GuestDTO findGuestByPhone(String phone) throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM guest WHERE Contact=?", phone);
        if (rs.next()) {
            return new GuestDTO(
                    rs.getString("GuestID"),
                    rs.getString("name"),
                    rs.getString("NIC"),
                    rs.getString("address"),
                    rs.getString("Contact")
            );
        }
        return null;
    }

    @Override
    public List<BFDashboardEntity> loadAllOrders() {
        return List.of();
    }
}
