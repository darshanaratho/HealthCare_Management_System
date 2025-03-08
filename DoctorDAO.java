package HealthCareManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public static void addDoctor(String name, String specialization, String contact, String email, int experience) {
        String sql = "INSERT INTO doctors (name, specialization, contact, email, experience) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, name);
            stmt.setString(2, specialization);
            stmt.setString(3, contact);
            stmt.setString(4, email);
            stmt.setInt(5, experience);
    
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Doctor added successfully.");
            } else {
                System.out.println("Failed to add doctor.");
            }
    
        } catch (SQLException e) {
            System.err.println("Error adding doctor: " + e.getMessage());
        }
    }
    

    public static List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                doctorList.add(new Doctor(rs.getInt("doctor_id"), rs.getString("name"), rs.getString("specialization"),
                        rs.getString("contact"),rs.getString("email"),rs.getInt("experience")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorList;
    }
}
