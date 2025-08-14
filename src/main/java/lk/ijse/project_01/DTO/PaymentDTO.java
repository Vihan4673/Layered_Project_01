package lk.ijse.project_01.DTO;

import java.time.LocalDate;

public class PaymentDTO {
    private String guestPhone;
    private double amount;
    private String paymentMethod;
    private LocalDate paymentDate;

    public PaymentDTO() { }

    public PaymentDTO(String guestPhone, double amount, String paymentMethod, LocalDate paymentDate) {
        this.guestPhone = guestPhone;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
