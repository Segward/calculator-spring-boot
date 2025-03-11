package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.UserDAO;
import edu.ntnu.idatt2105.responses.AuthenticateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticateService.class);

  public AuthenticateResponse getToken(String username, String password) throws Exception {
    boolean exists = UserDAO.exists(username);
    if (!exists) {
      byte[] salt = SecurityConfig.generateSalt();
      String hashedPassword = SecurityConfig.hashPassword(password, salt);
      logger.info("Creating new user: {}", username);
      UserDAO.insert(username, hashedPassword, salt);
    }
    byte[] salt = UserDAO.getSalt(username);
    String hashedPassword = UserDAO.getPassword(username);
    logger.info("Verifying password: {}", username);
    boolean valid = SecurityConfig.verifyPassword(password, salt, hashedPassword);
    if (!valid) {
      throw new Exception("Invalid password");
    }
    String jwt = SecurityConfig.generateToken(username);
    return new AuthenticateResponse(jwt);
  }
}