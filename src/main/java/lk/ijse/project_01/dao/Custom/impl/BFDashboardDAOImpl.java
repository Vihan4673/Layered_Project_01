package lk.ijse.project_01.dao.Custom.impl;


import lk.ijse.project_01.DB.DBConnection;
import lk.ijse.project_01.Entity.BFDashboardEntity;
import lk.ijse.project_01.dao.Custom.BFDashboardDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BFDashboardDAOImpl implements BFDashboardDAO {

    @Override
    public List<BFDashboardEntity> loadAllOrders() {
        List<BFDashboardEntity> list = new ArrayList<>();

        String sql = "SELECT b.guestPhone, " +
                "GROUP_CONCAT(CONCAT(b.roomType, ' (', b.duration, ')') SEPARATOR ', ') AS roomDescriptions, " +
                "GROUP_CONCAT(DISTINCT f.item_name SEPARATOR ', ') AS foodItems, " +
                "SUM(b.totalPrice) AS bookingCost, " +
                "IFNULL(SUM(f.total), 0) AS fnbCost, " +
                "SUM(b.totalPrice) + IFNULL(SUM(f.total), 0) AS totalCost, " +
                "MAX(b.status) AS paymentStatus " +
                "FROM booking b " +
                "LEFT JOIN foodorderdetail f ON b.guestPhone = f.guestPhone " +
                "GROUP BY b.guestPhone";

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String guestPhone = rs.getString("guestPhone");
                String roomDescriptions = rs.getString("roomDescriptions");
                String foodItems = rs.getString("foodItems");
                String description = "Rooms: " + roomDescriptions +
                        (foodItems != null && !foodItems.isEmpty() ? " | Food: " + foodItems : "");
                double bookingCost = rs.getDouble("bookingCost");
                double fnbCost = rs.getDouble("fnbCost");
                double totalCost = rs.getDouble("totalCost");
                String paymentStatus = rs.getString("paymentStatus");

                list.add(new BFDashboardEntity(null, guestPhone, "", description, bookingCost, fnbCost, totalCost, paymentStatus));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
