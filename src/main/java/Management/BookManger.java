package Management;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManger {
	
	    public void addBook(int id,String title, String author) throws ClassNotFoundException {
	        String query = "INSERT INTO books (book_id,title, author,is_available) VALUES (?,?,?,true)";
	        try (Connection connection = Database.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
	            stmt.setString(2, title);
	            stmt.setString(3, author);
	            stmt.executeUpdate();
	            System.out.println("Book added successfully!");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void listBooks() throws ClassNotFoundException {
	        String query = "SELECT * FROM Books";
	        try (Connection connection = Database.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {

	            System.out.println("List of Books:");
	            while (rs.next()) {
	                System.out.println("ID: " + rs.getInt("book_id") + ", Title: " + rs.getString("title") +
	                                   ", Author: " + rs.getString("author") + 
	                                   ", Available: " + rs.getBoolean("is_available"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
