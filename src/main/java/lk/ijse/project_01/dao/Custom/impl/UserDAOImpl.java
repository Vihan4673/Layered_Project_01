package lk.ijse.project_01.dao.Custom.impl;

import lk.ijse.project_01.DB.DBConnection;
import lk.ijse.project_01.dao.Custom.UserDAO;
import lk.ijse.project_01.Entity.UserEntity;
import lk.ijse.project_01.DTO.TM.UserTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(UserEntity user) throws SQLException {
        String sql = "INSERT INTO user (userName, password, phoneNo, role) VALUES (?, ?, ?, ?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, user.getUserName());
        pstm.setString(2, user.getPassword());
        pstm.setString(3, user.getPhoneNo());
        pstm.setString(4, user.getRole());
        return pstm.executeUpdate() > 0;
    }

    public boolean saveUser(UserTM user) throws SQLException {
        UserEntity entity = new UserEntity(
                user.getUserName(),
                user.getPassword(),
                user.getPhoneNo(),
                user.getRole()
        );
        return save(entity);
    }

    @Override
    public boolean checkCredential(String userName, String password) throws SQLException {
        String sql = "SELECT password FROM user WHERE userName = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return password.equals(rs.getString("password"));
        }
        return false;
    }
}