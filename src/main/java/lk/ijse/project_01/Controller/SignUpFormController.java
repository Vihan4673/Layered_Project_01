package lk.ijse.project_01.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_01.DTO.UserDTO;
import lk.ijse.project_01.Util.Regex;
import lk.ijse.project_01.bo.Custom.BOFactory;
import lk.ijse.project_01.bo.Custom.UserBO;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {

    @FXML
    private Button btnSignup;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPhoneNo;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUserName;


    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SIGNUP);

    @FXML
    public void signupbtnOnAction(ActionEvent actionEvent) {
        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();
        String role = txtRole.getText().trim();
        String phoneNo = txtPhoneNo.getText().trim();

        switch (isValid()) {
            case 0:
                try {
                    UserDTO userDTO = new UserDTO(userName, password, phoneNo, role);
                    boolean isSaved = userBO.registerUser(userDTO);

                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "User registered successfully!").show();
                        clearFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to register user.").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
                }
                break;
            case 1:
                new Alert(Alert.AlertType.ERROR, "Invalid username format").show();
                break;
            case 2:
                new Alert(Alert.AlertType.ERROR, "Invalid password format").show();
                break;
            case 3:
                new Alert(Alert.AlertType.ERROR, "Invalid phone number format").show();
                break;
            case 4:
                new Alert(Alert.AlertType.ERROR, "Invalid role format").show();
                break;
        }
    }

    private void clearFields() {
        txtUserName.clear();
        txtPassword.clear();
        txtPhoneNo.clear();
        txtRole.clear();
    }

    @FXML
    void linkSignInOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    @FXML
    void PasswordOnKeyOnreleased(KeyEvent event) {
        Regex.setTextColor(Regex.PASSWORD, txtPassword);
    }

    @FXML
    void PhoneNoOnKeyOnreleased(KeyEvent event) {
        Regex.setTextColor(Regex.PHONENO, txtPhoneNo);
    }

    @FXML
    void RoleOnkeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.NAME, txtRole);
    }

    @FXML
    void UserNameOnKeyOnreleased(KeyEvent event) {
        Regex.setTextColor(Regex.NAME, txtUserName);
    }

    private int isValid() {
        if (!Regex.setTextColor(Regex.NAME, txtUserName)) return 1;
        if (!Regex.setTextColor(Regex.PASSWORD, txtPassword)) return 2;
        if (!Regex.setTextColor(Regex.PHONENO, txtPhoneNo)) return 3;
        if (!Regex.setTextColor(Regex.NAME, txtRole)) return 4;
        return 0;
    }
}
