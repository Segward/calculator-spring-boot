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

  public static void delete(String username) {
    DatabaseProvider.executeQuery(
        String.format("DELETE FROM history WHERE username = '%s';", username));
  }

  public static ArrayList<String> extract(String username) throws SQLException {
    ResultSet rs =
        DatabaseProvider.executeQuery(
            String.format("SELECT * FROM history WHERE username = '%s';", username));
    if (rs == null || !rs.next()) {
      return null;
    }
    ArrayList<String> history = new ArrayList<>();
    while (rs.next()) {
      history.add(rs.getString("action"));
    }
    return history;
  }
}
