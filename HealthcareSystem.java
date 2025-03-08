package HealthCareManagementSystem;

import java.util.List;
import java.util.Scanner;

public class HealthcareSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\n=== Healthcare Management System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Doctor");
            System.out.println("4. View Doctors");
            System.out.println("5. Book Appointment");
            System.out.println("6. View Appointments");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. Add Prescription");
            System.out.println("9. View Prescriptions");
            System.out.println("10. Generate Bill");
            System.out.println("11. Pay Bill");
            System.out.println("12. View Bills");
            System.out.println("13. Gererate Reports");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: addPatient(); 
                    break;
                case 2: viewPatients(); 
                    break;
                case 3: addDoctor(); 
                    break;
                case 4: viewDoctors(); 
                    break;
                case 5: bookAppointment(); 
                    break;
                case 6: viewAppointments(); 
                    break;
                case 7: cancelAppointment();
                    break;
                case 8: addPrescription(); 
                    break;
                case 9: viewPrescriptions();
                     break;
                case 10: generateBill(); 
                     break;
                case 11: payBill(); 
                     break;
                case 12: viewBills(); 
                     break;    
                case 13: generateReports();
                    break;
                case 14: exitProgram();
                    break;
                   
                 //System.out.println("Exiting..."); System.exit(0);
                
                default: System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addPatient() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        System.out.println("ENter the Email:");
        String email=scanner.nextLine();
        System.out.println("ENter Address:");
        String address=scanner.nextLine();
        System.out.print("Enter Disease: ");
        String disease = scanner.nextLine();
        PatientDAO.addPatient(name, age, gender, contact,email,address, disease );
    }

    private static void viewPatients() {
        List<Patient> patients = PatientDAO.getAllPatients();
        System.out.println("\n=== Patient List ===");
        for (Patient p : patients) {
            p.display();
        }
    }

    private static void addDoctor() {
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        System.out.println("Enter Email:");
        String email=scanner.nextLine();
        System.out.println("ENter Experience:");
        int experience=scanner.nextInt();
        DoctorDAO.addDoctor(name, specialization, contact,email,experience);
    }

    private static void viewDoctors() {
        List<Doctor> doctors = DoctorDAO.getAllDoctors();
        System.out.println("\n=== Doctor List ===");
        for (Doctor d : doctors) {
            d.display();
        }
    }

    private static void bookAppointment() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        AppointmentDAO.bookAppointment(patientId, doctorId, date);
    }

    private static void viewAppointments() {
        List<Appointment> appointments = AppointmentDAO.getAllAppointments();
        System.out.println("\n=== Appointment List ===");
        for (Appointment a : appointments) {
            a.display();
        }
    }
    private static void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        AppointmentDAO.cancelAppointment(appointmentId);
    }
    private static void addPrescription() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Medicine: ");
        String medicine = scanner.nextLine();
        System.out.print("Enter Dosage: ");
        String dosage = scanner.nextLine();
        System.out.print("Enter Instructions: ");
        String instructions = scanner.nextLine();
        PrescriptionDAO.addPrescription(appointmentId, medicine, dosage, instructions);
    }
    
    private static void viewPrescriptions() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        List<Prescription> prescriptions = PrescriptionDAO.getPrescriptionsByAppointment(appointmentId);
        for (Prescription p : prescriptions) {
            p.display();
        }
    }
    private static void generateBill() {
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        System.out.print("Enter Bill Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        BillDAO.generateBill(appointmentId, amount);
    }
    
    private static void payBill() {
        System.out.print("Enter Bill ID: ");
        int billId = scanner.nextInt();
        scanner.nextLine();
        BillDAO.payBill(billId);
    }
    
    private static void viewBills() {
        List<Bill> bills = BillDAO.getAllBills();
        for (Bill b : bills) {
            b.display();
        }
    }
    private static void generateReports() {
        System.out.println("\n===== Hospital Reports =====");
        
        // Revenue Reports
        double totalRevenue = BillDAO.getTotalRevenue();
        System.out.println("Total Revenue (Paid Bills): $" + totalRevenue);
    
        double totalUnpaid = BillDAO.getTotalUnpaidAmount();
        System.out.println("Total Unpaid Bills: $" + totalUnpaid);
    
        // Unpaid Bills Report
        List<Bill> unpaidBills = BillDAO.getUnpaidBills();
        if (!unpaidBills.isEmpty()) {
            System.out.println("\nUnpaid Bills:");
            for (Bill b : unpaidBills) {
                b.display();
            }
        } else {
            System.out.println("No unpaid bills.");
        }
    
        // Appointment Reports
        int totalAppointments = AppointmentDAO.getTotalAppointments();
        System.out.println("\nTotal Appointments: " + totalAppointments);
    
        int completedAppointments = AppointmentDAO.getCompletedAppointments();
        System.out.println("Completed Appointments: " + completedAppointments);
    
        int cancelledAppointments = AppointmentDAO.getCancelledAppointments();
        System.out.println("Cancelled Appointments: " + cancelledAppointments);
    }
    public static void exitProgram() {
        System.out.print("Exiting");
    
        try {
            for (int i = 0; i < 3; i++) {  // Print 3 dots with 1-second delay
                Thread.sleep(1000); 
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            System.err.println("Sleep interrupted: " + e.getMessage());
        }
    
        System.out.println("\nGoodbye!");
        System.exit(0);  // Exit the program
    }
    
}

