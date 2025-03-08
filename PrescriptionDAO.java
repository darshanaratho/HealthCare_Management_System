package HealthCareManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {
    public static void addPrescription(int appointmentId, String medication, String dosage, String instructions) {
        String sql = "INSERT INTO prescriptions (appointment_id, medication, dosage, instructions) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            stmt.setString(2, medication);
            stmt.setString(3, dosage);
            stmt.setString(4, instructions);
            stmt.executeUpdate();
            System.out.println("Prescription added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Prescription> getPrescriptionsByAppointment(int appointmentId) {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM prescriptions WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                prescriptions.add(new Prescription(rs.getInt("appointment_id"), rs.getInt("appointment_id"),
                        rs.getString("medication"), rs.getString("dosage"), rs.getString("instructions")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }
}
