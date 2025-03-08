package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.config.SecurityConfig;
import edu.ntnu.idatt2105.model.AuthenticationRequest;
import edu.ntnu.idatt2105.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

  @Autowired SecurityConfig securityConfig;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) throws Exception {
    final String jwt = securityConfig.generateToken(request.getUsername());
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }


}
