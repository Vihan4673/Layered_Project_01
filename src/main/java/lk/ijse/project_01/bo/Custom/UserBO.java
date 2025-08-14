package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.DTO.UserDTO;
import lk.ijse.project_01.bo.SuperBO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean registerUser(UserDTO userDTO) throws SQLException;
    boolean validateUser(String userName, String password) throws SQLException;
}
