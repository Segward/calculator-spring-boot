package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.UserDAO;
import edu.ntnu.idatt2105.models.AuthenticateResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

  public AuthenticateResponse getToken(String username, String password) throws Exception {
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
    return new AuthenticateResponse(jwt);
  }

  public Boolean validate(String jwt) throws Exception {
    String username = SecurityConfig.extractUsername(jwt);
    return SecurityConfig.validateToken(jwt, username);
  }
}