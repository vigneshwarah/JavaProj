package vignesh;

import java.sql.*;
import java.util.Scanner;

public class ExpenseTracker {

    private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USER = "root"; // default XAMPP
    private static final String PASSWORD = ""; // default XAMPP

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // --- CRUD for Expenses ---
    private static void addExpense(Scanner sc) {
        try (Connection conn = getConnection()) {
            System.out.print("Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine(); // consume newline
            System.out.print("Category: ");
            String category = sc.nextLine();
            System.out.print("Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("Description: ");
            String desc = sc.nextLine();

            String sql = "INSERT INTO expenses (amount, category, date, description) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, category);
            pstmt.setString(3, date);
            pstmt.setString(4, desc);
            pstmt.executeUpdate();
            System.out.println("Expense added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewExpenses() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM expenses")) {

            System.out.println("ID | Amount | Category | Date | Description");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getDouble("amount") + " | " +
                                   rs.getString("category") + " | " +
                                   rs.getDate("date") + " | " +
                                   rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateExpense(Scanner sc) {
        try (Connection conn = getConnection()) {
            System.out.print("Enter Expense ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("New Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            System.out.print("New Category: ");
            String category = sc.nextLine();
            System.out.print("New Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("New Description: ");
            String desc = sc.nextLine();

            String sql = "UPDATE expenses SET amount=?, category=?, date=?, description=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, category);
            pstmt.setString(3, date);
            pstmt.setString(4, desc);
            pstmt.setInt(5, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Expense updated." : "Expense not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteExpense(Scanner sc) {
        try (Connection conn = getConnection()) {
            System.out.print("Enter Expense ID to delete: ");
            int id = sc.nextInt();
            String sql = "DELETE FROM expenses WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Expense deleted." : "Expense not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- CRUD for Income ---
    private static void addIncome(Scanner sc) {
        try (Connection conn = getConnection()) {
            System.out.print("Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            System.out.print("Source: ");
            String source = sc.nextLine();
            System.out.print("Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("Description: ");
            String desc = sc.nextLine();

            String sql = "INSERT INTO income (amount, source, date, description) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, source);
            pstmt.setString(3, date);
            pstmt.setString(4, desc);
            pstmt.executeUpdate();
            System.out.println("Income added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewIncome() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM income")) {

            System.out.println("ID | Amount | Source | Date | Description");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getDouble("amount") + " | " +
                                   rs.getString("source") + " | " +
                                   rs.getDate("date") + " | " +
                                   rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateIncome(Scanner sc) {
        try (Connection conn = getConnection()) {
            System.out.print("Enter Income ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("New Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            System.out.print("New Source: ");
            String source = sc.nextLine();
            System.out.print("New Date (YYYY-MM-DD): ");
            String date = sc.nextLine();
            System.out.print("New Description: ");
            String desc = sc.nextLine();

            String sql = "UPDATE income SET amount=?, source=?, date=?, description=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, source);
            pstmt.setString(3, date);
            pstmt.setString(4, desc);
            pstmt.setInt(5, id);

            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Income updated." : "Income not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteIncome(Scanner sc) {
        try (Connection conn = getConnection()) {
            System.out.print("Enter Income ID to delete: ");
            int id = sc.nextInt();
            String sql = "DELETE FROM income WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Income deleted." : "Income not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Main Menu ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Add Income");
            System.out.println("6. View Income");
            System.out.println("7. Update Income");
            System.out.println("8. Delete Income");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addExpense(sc);
                case 2 -> viewExpenses();
                case 3 -> updateExpense(sc);
                case 4 -> deleteExpense(sc);
                case 5 -> addIncome(sc);
                case 6 -> viewIncome();
                case 7 -> updateIncome(sc);
                case 8 -> deleteIncome(sc);
                case 9 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}

