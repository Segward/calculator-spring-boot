package edu.ntnu.idatt2105.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDAO {

  public static void insert(String username, String action) throws SQLException {
    String query = "INSERT INTO history (username, action) VALUES (?, ?)";
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, username);
      stmt.setString(2, action);
      stmt.executeUpdate();
    }
  }

  public static ArrayList<String> fetch(int userId) throws SQLException {
    String query = "SELECT * FROM history WHERE userId = ? ORDER BY created_at DESC";
    ArrayList<String> history = new ArrayList<>();
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          history.add(rs.getString("action"));
        }
      }
    }
    return history;
  }

  public static boolean exists(int userId) throws SQLException {
    String query = "SELECT * FROM history WHERE userId = ?";
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      try (ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    }
  }
}