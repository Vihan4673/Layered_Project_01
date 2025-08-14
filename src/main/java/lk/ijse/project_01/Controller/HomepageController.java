package lk.ijse.project_01.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_01.bo.Custom.DashboardBO;
import lk.ijse.project_01.bo.Custom.Impl.DashboardBOImpl;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    @FXML
    private Label availableRooms;

    @FXML
    private Label bookingCount;

    @FXML
    private BarChart<String, Number> fnbSalesChart;

    @FXML
    private Label mainDate;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private LineChart<String, Number> monthlyRevenueChart;

    @FXML
    private ComboBox<String> revenueFilterCombo;

    @FXML
    private Label totalFnbRevenue;

    @FXML
    private Label totalRevenue;

    @FXML
    private Label totalRoomPrice;

    private final DashboardBO dashboardBO = new DashboardBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        loadSummaryCards();
        loadRevenueChart("2025");
        loadTopFnbItems();
        loadYearsToComboBox();
    }

    private void loadSummaryCards() {
        try {
            totalRevenue.setText("Rs. " + dashboardBO.getTotalRevenue());
            bookingCount.setText(String.valueOf(dashboardBO.getBookingCount()));
            totalRoomPrice.setText("Rs. " + dashboardBO.getTotalRoomPrice());
            totalFnbRevenue.setText("Rs. " + dashboardBO.getFnbRevenue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadRevenueChart(String year) {
        monthlyRevenueChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        try {
            Map<String, Double> revenueData = dashboardBO.getMonthlyRevenue(year);
            for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        series.setName("Monthly Revenue - " + year);
        monthlyRevenueChart.getData().add(series);
    }

    private void loadTopFnbItems() {
        fnbSalesChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        try {
            Map<String, Integer> items = dashboardBO.getTopFnbItems();
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        series.setName("Top F&B Items");
        fnbSalesChart.getData().add(series);
    }

    private void loadYearsToComboBox() {
        revenueFilterCombo.setItems(FXCollections.observableArrayList("2023", "2024", "2025"));
        revenueFilterCombo.setValue("2025");
        revenueFilterCombo.setOnAction(e -> {
            String selectedYear = revenueFilterCombo.getSelectionModel().getSelectedItem();
            loadRevenueChart(selectedYear);
        });
    }
}
