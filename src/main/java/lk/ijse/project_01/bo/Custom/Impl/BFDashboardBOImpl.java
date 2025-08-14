package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.DTO.BFDashboardDTO;
import lk.ijse.project_01.Entity.BFDashboardEntity;
import lk.ijse.project_01.bo.Custom.BFDashboardBO;
import lk.ijse.project_01.dao.Custom.BFDashboardDAO;
import lk.ijse.project_01.dao.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class BFDashboardBOImpl implements BFDashboardBO {

    private final BFDashboardDAO dashboardDAO = (BFDashboardDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BF_DASHBOARD);

    @Override
    public List<BFDashboardDTO> getAllOrders() {
        List<BFDashboardEntity> entities = dashboardDAO.loadAllOrders();
        List<BFDashboardDTO> dtoList = new ArrayList<>();

        for (BFDashboardEntity e : entities) {
            dtoList.add(new BFDashboardDTO(
                    e.getOrderId(),
                    e.getGuestPhone(),
                    e.getGuestName(),
                    e.getDescription(),
                    e.getBookingCost(),
                    e.getFnbCost(),
                    e.getTotalCost(),
                    e.getPaymentStatus()
            ));
        }
        return dtoList;
    }
}