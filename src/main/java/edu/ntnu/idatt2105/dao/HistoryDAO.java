package edu.ntnu.idatt2105.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDAO {

  public static void insert(String username, String action) {
    DatabaseProvider.executeQuery(
        String.format(
            "INSERT INTO history (username, action) VALUES ('%s','%s');", username, action));
  }

  public static ArrayList<String> fetch(int userId) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format(
                "SELECT * FROM history WHERE userId = %d ORDER BY created_at DESC;", userId));
    ArrayList<String> history = new ArrayList<>();
    while (rs.next()) {
      history.add(rs.getString("action"));
    }
    return history;
  }

  public static boolean exists(int userId) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format("SELECT * FROM history WHERE userId = %d;", userId));
    return rs != null && rs.next();
  }
}
