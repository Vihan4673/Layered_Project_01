package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.Entity.UserEntity;
import java.sql.SQLException;

public interface UserDAO {
    boolean save(UserEntity user) throws SQLException;
    boolean checkCredential(String userName, String password) throws SQLException;
}
