package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.UserBO;
import lk.ijse.project_01.dao.Custom.UserDAO;
import lk.ijse.project_01.dao.Custom.impl.UserDAOImpl;
import lk.ijse.project_01.DTO.UserDTO;
import lk.ijse.project_01.Entity.UserEntity;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean registerUser(UserDTO userDTO) throws SQLException {
        UserEntity entity = new UserEntity(
                userDTO.getUserName(),
                userDTO.getPassword(),
                userDTO.getPhoneNo(),
                userDTO.getRole()
        );
        return userDAO.save(entity);
    }

    @Override
    public boolean validateUser(String userName, String password) throws SQLException {
        return userDAO.checkCredential(userName, password);
    }
}
