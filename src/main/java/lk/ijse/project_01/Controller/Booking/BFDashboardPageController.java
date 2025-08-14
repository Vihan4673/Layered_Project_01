package lk.ijse.project_01.Controller.Booking;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.project_01.DTO.BFDashboardDTO;
import lk.ijse.project_01.bo.Custom.BFDashboardBO;
import lk.ijse.project_01.bo.Custom.BOFactory;
import lk.ijse.project_01.dao.Custom.impl.PaymentDAOImpl;

import java.io.IOException;

public class BFDashboardPageController {

    @FXML
    private VBox orderDashboard;
    @FXML
    private JFXButton addBooking;
    @FXML
    private JFXButton addFoodBeverage;
    @FXML
    private JFXButton btnPayment;
    @FXML
    private JFXButton btnSearch;

    @FXML
    private TableColumn<BFDashboardDTO, String> descriptionCol;
    @FXML
    private TableColumn<BFDashboardDTO, String> guestNameCol;
    @FXML
    private TableColumn<BFDashboardDTO, String> guestTelCol;
    @FXML
    private TableView<BFDashboardDTO> orderTable;
    @FXML
    private TableColumn<BFDashboardDTO, String> paymentCol;
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<BFDashboardDTO, Double> totalCostCol;

    private final ObservableList<BFDashboardDTO> orderList = FXCollections.observableArrayList();
    private final BFDashboardBO dashboardBO =
            (BFDashboardBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BFDASHBOARD);

    @FXML
    public void initialize() {
        guestTelCol.setCellValueFactory(cell -> cell.getValue().guestPhoneProperty());
        guestNameCol.setCellValueFactory(cell -> cell.getValue().guestNameProperty());
        descriptionCol.setCellValueFactory(cell -> cell.getValue().descriptionProperty());
        totalCostCol.setCellValueFactory(cell -> cell.getValue().totalCostProperty().asObject());
        paymentCol.setCellValueFactory(cell -> cell.getValue().paymentStatusProperty());

        loadOrders();
    }

    private void loadOrders() {
        orderList.setAll(dashboardBO.getAllOrders());
        orderTable.setItems(orderList);
    }

    @FXML
    void addBookingOnAction(ActionEvent event) {
        try {
            AnchorPane bookingPage = FXMLLoader.load(getClass().getResource("/View/BookingPage.fxml"));
            orderDashboard.getChildren().setAll(bookingPage);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load booking page!").show();
        }
    }

    @FXML
    void addFoodBeverageOnAction(ActionEvent event) {
        try {
            AnchorPane foodBeveragePage = FXMLLoader.load(getClass().getResource("/View/F&BOderPage.fxml"));
            orderDashboard.getChildren().setAll(foodBeveragePage);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load food & beverage page!").show();
        }
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        BFDashboardDTO selectedOrder = orderTable.getSelectionModel().getSelectedItem();

        if (selectedOrder == null) {
            new Alert(Alert.AlertType.WARNING, "Please select an order to make payment.").show();
            return;
        }

        if ("Paid".equalsIgnoreCase(selectedOrder.getPaymentStatus())) {
            new Alert(Alert.AlertType.INFORMATION, "This order is already paid.").show();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PaymentPage.fxml"));
            AnchorPane paymentPage = loader.load();

            PaymentPageController controller = loader.getController();
            controller.setPaymentData(
                    selectedOrder.getGuestPhone(),
                    selectedOrder.getTotalCost()
            );

            controller.setOnPaymentSuccess(() -> {
                boolean updated = PaymentDAOImpl.updatePaymentStatus(selectedOrder.getGuestPhone());
                if (updated) {
                    loadOrders();
                    new Alert(Alert.AlertType.INFORMATION, "Payment successful and updated!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment saved, but failed to update status!").show();
                }
            });

            orderDashboard.getChildren().setAll(paymentPage);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load payment page!").show();
        }
    }

    @FXML
    void searchOnAction(ActionEvent event) {
        String searchText = searchField.getText().trim();

        if (searchText.isEmpty()) {
            loadOrders();
            return;
        }

        ObservableList<BFDashboardDTO> filteredOrders = FXCollections.observableArrayList(
                dashboardBO.getAllOrders().stream()
                        .filter(order -> order.getGuestPhone().toLowerCase().contains(searchText.toLowerCase()))
                        .toList()
        );

        orderList.setAll(filteredOrders);
        orderTable.setItems(orderList);
    }
}
