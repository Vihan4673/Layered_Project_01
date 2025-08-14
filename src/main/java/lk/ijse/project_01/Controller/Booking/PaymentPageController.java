package lk.ijse.project_01.Controller.Booking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.project_01.dao.Custom.impl.PaymentDAOImpl;
import java.io.IOException;

public class PaymentPageController {

    @FXML private TextField txtGuestPhone, txtTotalAmount, txtGuestPaid, txtAmountToPay, txtCardNumber;
    @FXML private RadioButton rbCash, rbCard;
    @FXML private ToggleGroup paymentToggleGroup;
    @FXML private Label lblStatus;

    private String guestPhone;
    private double totalAmount;
    private Runnable onPaymentSuccess;


    public void setPaymentData(String guestPhone, double totalAmount) {
        this.guestPhone = guestPhone;
        this.totalAmount = totalAmount;

        txtGuestPhone.setText(guestPhone);
        txtTotalAmount.setText(String.format("%.2f", totalAmount));
        txtGuestPaid.clear();
        txtAmountToPay.clear();
        txtCardNumber.clear();
        lblStatus.setText("");

        txtGuestPaid.setEditable(true);
        txtAmountToPay.setEditable(false);

        rbCash.setDisable(false);
        rbCard.setDisable(false);
        rbCash.setSelected(true);

        updateCardNumberField();
    }

    public void setOnPaymentSuccess(Runnable onPaymentSuccess) {
        this.onPaymentSuccess = onPaymentSuccess;
    }


    @FXML
    private void handlePayAction() {
        lblStatus.setText("");

        Double guestPaid = parseDouble(txtGuestPaid.getText().trim(), "Invalid paid amount.");
        if (guestPaid == null || guestPaid <= 0) {
            showError("Amount must be a positive number.");
            return;
        }

        RadioButton selectedMethod = (RadioButton) paymentToggleGroup.getSelectedToggle();
        if (selectedMethod == null) {
            showError("Please select a payment method.");
            return;
        }

        String paymentMethod = selectedMethod.getText();
        if ("Card".equalsIgnoreCase(paymentMethod) && txtCardNumber.getText().trim().isEmpty()) {
            showError("Please enter card number for card payment.");
            return;
        }

        double balance = totalAmount - guestPaid;

        boolean saved = PaymentDAOImpl.savePayment(guestPhone, guestPaid, paymentMethod);
        if (!saved) {
            showError("Payment failed. Please try again.");
            return;
        }

        boolean updated = PaymentDAOImpl.updatePaymentStatus(guestPhone);
        if (updated) {
            showSuccess(balance);
            lockPaymentFields();
            showAlert(balance);
            if (onPaymentSuccess != null) {
                onPaymentSuccess.run();
            }
        } else {
            lblStatus.setStyle("-fx-text-fill: orange;");
            lblStatus.setText("Payment saved, but status update failed.");
        }
    }

    @FXML
    public void calculateBalance(KeyEvent keyEvent) {
        Double guestPaid = parseDouble(txtGuestPaid.getText().trim(), null);
        if (guestPaid == null) {
            txtAmountToPay.clear();
            lblStatus.setText("");
            return;
        }

        double balance = totalAmount - guestPaid;
        txtAmountToPay.setText(String.format("%.2f", balance));

        if (balance < 0) {
            lblStatus.setStyle("-fx-text-fill: orange;");
            lblStatus.setText("Guest paid more than total. Return change: " + String.format("%.2f", -balance));
        } else if (guestPaid < 0) {
            showError("Amount must be positive.");
            txtAmountToPay.clear();
        } else {
            lblStatus.setText("");
        }
    }

    @FXML
    public void handlePaymentMethodChange(ActionEvent event) {
        updateCardNumberField();
    }

    private void updateCardNumberField() {
        boolean isCardSelected = rbCard.isSelected();
        txtCardNumber.setDisable(!isCardSelected);
        txtCardNumber.setVisible(isCardSelected);

        if (!isCardSelected) {
            txtCardNumber.clear();
        }
    }

    @FXML
    public void handlePrintBillAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BillView.fxml"));
            Parent root = loader.load();

            BillViewController controller = loader.getController();
            String paymentMethod = ((RadioButton) paymentToggleGroup.getSelectedToggle()).getText();
            double paidAmount = parseDouble(txtGuestPaid.getText().trim(), "0.00");
            double balance = totalAmount - paidAmount;

            controller.setBillData(guestPhone, totalAmount, paidAmount, paymentMethod, balance);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Bill");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to open bill: " + e.getMessage());
        }
    }

    private Double parseDouble(String text, String errorMsg) {
        if (text.isEmpty()) {
            if (errorMsg != null) showError(errorMsg);
            return null;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            if (errorMsg != null) showError(errorMsg);
            return null;
        }
    }

    private void showSuccess(double balance) {
        lblStatus.setStyle("-fx-text-fill: green;");
        if (balance < 0) {
            lblStatus.setText("Payment successful! Return change: " + String.format("%.2f", -balance));
        } else {
            lblStatus.setText("Payment successful! Balance: " + String.format("%.2f", balance));
        }
    }

    private void showError(String message) {
        lblStatus.setStyle("-fx-text-fill: red;");
        lblStatus.setText(message);
    }

    private void lockPaymentFields() {
        txtGuestPaid.setEditable(false);
        txtAmountToPay.setEditable(false);
        rbCash.setDisable(true);
        rbCard.setDisable(true);
        txtCardNumber.setEditable(false);
    }

    private void showAlert(double balance) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment");
        alert.setHeaderText(null);
        if (balance < 0) {
            alert.setContentText("Payment processed.\nReturn change: " + String.format("%.2f", -balance));
        } else {
            alert.setContentText("Payment processed.\nBalance: " + String.format("%.2f", balance));
        }
        alert.showAndWait();
    }
}
