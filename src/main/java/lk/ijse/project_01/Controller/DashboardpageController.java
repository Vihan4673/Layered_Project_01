package lk.ijse.project_01.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lk.ijse.project_01.bo.Custom.DashboardBO;
import lk.ijse.project_01.bo.Custom.Impl.DashboardBOImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardpageController implements Initializable {

    public AnchorPane ancMainContainer;

    private String username;

    private final DashboardBO dashboardBO = new DashboardBOImpl();

    public JFXButton btnGoHomePage;
    public JFXButton btnGoGuestPage;
    public JFXButton btnorderPage;
    public JFXButton btnEmployeePage;
    public JFXButton btnSupplierPage;
    public JFXButton btnReportPage;
    public JFXButton logOutBtn;

    public void setUsername(String username) {
        this.username = username;
        System.out.println("Logged in user is: " + username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        navigateTo("/View/Homepage.fxml");

        addHoverEffect(btnGoHomePage);
        addHoverEffect(btnGoGuestPage);
        addHoverEffect(btnorderPage);
        addHoverEffect(btnEmployeePage);
        addHoverEffect(btnSupplierPage);
        addHoverEffect(btnReportPage);
        addHoverEffect(logOutBtn);
    }

    private void addHoverEffect(JFXButton button) {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setColor(Color.DARKGRAY);

        button.setOnMouseEntered(e -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
            button.setEffect(shadow);
        });
        button.setOnMouseExited(e -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            button.setEffect(null);
        });
    }

    public void btnGoHomePageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/Homepage.fxml");
    }

    public void btnGoGuestPageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/Guestpage.fxml");
    }

    public void btnorderPageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/B&FDashboardPage.fxml");
    }

    public void btnEmployeePageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/employee_form.fxml");
    }

    public void btnReportPageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/ReportPage.fxml");
    }

    public void btnSupplierPageOnAction(ActionEvent actionEvent) {
        navigateTo("/View/InventoryPage.fxml");
    }

    public void btnLogouttPageOnAction(ActionEvent event) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/View/login_form.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loginRoot));
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    private void navigateTo(String path) {
        try {
            ancMainContainer.getChildren().clear();

            Parent root = FXMLLoader.load(getClass().getResource(path));

            if (root instanceof Region) {
                Region regionRoot = (Region) root;
                regionRoot.prefWidthProperty().bind(ancMainContainer.widthProperty());
                regionRoot.prefHeightProperty().bind(ancMainContainer.heightProperty());
            }

            ancMainContainer.getChildren().add(root);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }
}
