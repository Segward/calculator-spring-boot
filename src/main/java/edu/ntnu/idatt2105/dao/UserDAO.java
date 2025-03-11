package edu.ntnu.idatt2105.dao;

import edu.ntnu.idatt2105.data.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

  public static void create(String username, String password) {
    DatabaseProvider.executeQuery(
        "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "');");
  }

  public static void delete(String username) {
    DatabaseProvider.executeQuery("DELETE FROM users WHERE username = '" + username + "';");
  }

  public static User extract(String username, String password) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");
    if (rs == null || !rs.next()) {
      return null;
    }
    String extractedUsername = rs.getString("username");
    String extractedPassword = rs.getString("password");
    byte[] extractedSalt = rs.getBytes("salt");
    return new User(extractedUsername, extractedPassword, extractedSalt);
  }
}
