package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.UserDAO;
import edu.ntnu.idatt2105.requests.AuthenticateRequest;
import edu.ntnu.idatt2105.responses.AuthenticateResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

  public AuthenticateResponse getToken(AuthenticateRequest request) throws Exception {
    boolean exists = UserDAO.exists(request.getUsername());
    if (!exists) {
      byte[] salt = SecurityConfig.generateSalt();
      String hashedPassword = SecurityConfig.hashPassword(request.getPassword(), salt);
      UserDAO.insert(request.getUsername(), hashedPassword, salt);
    }
    byte[] salt = UserDAO.getSalt(request.getUsername());
    String hashedPassword = UserDAO.getPassword(request.getUsername());
    boolean valid = SecurityConfig.verifyPassword(request.getPassword(), salt, hashedPassword);
    if (!valid) {
      throw new Exception("Invalid password");
    }
    String jwt = SecurityConfig.generateToken(request.getUsername());
    return new AuthenticateResponse(jwt);
  }
}