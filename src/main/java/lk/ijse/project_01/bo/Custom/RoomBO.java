package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.bo.SuperBO;

import java.sql.SQLException;

public interface RoomBO extends SuperBO {
    int getAvailableRoomCount(String roomType) throws SQLException;
    String getAvailableRoomId(String roomType) throws SQLException;
    boolean bookRoomById(String roomId) throws SQLException;
    boolean releaseRoomById(String roomId) throws SQLException;
}
