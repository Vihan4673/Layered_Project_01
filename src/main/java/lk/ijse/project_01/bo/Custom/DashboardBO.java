package lk.ijse.project_01.bo.Custom;

import java.sql.SQLException;
import java.util.Map;

public interface DashboardBO {
    double getTotalRevenue() throws SQLException;
    int getBookingCount() throws SQLException;
    double getTotalRoomPrice() throws SQLException;
    double getFnbRevenue() throws SQLException;
    Map<String, Double> getMonthlyRevenue(String year) throws SQLException;
    Map<String, Integer> getTopFnbItems() throws SQLException;
}
