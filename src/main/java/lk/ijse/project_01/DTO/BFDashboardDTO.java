package lk.ijse.project_01.DTO;

import javafx.beans.property.*;

public class BFDashboardDTO {
    private final StringProperty orderId = new SimpleStringProperty();
    private final StringProperty guestPhone = new SimpleStringProperty();
    private final StringProperty guestName = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final DoubleProperty bookingCost = new SimpleDoubleProperty();
    private final DoubleProperty fnbCost = new SimpleDoubleProperty();
    private final DoubleProperty totalCost = new SimpleDoubleProperty();
    private final StringProperty paymentStatus = new SimpleStringProperty();

    public BFDashboardDTO(String orderId, String guestPhone, String guestName, String description,
                          double bookingCost, double fnbCost, double totalCost, String paymentStatus) {
        this.orderId.set(orderId);
        this.guestPhone.set(guestPhone);
        this.guestName.set(guestName);
        this.description.set(description);
        this.bookingCost.set(bookingCost);
        this.fnbCost.set(fnbCost);
        this.totalCost.set(totalCost);
        this.paymentStatus.set(paymentStatus);
    }

    public String getOrderId() {
        return orderId.get();
    }

    public String getGuestPhone() {
        return guestPhone.get();
    }

    public String getGuestName() {
        return guestName.get();
    }

    public String getDescription() {
        return description.get();
    }

    public double getBookingCost() {
        return bookingCost.get();
    }

    public double getFnbCost() {
        return fnbCost.get();
    }

    public double getTotalCost() {
        return totalCost.get();
    }

    public String getPaymentStatus() {
        return paymentStatus.get();
    }


    public StringProperty orderIdProperty() {
        return orderId;
    }

    public StringProperty guestPhoneProperty() {
        return guestPhone;
    }

    public StringProperty guestNameProperty() {
        return guestName;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public DoubleProperty bookingCostProperty() {
        return bookingCost;
    }

    public DoubleProperty fnbCostProperty() {
        return fnbCost;
    }

    public DoubleProperty totalCostProperty() {
        return totalCost;
    }

    public StringProperty paymentStatusProperty() {
        return paymentStatus;
    }
}
