package lk.ijse.project_01.dao.Custom;

import lk.ijse.project_01.Entity.BFDashboardEntity;
import java.util.List;

public interface BFDashboardDAO {
    List<BFDashboardEntity> loadAllOrders();
}
