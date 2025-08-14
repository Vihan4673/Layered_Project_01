package lk.ijse.project_01.Entity;

import java.time.LocalDate;

public class Payment {
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String guestPhone;

    public Payment(double amount, LocalDate paymentDate, String paymentMethod, String guestPhone) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.guestPhone = guestPhone;
    }

    public double getAmount() { return amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getGuestPhone() { return guestPhone; }
}
