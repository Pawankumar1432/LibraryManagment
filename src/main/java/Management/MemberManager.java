package Management;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberManager {
    public void addMember(int member_id,String name) throws ClassNotFoundException {
        String query = "INSERT INTO Members (member_id,name, membership_date) VALUES (?,?, CURDATE())";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, member_id);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("Member added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listMembers() throws ClassNotFoundException {
        String query = "SELECT * FROM Members";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("List of Members:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("member_id") + ", Name: " + rs.getString("name") +
                                   ", Membership Date: " + rs.getDate("membership_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
