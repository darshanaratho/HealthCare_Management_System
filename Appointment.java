package HealthCareManagementSystem;


public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private String date;
    private String status;

    public Appointment(int id, int patientId, int doctorId, String date, String status) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.status = status;
    }

    public void display() {
        System.out.println("ID: " + id + ", Patient ID: " + patientId + ", Doctor ID: " + doctorId + 
                           ", Date: " + date + ", Status: " + status);
    }
}
