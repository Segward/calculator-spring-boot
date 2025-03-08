package edu.ntnu.idatt2105.services;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.model.AuthenticationRequest;
import edu.ntnu.idatt2105.model.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

  private SecurityConfig securityConfig = new SecurityConfig();

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    final String jwt = securityConfig.generateToken(request.getUsername());
    return new AuthenticationResponse(jwt);
  }

  public boolean validate(AuthenticationRequest request, String jwt) {
    return securityConfig.validateToken(jwt, request.getUsername());
  }
}
