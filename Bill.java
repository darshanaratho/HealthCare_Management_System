package HealthCareManagementSystem;


public class Bill {
    private int id;
    private int appointmentId;
    private double amount;
    private String paymentStatus;

    public Bill(int id, int appointmentId, double amount, String paymentStatus) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public void display() {
        System.out.println("Bill ID: " + id + ", Appointment ID: " + appointmentId + ", Amount: $" + amount +
                           ", Payment Status: " + paymentStatus);
    }
}
