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
      UserDAO.insert(request.getUsername(), request.getPassword(), salt);
    }
    String jwt = SecurityConfig.generateToken(request.getUsername());
    return new AuthenticateResponse(jwt);
  }
}