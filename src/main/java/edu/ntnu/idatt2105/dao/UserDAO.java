package edu.ntnu.idatt2105.dao;

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

  public static int getUserId(String username) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format("SELECT * FROM users WHERE username = '%s';", username));
    if (rs == null || !rs.next()) {
      return -1;
    }
    return rs.getInt("id");
  }

  public static byte[] getSalt(String username) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format("SELECT * FROM users WHERE username = '%s';", username));
    if (rs == null || !rs.next()) {
      return null;
    }
    return rs.getBytes("salt");
  }

  public static String getPassword(String username) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format("SELECT * FROM users WHERE username = '%s';", username));
    if (rs == null || !rs.next()) {
      return null;
    }
    return rs.getString("password");
  }

  public static boolean exists(String username) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format("SELECT * FROM users WHERE username = '%s';", username));
    return rs != null && rs.next();
  }
}
