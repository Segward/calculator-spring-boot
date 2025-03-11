package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.dao.UserDAO;
import edu.ntnu.idatt2105.data.User;
import edu.ntnu.idatt2105.model.AuthenticateRequest;
import edu.ntnu.idatt2105.model.AuthenticateResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

  private SecurityConfig securityConfig = new SecurityConfig();

  public AuthenticateResponse getToken(AuthenticateRequest request) {
    final String jwt = securityConfig.generateToken(request.getUsername());
    return new AuthenticateResponse(jwt);
  }

  public boolean getValidity(AuthenticateRequest request, String jwt) {
    return securityConfig.validateToken(jwt, request.getUsername());
  }

  public AuthenticateResponse login(AuthenticateRequest request) throws Exception {
    String username = request.getUsername();
    String password = request.getPassword();
    User user = UserDAO.extract(username);
    boolean validity = SecurityConfig.verifyPassword(password, user.getSalt(), user.getPassword());
    if (!validity) {
      throw new Exception("Invalid username or password");
    }
    return getToken(request);
  }

  public AuthenticateResponse register(AuthenticateRequest request) {
    String username = request.getUsername();
    byte[] salt = securityConfig.generateSalt();
    String hashedPassword = securityConfig.hashPassword(request.getPassword(), salt);
    UserDAO.insert(username, hashedPassword, salt);
    return getToken(request);
  }
}