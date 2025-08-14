package lk.ijse.project_01.dao.Custom;

import java.sql.Connection;
import java.sql.SQLException;

public interface RoomDAO {
    int getAvailableRoomCount(Connection con, String roomType) throws SQLException;
    String getAvailableRoomId(Connection con, String roomType) throws SQLException;
    boolean bookRoomById(Connection con, String roomId) throws SQLException;
    boolean releaseRoomById(Connection con, String roomId) throws SQLException;
}
