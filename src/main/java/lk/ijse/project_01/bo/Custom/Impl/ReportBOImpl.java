package lk.ijse.project_01.bo.Custom.Impl;

import lk.ijse.project_01.bo.Custom.ReportBO;
import lk.ijse.project_01.dao.Custom.ReportDAO;
import lk.ijse.project_01.dao.Custom.impl.ReportDAOImpl;

public class ReportBOImpl implements ReportBO {

    private final ReportDAO reportDAO = new ReportDAOImpl();

    @Override
    public void generateGuestReport() throws Exception {
        reportDAO.generateReport("/Report/GuestDetailsReport.jrxml");
    }

    @Override
    public void generateBookingReport() throws Exception {
        reportDAO.generateReport("/Report/BookingReport.jrxml");
    }

    @Override
    public void generateEmployeeReport() throws Exception {
        reportDAO.generateReport("/Report/EmployeeReport.jrxml");
    }
}
