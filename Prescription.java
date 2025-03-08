package HealthCareManagementSystem;

public class Prescription {
    private int id;
    private int appointmentId;
    private String medication;
    private String dosage;
    private String instructions;

    public Prescription(int id, int appointmentId, String mediaction, String dosage, String instructions) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
    }

    public void display() {
        System.out.println("Prescription ID: " + id + ", Medicine: " + medication + ", Dosage: " + dosage +
                           ", Instructions: " + instructions);
    }
}

