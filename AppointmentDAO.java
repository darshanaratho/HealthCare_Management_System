package HealthCareManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public static void bookAppointment(int patientId, int doctorId, String date) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, 'Scheduled')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setString(3, date);
            stmt.executeUpdate();
            System.out.println("Appointment booked successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                appointmentList.add(new Appointment(rs.getInt("appointment_id"), rs.getInt("patient_id"),
                        rs.getInt("doctor_id"), rs.getString("appointment_date"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }
    public static void cancelAppointment(int appointmentId) {
        String sql = "UPDATE appointments SET status = 'Cancelled' WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Appointment cancelled successfully.");
            } else {
                System.out.println("Appointment ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getTotalAppointments() {
        String sql = "SELECT COUNT(*) FROM appointments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Get count of completed appointments
    public static int getCompletedAppointments() {
        String sql = "SELECT COUNT(*) FROM appointments WHERE status = 'Completed'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Get count of cancelled appointments
    public static int getCancelledAppointments() {
        String sql = "SELECT COUNT(*) FROM appointments WHERE status = 'Cancelled'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

