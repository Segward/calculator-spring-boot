package edu.ntnu.idatt2105.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

  public static boolean exists(String username) throws SQLException {
    String query = "SELECT COUNT(*) FROM users WHERE username = ?";
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, username);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return rs.getInt(1) > 0;
        }
      }
    }
    return false;
  }

  public static byte[] getSalt(String username) throws SQLException {
    String query = "SELECT salt FROM users WHERE username = ?";
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, username);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return rs.getBytes("salt");
        }
      }
    }
    return null;
  }

  public static String getPassword(String username) throws SQLException {
    String query = "SELECT password FROM users WHERE username = ?";
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, username);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return rs.getString("password");
        }
      }
    }
    return null;
  }

  public static int getUserId(String username) throws SQLException {
    String query = "SELECT id FROM users WHERE username = ?";
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, username);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return rs.getInt("id");
        }
      }
    }
    return -1;
  }

  public static void insert(String username, String hashedPassword, byte[] salt)
      throws SQLException {
    String query = "INSERT INTO users (username, password, salt) VALUES (?, ?, ?)";
    try (Connection conn = DatabaseProvider.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, username);
      stmt.setString(2, hashedPassword);
      stmt.setBytes(3, salt);
      stmt.executeUpdate();
    }
  }
}