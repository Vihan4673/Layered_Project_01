package lk.ijse.project_01.Entity;

public class BFDashboardEntity {
    private String orderId;
    private String guestPhone;
    private String guestName;
    private String description;
    private double bookingCost;
    private double fnbCost;
    private double totalCost;
    private String paymentStatus;

    public BFDashboardEntity(String orderId, String guestPhone, String guestName, String description,
                             double bookingCost, double fnbCost, double totalCost, String paymentStatus) {
        this.orderId = orderId;
        this.guestPhone = guestPhone;
        this.guestName = guestName;
        this.description = description;
        this.bookingCost = bookingCost;
        this.fnbCost = fnbCost;
        this.totalCost = totalCost;
        this.paymentStatus = paymentStatus;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getDescription() {
        return description;
    }

    public double getBookingCost() {
        return bookingCost;
    }

    public double getFnbCost() {
        return fnbCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    // Setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBookingCost(double bookingCost) {
        this.bookingCost = bookingCost;
    }

    public void setFnbCost(double fnbCost) {
        this.fnbCost = fnbCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
