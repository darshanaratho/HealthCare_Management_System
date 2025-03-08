package HealthCareManagementSystem;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public static void generateBill(int appointmentId, double amount) {
        String sql = "INSERT INTO bills (appointment_id, amount, payment_status) VALUES (?, ?, 'Pending')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
            System.out.println("Bill generated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void payBill(int billId) {
        String sql = "UPDATE bills SET payment_status = 'Paid' WHERE bill_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Bill paid successfully.");
            } else {
                System.out.println("Bill ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Bill> getAllBills() {
        List<Bill> billList = new ArrayList<>();
        String sql = "SELECT * FROM bills";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                billList.add(new Bill(rs.getInt("bill_id"), rs.getInt("appointment_id"),
                        rs.getDouble("amount"), rs.getString("payment_status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billList;
    }
    public static double getTotalRevenue() {
        String sql = "SELECT SUM(amount) FROM bills WHERE payment_status = 'Paid'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Get total unpaid bills
    public static double getTotalUnpaidAmount() {
        String sql = "SELECT SUM(amount) FROM bills WHERE payment_status = 'Pending'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Get list of unpaid bills
    public static List<Bill> getUnpaidBills() {
        List<Bill> unpaidBills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE payment_status = 'Pending'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                unpaidBills.add(new Bill(rs.getInt("bill_id"), rs.getInt("appointment_id"),
                        rs.getDouble("amount"), rs.getString("payment_status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unpaidBills;
    }
}
