package lk.ijse.project_01.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_01.Util.Regex;
import lk.ijse.project_01.bo.Custom.BOFactory;
import lk.ijse.project_01.bo.Custom.UserBO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private AnchorPane rootNode;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    public void loginbtnOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText().trim();
        String pw = txtPassword.getText().trim();

        if (userName.isEmpty() || pw.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter username and password.").show();
            return;
        }

        try {
            boolean isCorrect = userBO.validateUser(userName, pw);
            if (isCorrect) {
                navigateToDashBoard(userName);
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        }
    }

    private void navigateToDashBoard(String userName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboardpage.fxml"));
            Parent dashboardRoot = loader.load();

            DashboardpageController controller = loader.getController();
            controller.setUsername(userName);

            Scene scene = new Scene(dashboardRoot);
            Stage stage = (Stage) this.rootNode.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Wimal Villa");


            try (InputStream iconStream = getClass().getResourceAsStream("/icon/sns-removebg-preview.png")) {
                if (iconStream != null) {
                    Image icon = new Image(iconStream);
                    stage.getIcons().add(icon);
                } else {
                    System.err.println("Application icon not found.");
                }
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading dashboard: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    public void linkRegisterOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/signup_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Sign Up Form");
    }

    @FXML
    void PasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.getPasswordPattern(), txtPassword);
    }

    @FXML
    void UserNameOnKeyOnreleased(KeyEvent event) {
        Regex.setTextColor(Regex.getNamePattern(), txtUserName);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        loginbtnOnAction(null);
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }
}
