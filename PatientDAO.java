package HealthCareManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    //public static void addPatient(String name, int age, String gender, String contact, String email, String address, String disease) {        public static void addPatient(String name, int age, String gender, String contact, String email, String address, String disease)
    public static void addPatient(String name, int age, String gender, String contact, String email, String address, String disease){

        String sql = "INSERT INTO patients (name, age, gender, contact, email, address, disease) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, gender);
            stmt.setString(4, contact);
            stmt.setString(5, email);
            stmt.setString(6, address);
            stmt.setString(7, disease);
            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                patientList.add(new Patient(rs.getInt("patient_id"), rs.getString("name"), rs.getInt("age"),
                        rs.getString("gender"), rs.getString("contact"),rs.getString("email"), rs.getString("disease"),rs.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientList;
    }
}
