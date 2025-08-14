package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.RoomBO;
import lk.ijse.project_01.dao.Custom.RoomDAO;
import lk.ijse.project_01.dao.Custom.impl.RoomDAOImpl;
import lk.ijse.project_01.DB.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class RoomBOImpl implements RoomBO {

    private final RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public int getAvailableRoomCount(String roomType) throws SQLException {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            return roomDAO.getAvailableRoomCount(con, roomType);
        }
    }

    @Override
    public String getAvailableRoomId(String roomType) throws SQLException {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            return roomDAO.getAvailableRoomId(con, roomType);
        }
    }

    @Override
    public boolean bookRoomById(String roomId) throws SQLException {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            return roomDAO.bookRoomById(con, roomId);
        }
    }

    @Override
    public boolean releaseRoomById(String roomId) throws SQLException {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            return roomDAO.releaseRoomById(con, roomId);
        }
    }
}
