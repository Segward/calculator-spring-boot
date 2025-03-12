package edu.ntnu.idatt2105.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDAO {

  public static void insert(int userId, String equation, String result) throws SQLException {
    String query = "INSERT INTO history (user_id, equation, result) VALUES (?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      stmt.setString(2, equation);
      stmt.setString(3, result);
      stmt.executeUpdate();
    }
  }

  public static ArrayList<String> fetchEquations(int userId) throws SQLException {
    String query = "SELECT * FROM history WHERE user_id = ? ORDER BY created_at DESC LIMIT 10";
    ArrayList<String> history = new ArrayList<>();
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          history.add(rs.getString("equation"));
        }
      }
    }
    return history;
  }

  public static ArrayList<String> fetchResults(int userId) throws SQLException {
    String query = "SELECT * FROM history WHERE user_id = ? ORDER BY created_at DESC LIMIT 10";
    ArrayList<String> history = new ArrayList<>();
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          history.add(rs.getString("result"));
        }
      }
    }
    return history;
  }

  public static boolean exists(int userId) throws SQLException {
    String query = "SELECT * FROM history WHERE user_id = ?";
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, userId);
      try (ResultSet rs = stmt.executeQuery()) {
        return rs.next();
      }
    }
  }
}