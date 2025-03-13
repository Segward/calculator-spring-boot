package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.UserDAO;
import edu.ntnu.idatt2105.models.TokenResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

  public TokenResponse getToken(String username, String password) throws Exception {
    if (username == null || password == null || username.length() < 1 || password.length() < 1) {
      throw new Exception("Username or password is null or empty");
    }
    boolean exists = UserDAO.exists(username);
    if (!exists) {
      byte[] salt = SecurityConfig.generateSalt();
      String hashedPassword = SecurityConfig.hashPassword(password, salt);
      UserDAO.insert(username, hashedPassword, salt);
    }
    byte[] salt = UserDAO.getSalt(username);
    String hashedPassword = UserDAO.getPassword(username);
    boolean valid = SecurityConfig.verifyPassword(password, salt, hashedPassword);
    if (!valid) {
      throw new Exception("Invalid password");
    }
    String jwt = SecurityConfig.generateToken(username);
    return new TokenResponse(jwt);
  }

  public boolean validate(String jwt) {
    try {
      String username = SecurityConfig.extractUsername(jwt);
      return SecurityConfig.validateToken(jwt, username);
    } catch (Exception e) {
      return false;
    }
  }
}