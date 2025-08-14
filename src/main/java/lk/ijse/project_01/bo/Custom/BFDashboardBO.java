package lk.ijse.project_01.bo.Custom;

import lk.ijse.project_01.DTO.BFDashboardDTO;
import lk.ijse.project_01.bo.SuperBO;

import java.util.List;

public interface BFDashboardBO extends SuperBO {
    List<BFDashboardDTO> getAllOrders();
}
