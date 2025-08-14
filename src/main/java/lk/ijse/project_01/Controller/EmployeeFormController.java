package lk.ijse.project_01.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.project_01.DTO.Employee;
import lk.ijse.project_01.Util.Regex;
import lk.ijse.project_01.bo.Custom.EmployeeBO;
import lk.ijse.project_01.bo.Custom.Impl.EmployeeBOImpl;

import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {

    @FXML private TableView<Employee> tblEmployee;
    @FXML private TableColumn<Employee, String> colId;
    @FXML private TableColumn<Employee, String> colName;
    @FXML private TableColumn<Employee, String> colAddress;
    @FXML private TableColumn<Employee, String> colPhoneno;
    @FXML private TableColumn<Employee, String> colPosition;
    @FXML private TableColumn<Employee, Double> colSalary;

    @FXML private TextField txtEmployeeId;
    @FXML private TextField txtEmployeeName;
    @FXML private TextField txtEmployeeAddress;
    @FXML private TextField txtEmployeeTel;
    @FXML private TextField txtEmployeePosition;
    @FXML private TextField txtEmployeeSalary;

    private final EmployeeBO employeeBO = new EmployeeBOImpl();

    @FXML
    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();
        setupTableListener();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        colPhoneno.setCellValueFactory(new PropertyValueFactory<>("employeeTel"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("employeePosition"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("employeeSalary"));
    }

    private void loadAllEmployees() {
        try {
            List<Employee> employees = employeeBO.getAllEmployees();
            ObservableList<Employee> obList = FXCollections.observableArrayList(employees);
            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load employees: " + e.getMessage()).show();
        }
    }

    private void setupTableListener() {
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtEmployeeId.setText(newVal.getEmployeeId());
                txtEmployeeName.setText(newVal.getEmployeeName());
                txtEmployeeAddress.setText(newVal.getEmployeeAddress());
                txtEmployeeTel.setText(newVal.getEmployeeTel());
                txtEmployeePosition.setText(newVal.getEmployeePosition());
                txtEmployeeSalary.setText(String.valueOf(newVal.getEmployeeSalary()));
            }
        });
    }

    @FXML
    private void txtEmpIdOnAction(ActionEvent event) {
        txtEmployeeName.requestFocus();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            if (!validateInputs()) return;

            Employee employee = new Employee(
                    txtEmployeeId.getText().trim(),
                    txtEmployeeName.getText().trim(),
                    txtEmployeeAddress.getText().trim(),
                    txtEmployeeTel.getText().trim(),
                    txtEmployeePosition.getText().trim(),
                    Double.parseDouble(txtEmployeeSalary.getText().trim())
            );

            boolean isSaved = employeeBO.saveEmployee(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved successfully!").show();
                clearFields();
                loadAllEmployees();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to save employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error while saving: " + e.getMessage()).show();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary input!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (!validateInputs()) return;

            Employee employee = new Employee(
                    txtEmployeeId.getText().trim(),
                    txtEmployeeName.getText().trim(),
                    txtEmployeeAddress.getText().trim(),
                    txtEmployeeTel.getText().trim(),
                    txtEmployeePosition.getText().trim(),
                    Double.parseDouble(txtEmployeeSalary.getText().trim())
            );

            boolean isUpdated = employeeBO.updateEmployee(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!").show();
                clearFields();
                loadAllEmployees();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to update employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error while updating: " + e.getMessage()).show();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary input!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            String id = txtEmployeeId.getText().trim();
            if (id.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please enter Employee ID to delete!").show();
                return;
            }
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Employee deleted successfully!").show();
                clearFields();
                loadAllEmployees();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to delete employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error while deleting: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeeAddress.clear();
        txtEmployeeTel.clear();
        txtEmployeePosition.clear();
        txtEmployeeSalary.clear();
        tblEmployee.getSelectionModel().clearSelection();
    }

    private boolean validateInputs() {
        if (!Regex.getIdPattern().matcher(txtEmployeeId.getText()).matches()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID format!").show();
            return false;
        }
        if (!Regex.getNamePattern().matcher(txtEmployeeName.getText()).matches()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Name!").show();
            return false;
        }
        if (!Regex.getAddressPattern().matcher(txtEmployeeAddress.getText()).matches()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address!").show();
            return false;
        }
        if (!Regex.getMobilePattern().matcher(txtEmployeeTel.getText()).matches()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Phone Number!").show();
            return false;
        }
        if (!Regex.getNamePattern().matcher(txtEmployeePosition.getText()).matches()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Position!").show();
            return false;
        }
        if (!Regex.getSalaryPattern().matcher(txtEmployeeSalary.getText()).matches()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Salary!").show();
            return false;
        }
        return true;
    }

    @FXML
    void txtEmployeeIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.getIdPattern(), txtEmployeeId);
    }

    @FXML
    void txtEmployeeNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.getNamePattern(), txtEmployeeName);
    }

    @FXML
    void txtEmployeeAddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.getAddressPattern(), txtEmployeeAddress);
    }

    @FXML
    void txtPhoneNoOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.getMobilePattern(), txtEmployeeTel);
    }

    @FXML
    void txtPositionOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.getNamePattern(), txtEmployeePosition);
    }

    @FXML
    void txtSalaryOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(Regex.getSalaryPattern(), txtEmployeeSalary);
    }
}
