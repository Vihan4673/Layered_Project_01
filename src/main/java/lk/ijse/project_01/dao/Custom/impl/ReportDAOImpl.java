package lk.ijse.project_01.dao.Custom.impl;

import lk.ijse.project_01.DB.DBConnection;
import lk.ijse.project_01.dao.Custom.ReportDAO;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReportDAOImpl implements ReportDAO {
    @Override
    public void generateReport(String reportPath) throws Exception {
        JasperReport report = JasperCompileManager.compileReport(
                getClass().getResourceAsStream(reportPath)
        );
        Connection connection = DBConnection.getInstance().getConnection();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("P_DATE", LocalDate.now().toString());

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                parameters,
                connection
        );

        JasperViewer.viewReport(jasperPrint, false);
    }
}
