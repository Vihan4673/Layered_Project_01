package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.DashboardBO;
import lk.ijse.project_01.dao.Custom.DashboardDAO;
import lk.ijse.project_01.dao.Custom.impl.DashboardDAOImpl;
import java.sql.SQLException;
import java.util.Map;

public class DashboardBOImpl implements DashboardBO {

    private final DashboardDAO dashboardDAO = new DashboardDAOImpl();

    @Override
    public double getTotalRevenue() throws SQLException {
        return dashboardDAO.getTotalRevenue();
    }

    @Override
    public int getBookingCount() throws SQLException {
        return dashboardDAO.getBookingCount();
    }

    @Override
    public double getTotalRoomPrice() throws SQLException {
        return dashboardDAO.getTotalRoomPrice();
    }

    @Override
    public double getFnbRevenue() throws SQLException {
        return dashboardDAO.getFnbRevenue();
    }

    @Override
    public Map<String, Double> getMonthlyRevenue(String year) throws SQLException {
        return dashboardDAO.getMonthlyRevenue(year);
    }

    @Override
    public Map<String, Integer> getTopFnbItems() throws SQLException {
        return dashboardDAO.getTopFnbItems();
    }
}
