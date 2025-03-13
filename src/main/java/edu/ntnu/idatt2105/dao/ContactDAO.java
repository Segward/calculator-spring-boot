package edu.ntnu.idatt2105.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDAO {

  public static void insert(int userId, String name, String email, String message)
      throws SQLException {
    String query = "INSERT INTO contact (user_id, name, email, message) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      stmt.setString(2, name);
      stmt.setString(3, email);
      stmt.setString(4, message);
      stmt.executeUpdate();
    }
  }
}
