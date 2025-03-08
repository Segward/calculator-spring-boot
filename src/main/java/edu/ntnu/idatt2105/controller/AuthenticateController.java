package edu.ntnu.idatt2105.controller;

import edu.ntnu.idatt2105.model.AuthenticationRequest;
import edu.ntnu.idatt2105.model.AuthenticationResponse;
import edu.ntnu.idatt2105.services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

  @Autowired AuthenticateService authenticateService;

  @RequestMapping(value = "/token", method = RequestMethod.GET)
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticateService.getToken(request));
  }

  @RequestMapping(value = "/valid", method = RequestMethod.GET)
  public ResponseEntity<Boolean> verify(
      @RequestBody AuthenticationRequest request, @RequestBody String jwt) {
    return ResponseEntity.ok(authenticateService.getValidity(request, jwt));
  }
}
