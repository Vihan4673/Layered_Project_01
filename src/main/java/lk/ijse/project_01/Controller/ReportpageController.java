package lk.ijse.project_01.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import lk.ijse.project_01.bo.Custom.ReportBO;
import lk.ijse.project_01.bo.Custom.Impl.ReportBOImpl;

public class ReportpageController {

    private final ReportBO reportBO = new ReportBOImpl();

    @FXML
    private JFXButton cusReportbtn;

    @FXML
    private JFXButton empReport;

    @FXML
    private JFXButton inventory;

    @FXML
    void GuestreportOnAction(ActionEvent event) {
        try {
            reportBO.generateGuestReport();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate guest report").show();
        }
    }

    public void BookingReportOnAction(ActionEvent actionEvent) {
        try {
            reportBO.generateBookingReport();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate booking report").show();
        }
    }

    public void EmployeeReportOnAction(ActionEvent actionEvent) {
        try {
            reportBO.generateEmployeeReport();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate employee report").show();
        }
    }
}
