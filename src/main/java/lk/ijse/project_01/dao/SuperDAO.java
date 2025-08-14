package lk.ijse.project_01.dao;

import lk.ijse.project_01.Entity.BFDashboardEntity;

import java.util.List;

public interface SuperDAO {
    List<BFDashboardEntity> loadAllOrders();
}
