package Management;

 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanManager {

    public void borrowBook(int memberId, int bookId) throws ClassNotFoundException {
        String updateBookQuery = "UPDATE Books SET is_available = FALSE WHERE book_id = ?";
        String insertLoanQuery = "INSERT INTO loans (member_id, book_id, loan_date) VALUES (?, ?, CURDATE())";
        
        try (Connection connection = Database.getConnection()) {

            // Update book availability
            try (PreparedStatement bookStmt = connection.prepareStatement(updateBookQuery)) {
                bookStmt.setInt(1, bookId);
                bookStmt.executeUpdate();
            }

            // Insert new loan record
            try (PreparedStatement loanStmt = connection.prepareStatement(insertLoanQuery)) {
                loanStmt.setInt(1, memberId);
                loanStmt.setInt(2, bookId);
                loanStmt.executeUpdate();
                System.out.println("Book borrowed successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    public void returnBook(int bookId) throws ClassNotFoundException {
//        String updateBookQuery = "UPDATE Books SET is_available = TRUE WHERE book_id = ?";
//        String updateLoanQuery = "UPDATE Loans SET return_date = CURDATE() WHERE book_id = ? AND return_date IS NULL";
//        
//        try (Connection connection = Database.getConnection()) {
//
//            // Update book availability
//            try (PreparedStatement bookStmt = connection.prepareStatement(updateBookQuery)) {
//                bookStmt.setInt(1, bookId);
//                bookStmt.executeUpdate();
//            }
//
//            // Update loan record with return date
//            try (PreparedStatement loanStmt = connection.prepareStatement(updateLoanQuery)) {
//                loanStmt.setInt(1, bookId);
//                loanStmt.executeUpdate();
//                System.out.println("Book returned successfully!");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void returnBook(int bookId) throws ClassNotFoundException {
        String updateBookQuery = "UPDATE Books SET is_available = TRUE WHERE book_id = ?";
        String updateLoanQuery = "UPDATE Loans SET return_date = CURDATE() WHERE book_id = ? AND return_date IS NULL";
        
        try (Connection connection = Database.getConnection()) {

            // Ensure auto-commit is enabled or explicitly commit changes
            connection.setAutoCommit(false); // Optional if auto-commit is enabled by default

            // Update book availability
            try (PreparedStatement bookStmt = connection.prepareStatement(updateBookQuery)) {
                bookStmt.setInt(1, bookId);
                int rowsUpdated = bookStmt.executeUpdate();
                if (rowsUpdated == 0) {
                    System.out.println("No book found with the given book_id to update availability.");
                }
            }

            // Update loan record with return date
            try (PreparedStatement loanStmt = connection.prepareStatement(updateLoanQuery)) {
                loanStmt.setInt(1, bookId);
                int rowsUpdated = loanStmt.executeUpdate();
                if (rowsUpdated == 0) {
                    System.out.println("No loan found with the given book_id and return_date IS NULL.");
                } else {
                    System.out.println("Book returned successfully!");
                }
            }

            // Commit transaction
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
             
        }
    }

}

