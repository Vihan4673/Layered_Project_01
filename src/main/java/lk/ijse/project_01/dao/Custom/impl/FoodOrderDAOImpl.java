package lk.ijse.project_01.dao.Custom.impl;

import lk.ijse.project_01.DB.DBConnection;
import lk.ijse.project_01.DTO.FoodItem;
import lk.ijse.project_01.DTO.OrderItem;
import lk.ijse.project_01.dao.Custom.FoodOrderDAO;

import java.sql.*;
import java.util.*;

public class FoodOrderDAOImpl implements FoodOrderDAO {

    @Override
    public String getGuestNameByPhone(String phoneNo) throws SQLException {
        String sql = "SELECT name FROM guest WHERE contact = ?";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, phoneNo);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) return rs.getString("name");
                else return null;
            }
        }
    }

    @Override
    public boolean saveFoodOrder(String phone, List<OrderItem> orderList) throws SQLException {
        String detailSql = "INSERT INTO FoodOrderDetail (item_name, quantity, unit_price, total, guestPhone, date) VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement detailStmt = con.prepareStatement(detailSql)) {

            con.setAutoCommit(false);

            for (OrderItem item : orderList) {
                detailStmt.setString(1, item.getItem());
                detailStmt.setInt(2, item.getQuantity());
                detailStmt.setDouble(3, item.getUnitPrice());
                detailStmt.setDouble(4, item.getTotal());
                detailStmt.setString(5, phone);
                detailStmt.addBatch();
            }

            int[] result = detailStmt.executeBatch();

            for (int res : result) {
                if (res == 0) {
                    con.rollback();
                    return false;
                }
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            throw e;
        } finally {
            try (Connection con = DBConnection.getInstance().getConnection()) {
                con.setAutoCommit(true);
            }
        }
    }

    @Override
    public Map<String, List<FoodItem>> getAllFoodItemsGroupedByCategory() throws SQLException {
        String sql = "SELECT name, price, category FROM FoodItem";
        Map<String, List<FoodItem>> categoryMap = new HashMap<>();
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String category = rs.getString("category");

                FoodItem item = new FoodItem(name, price);
                categoryMap.computeIfAbsent(category, k -> new ArrayList<>()).add(item);
            }
        }
        return categoryMap;
    }
}
