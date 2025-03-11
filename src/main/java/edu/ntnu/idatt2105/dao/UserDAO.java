package edu.ntnu.idatt2105.dao;

import edu.ntnu.idatt2105.data.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

  public static void insert(String username, String password, byte[] salt) {
    String query =
        String.format(
            "INSERT INTO users (username, password, salt) VALUES ('%s','%s','%s');",
            username, password, salt);
    DatabaseProvider.executeQuery(query);
  }

  public static void delete(String username) {
    DatabaseProvider.executeQuery(
        String.format("DELETE FROM users WHERE username = '%s';", username));
  }

  public static User extract(String username, String password) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format(
                "SELECT * FROM users WHERE username = '%s' AND password = '%s';",
                username, password));
    if (rs == null || !rs.next()) {
      return null;
    }
    String extractedUsername = rs.getString("username");
    String extractedPassword = rs.getString("password");
    byte[] extractedSalt = rs.getBytes("salt");
    return new User(extractedUsername, extractedPassword, extractedSalt);
  }
}
