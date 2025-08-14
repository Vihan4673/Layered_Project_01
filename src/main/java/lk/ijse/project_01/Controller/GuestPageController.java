package lk.ijse.project_01.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.project_01.DTO.GuestDTO;
import lk.ijse.project_01.DTO.TM.GuestTM;
import lk.ijse.project_01.bo.Custom.BOFactory;
import lk.ijse.project_01.bo.Custom.GuestBO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GuestPageController implements Initializable {

    @FXML private Label lblGuestId;
    @FXML private TextField txtName;
    @FXML private TextField txtNic;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPhone;

    @FXML private TableView<GuestTM> tblGuest;
    @FXML private TableColumn<GuestTM, String> colId;
    @FXML private TableColumn<GuestTM, String> colName;
    @FXML private TableColumn<GuestTM, String> colNic;
    @FXML private TableColumn<GuestTM, String> colAddress;
    @FXML private TableColumn<GuestTM, String> colPhone;

    @FXML private Button btnSave;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;

    private final String namePattern = "^[A-Za-z ]+$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String addressPattern = "^[A-Za-z0-9 ,.]+$";
    private final String phonePattern = "^[0-9]{10}$";

    private final GuestBO guestBO =
            (GuestBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.GUEST);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactories();
        loadAllGuests();
        setNextGuestId();
        disableButtons(true);
    }

    private void setCellValueFactories() {
        colId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void loadAllGuests() {
        try {
            List<GuestDTO> guests = guestBO.getAllGuests();
            List<GuestTM> tmList = guests.stream()
                    .map(dto -> new GuestTM(
                            dto.getGuestId(),
                            dto.getName(),
                            dto.getNic(),
                            dto.getAddress(),
                            dto.getPhone()
                    ))
                    .toList();
            tblGuest.setItems(FXCollections.observableArrayList(tmList));
        } catch (SQLException e) {
            showError("Failed to load guests.");
        }
    }

    private void setNextGuestId() {
        try {
            lblGuestId.setText(guestBO.getNextGuestId());
        } catch (SQLException e) {
            showError("Failed to generate Guest ID.");
        }
    }

    private void clearFields() {
        txtName.clear();
        txtNic.clear();
        txtAddress.clear();
        txtPhone.clear();
        setNextGuestId();
        disableButtons(true);
    }

    private void disableButtons(boolean isNew) {
        btnSave.setDisable(!isNew);
        btnUpdate.setDisable(isNew);
        btnDelete.setDisable(isNew);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if (!validateFields()) return;

        GuestDTO dto = new GuestDTO(
                lblGuestId.getText(),
                txtName.getText(),
                txtNic.getText(),
                txtAddress.getText(),
                txtPhone.getText()
        );

        try {
            if (guestBO.saveGuest(dto)) {
                loadAllGuests();
                clearFields();
                showInfo("Guest saved successfully.");
            } else {
                showError("Failed to save guest.");
            }
        } catch (SQLException e) {
            showError("Database error while saving guest.");
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (!validateFields()) return;

        GuestDTO dto = new GuestDTO(
                lblGuestId.getText(),
                txtName.getText(),
                txtNic.getText(),
                txtAddress.getText(),
                txtPhone.getText()
        );

        try {
            if (guestBO.updateGuest(dto)) {
                loadAllGuests();
                clearFields();
                showInfo("Guest updated successfully.");
            } else {
                showError("Failed to update guest.");
            }
        } catch (SQLException e) {
            showError("Database error while updating guest.");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                if (guestBO.deleteGuest(lblGuestId.getText())) {
                    loadAllGuests();
                    clearFields();
                    showInfo("Guest deleted successfully.");
                } else {
                    showError("Failed to delete guest.");
                }
            } catch (SQLException e) {
                showError("Database error while deleting guest.");
            }
        }
    }

    @FXML
    private void btnResetOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void onClickTable(MouseEvent event) {
        GuestTM selected = tblGuest.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lblGuestId.setText(selected.getGuestId());
            txtName.setText(selected.getName());
            txtNic.setText(selected.getNic());
            txtAddress.setText(selected.getAddress());
            txtPhone.setText(selected.getPhone());
            disableButtons(false);
        }
    }

    private boolean validateFields() {
        boolean valid = true;

        if (!txtName.getText().matches(namePattern)) {
            txtName.setStyle("-fx-border-color: red;");
            valid = false;
        } else txtName.setStyle(null);

        if (!txtNic.getText().matches(nicPattern)) {
            txtNic.setStyle("-fx-border-color: red;");
            valid = false;
        } else txtNic.setStyle(null);

        if (!txtAddress.getText().matches(addressPattern)) {
            txtAddress.setStyle("-fx-border-color: red;");
            valid = false;
        } else txtAddress.setStyle(null);

        if (!txtPhone.getText().matches(phonePattern)) {
            txtPhone.setStyle("-fx-border-color: red;");
            valid = false;
        } else txtPhone.setStyle(null);

        if (!valid) showError("Please fix validation errors.");
        return valid;
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    private void showInfo(String message) {
        new Alert(Alert.AlertType.INFORMATION, message).show();
    }
}
